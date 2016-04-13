package org.usfirst.frc2175.config;

import java.util.Properties;
import java.util.logging.Logger;

import org.usfirst.frc2175.util.HighestArrayIndexFinder;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessingConfig extends BaseConfig {
    private final Logger log = Logger.getLogger(getClass().getName());

    private static final String PROPERTY_FILE_NAME = "vision.properties";

    private NetworkTable contourReport;
    private HighestArrayIndexFinder indexFinder;

    private String contourReportName;

    private double[] defaultValue = { 0 };

    private int webCamQuality;
    private String webCamName;
    private double cameraFOV;
    private double cameraHorizontalRes;

    private double previousCenterXValue;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        this.contourReportName =
                getStringPropertyValue("GRIP.networktable.name", properties);

        SmartDashboard.putString("Vision table location", contourReportName);

        contourReport = NetworkTable.getTable(contourReportName);

        indexFinder = new HighestArrayIndexFinder();

        this.webCamQuality =
                getIntPropertyValue("webcam.usb.quality", properties);
        this.webCamName = getStringPropertyValue("webcam.usb.name", properties);
        this.cameraFOV = getDoublePropertyValue("camera.fov", properties);
        this.cameraHorizontalRes =
                getDoublePropertyValue("camera.res.horizontal", properties);
    }

    private void updateTable() {
        // TODO figure out how to refresh a networktable
    }

    public double[] getContourCenterX() {
        updateTable();
        double[] value = contourReport.getNumberArray("centerX", defaultValue);
        return value;
    }

    public double[] getContourCenterY() {
        updateTable();
        return contourReport.getNumberArray("centerY", defaultValue);
    }

    public double[] getContourWidth() {
        updateTable();
        return contourReport.getNumberArray("width", defaultValue);
    }

    public double getLargestContourCenterX() {
        double[] contourWidths;
        double[] contourCenterXs;
        synchronized (this) {
            contourWidths = getContourWidth();
            contourCenterXs = getContourCenterX();
        }

        final double value;

        int largestContourIndex =
                indexFinder.determineLargestArrayItemIndex(contourWidths);

        if (largestContourIndex == HighestArrayIndexFinder.NO_VALUES) {
            value = HighestArrayIndexFinder.NO_VALUES;
        } else if (contourWidths.length != contourCenterXs.length) {
            log.warning("Center X arrays weren't the same length!"
                    + " Using previous value of " + previousCenterXValue
                    + ". contourCenterXs.length=" + contourCenterXs.length
                    + "; contourWidths.length=" + contourWidths.length);
            value = previousCenterXValue;
        } else {
            value = contourCenterXs[largestContourIndex];
            previousCenterXValue = value;
        }

        log.info("Center X value returned: " + value);
        return value;
    }

    public double getCameraFOV() {
        return cameraFOV;
    }

    public double getCameraHorizontalRes() {
        return cameraHorizontalRes;
    }

    public int getWebCamQuality() {
        return webCamQuality;
    }

    public String getWebCamName() {
        return webCamName;
    }
}
