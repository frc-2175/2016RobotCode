package org.usfirst.frc2175.config;

import java.util.Properties;

import org.usfirst.frc2175.util.HighestArrayIndexFinder;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessingConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "vision.properties";

    private NetworkTable contourReport;
    private HighestArrayIndexFinder indexFinder;

    private String contourReportName;

    private double[] defaultValue = { 0 };

    private int webCamQuality;
    private String webCamName;
    private double cameraFOV;
    private double cameraHorizontalRes;

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

        int webCamQuality =
                getIntPropertyValue("webcam.usb.quality", properties);
        this.webCamQuality = webCamQuality;
        String webCamName =
                getStringPropertyValue("webcam.usb.name", properties);
        this.webCamName = webCamName;
        double cameraFOV = getDoublePropertyValue("camera.fov", properties);
        this.cameraFOV = cameraFOV;
        double cameraHorizontalRes =
                getDoublePropertyValue("camera.res.horizontal", properties);
        this.cameraHorizontalRes = cameraHorizontalRes;
    }

    private void updateTable() {
        // TODO figure out how to refresh a networktable
    }

    public double[] getContourCenterX() {
        updateTable();
        double[] value = contourReport.getNumberArray("centerX", defaultValue);
        return value;
    }

    public int getWebCamQuality() {
        return webCamQuality;
    }

    public String getWebCamName() {
        return webCamName;
    }

    public double[] getContourCenterY() {
        updateTable();
        return contourReport.getNumberArray("centerY", defaultValue);
    }

    public double[] getContourHeight() {
        updateTable();
        return contourReport.getNumberArray("height", defaultValue);
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

        double value;

        int largestContourIndex =
                indexFinder.determineLargestArrayItemIndex(contourWidths);

        if (largestContourIndex == HighestArrayIndexFinder.NO_VALUES) {
            value = HighestArrayIndexFinder.NO_VALUES;
        } else {
            if (contourCenterXs.length != contourWidths.length) {
                System.out.println("contourCenterXs.length="
                        + contourCenterXs.length + "; contourWidths.length="
                        + contourWidths.length);
            }
            value = contourCenterXs[largestContourIndex];
        }
        return value;
    }

    public double getCameraFOV() {
        return cameraFOV;
    }

    public double getCameraHorizontalRes() {
        return cameraHorizontalRes;
    }

}
