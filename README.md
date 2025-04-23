# OrganoidsPlugin

**OrganoidsPlugin** is an ImageJ/Fiji plugin (written in Java) for automated, high-throughput analysis of 3D cerebral organoid cultures. It streamlines the measurement of early developmental morphology—such as embryoid body perimeter and neuroepithelium thickness—from your brightfield or phase-contrast images, and exports standardized results for downstream statistics.

---

## 🔍 Features

- **Automatic EB Segmentation**  
  Detects and segments each embryoid body (EB) in your image stack.  

- **Morphological Metrics**  
  • EB perimeter, area, circularity  
  • Neuroepithelium (NE) thickness at multiple sampling points  

- **Batch Processing**  
  Process entire folders of images in one go.  

- **Results Export**  
  Saves quantitative measurements in a CSV file for easy import into R, Python, or Excel.

---

## ⚙️ Prerequisites

- **Fiji / ImageJ** (version ≥ 1.53 or later)  
- **Java 8** (or later) runtime  

---

## 🚀 Installation

1. **Download** the latest `OrganoidsPlugin.jar` from the [Releases](https://github.com/puerhmaster/OrganoidsPlugin/releases) page.  
2. **Copy** `OrganoidsPlugin.jar` into your Fiji/ImageJ `plugins/` directory.  
3. **Restart** Fiji/ImageJ.  

---

## 🛠️ Usage

1. In Fiji, go to **Plugins → Organoids → Organoid Analysis**.  
2. In the dialog:  
   - **Select Input Folder** containing your EB images.  
   - **Adjust Parameters** (e.g. thresholding method, NE sampling lines).  
   - **Choose Output Path** for the CSV results.  
3. Click **Run**.  
4. Find your measurements (perimeter, NE thickness, etc.) in the exported `results.csv`.

---

## 🎯 Typical Workflow

1. Seed and grow organoids under your desired conditions.  
2. Acquire brightfield/phase-contrast images at early timepoints (Days 4–9).  
3. Run **OrganoidsPlugin** to extract morphological metrics.  
4. Correlate these metrics with downstream assays (qPCR, immunostaining) in R/Python.

---

## 🤝 Contributing

1. Fork the repo  
2. Create a feature branch (`git checkout -b feature/YourFeature`)  
3. Commit your changes (`git commit -m "Add some feature"`)  
4. Push to the branch (`git push origin feature/YourFeature`)  
5. Open a Pull Request  

Please also file any bug reports under **Issues**.

---

## 📄 License

© 2025 Evgenii Kachkin

This plugin is provided free of charge for any use—academic, commercial, or personal. You are welcome to:

- **Use** it in your own work.
- **Modify** the source code.
- **Distribute** copies, including your modified versions.

**In return, please:**
1. **Credit** the original author (Evgenii Kachkin) in your documentation or code headers.
2. **Contribute back** any bug fixes or enhancements via pull requests to  
   <https://github.com/puerhmaster/OrganoidsPlugin>.

---