import ij.IJ;
import ij.ImagePlus;
import ij.io.DirectoryChooser;
import ij.measure.ResultsTable;
import ij.plugin.PlugIn;
import java.io.File;

public class OrganoidsPlugin implements PlugIn {

    @Override
    public void run(String arg) {

        // 1) Prompt for the input folder
        DirectoryChooser dcIn = new DirectoryChooser("Choose the folder with input images");
        String inputDir = dcIn.getDirectory();
        if (inputDir == null) {
            IJ.error("No input folder selected. Plugin canceled.");
            return;
        }

        // 2) Prompt for the output folder
        DirectoryChooser dcOut = new DirectoryChooser("Choose the folder to save the final CSV");
        String outputDir = dcOut.getDirectory();
        if (outputDir == null) {
            IJ.error("No output folder selected. Plugin canceled.");
            return;
        }

        // Ensure trailing slash
        if (!outputDir.endsWith(File.separator)) {
            outputDir += File.separator;
        }

        // 3) Configure measurements: measure area + perimeter; display them
        // Also label so we can see which ROI is which
        IJ.run("Set Measurements...", "area perimeter display label decimal=3");

        // IMPORTANT: Clear any old data so we start fresh
        IJ.run("Clear Results");

        // 4) Get the files in the input directory
        File inFolder = new File(inputDir);
        File[] files = inFolder.listFiles();
        if (files == null || files.length == 0) {
            IJ.error("No files found in input folder.");
            return;
        }

        // We'll keep using the *same* global Results Table for all images
        ResultsTable rt = ResultsTable.getResultsTable();
        if (rt == null) {
            // If for some reason we can't get the global table, create a new one
            rt = new ResultsTable();
            rt.show("Results");
        }

        // 5) Loop over each file
        for (File f : files) {
            if (!f.isFile()) continue; // skip directories

            String name = f.getName().toLowerCase();
            // Only process TIF, JPG, PNG, etc.
            if (name.endsWith(".tif") || name.endsWith(".tiff") ||
                name.endsWith(".jpg") || name.endsWith(".jpeg") ||
                name.endsWith(".png")) 
            {
                IJ.log("Processing: " + f.getName());
                
                // A) Open image
                ImagePlus imp = IJ.openImage(f.getAbsolutePath());
                if (imp == null) {
                    IJ.log("Could not open: " + f.getAbsolutePath());
                    continue;
                }
                imp.setTitle(f.getName());
                imp.show();

                // B) Enhance Contrast
                IJ.run("Enhance Contrast...", "saturated=0.60 normalize process");

                // C) Convert to 8-bit
                IJ.run(imp, "8-bit", "");

                // D) Blur
                IJ.run(imp, "Gaussian Blur...", "sigma=60");

                // E) Threshold and mask
                IJ.setThreshold(imp, 0, 180);
                IJ.run(imp, "Convert to Mask", "");

                // F) Watershed
                IJ.run(imp, "Watershed", "");

                // G) Analyze Particles
                // "display" so the Results Table is updated each time
                // "show=Outlines" to see the outlines image
                IJ.run(imp, "Analyze Particles...", 
                    "size=0-Infinity circularity=0.00-1.00 show=Outlines display add");

                // Now, we want to append the file name to each row that was just added.
                // We'll do that by checking how many rows are in the ResultsTable before & after.

                int preCount = rt.size(); // row count before Analyze Particles
                // The Analyze Particles command has updated the same "global" table

                int postCount = rt.size(); // row count after
                // For each newly added row (from preCount to postCount-1),
                // set the "FileName" column
                for (int row = preCount; row < postCount; row++) {
                    rt.setValue("FileName", row, f.getName());
                }

                // We're NOT clearing the table here. We'll keep building it for all images.

                // Close this image
                imp.changes = false;
                imp.close();
            }
        }

        // 6) Done with all images. Now save *one* big CSV with all rows
        if (rt.size() == 0) {
            IJ.log("No measurements found overall. Exiting.");
        } else {
            // Save final CSV
            String finalCSV = outputDir + "ResultsX_1dps.csv";
            IJ.log("Saving combined results to: " + finalCSV);
            try {
                rt.saveAs(finalCSV);
            } catch (Exception e) {
                IJ.log("Error saving combined CSV: " + e.getMessage());
            }
        }

        IJ.log("Single-CSV batch processing complete!");
    }
}