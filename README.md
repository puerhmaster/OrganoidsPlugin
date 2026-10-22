# OrganoidsPlugin

**OrganoidsPlugin** is an ImageJ/Fiji plugin (written in Java) for automated batch analysis of early cerebral organoids. It segments embryoid bodies (EBs) in brightfield or phase-contrast images, measures their area and perimeter, and exports the results of a whole folder to one CSV file for downstream statistics.

---

## 🔍 Features

- **Automatic EB Segmentation**  
  Detects and segments embryoid bodies in each image (contrast enhancement, Gaussian blur, threshold, watershed, *Analyze Particles*).  

- **Morphological Metrics**  
  • EB area and perimeter  

- **Batch Processing**  
  Processes every `.tif`, `.tiff`, `.jpg`, `.jpeg` and `.png` image in a folder in one run.  

- **Results Export**  
  Saves all measurements in one CSV file (`ResultsX_1dps.csv`) for easy import into R, Python, or Excel.

---

## ⚙️ Prerequisites

- **Fiji / ImageJ** (version ≥ 1.53 or later)  
- **Java 8** (or later) runtime  

---

## 🚀 Installation

There is no prebuilt `.jar` release yet — the plugin is built from source:

1. **Download** `OrganoidsPlugin.java` from this repository.  
2. In Fiji, open **Plugins → Compile and Run…** and select `OrganoidsPlugin.java`. Fiji compiles the plugin and starts it.  

---

## 🛠️ Usage

1. Start the plugin with **Plugins → Compile and Run…** (see Installation).  
2. Choose the **folder with input images** (EB images).  
3. Choose the **folder to save the final CSV**.  
4. The plugin processes every image and shows the outlines of the detected particles.  
5. Find the measurements (area, perimeter, image label) in `ResultsX_1dps.csv` in the output folder.

Processing parameters (Gaussian blur σ = 60, threshold 0–180) are set in the code; change them in `OrganoidsPlugin.java` if your images need different values.

---

## 🎯 Typical Workflow

1. Seed and grow organoids under your desired conditions.  
2. Acquire brightfield/phase-contrast images at early timepoints (Days 4–9).  
3. Run **OrganoidsPlugin** to extract EB area and perimeter.  
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