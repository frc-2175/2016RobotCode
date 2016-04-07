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
    private String webCamUSBName;
    private String webCamAxisName;

    private int[] crosshairPointTop;
    private int[] crosshairPointBottom;
    private int[] crosshairPointLeft;
    private int[] crosshairPointRight;

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
        String webCamUSBName =
                getStringPropertyValue("webcam.usb.name", properties);
        this.webCamUSBName = webCamUSBName;
        String webCamAxisName =
                getStringPropertyValue("webcam.axis.name", properties);
        this.webCamAxisName = webCamAxisName;

        this.crosshairPointTop =
                getIntArrayPropertyValue("crosshair.point.top", properties);
        this.crosshairPointBottom =
                getIntArrayPropertyValue("crosshair.point.bottom", properties);
        this.crosshairPointLeft =
                getIntArrayPropertyValue("crosshair.point.left", properties);
        this.crosshairPointRight =
                getIntArrayPropertyValue("crosshair.point.right", properties);
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

    public String getWebCamUSBName() {
        return webCamUSBName;
    }

    public String getWebCamAxisName() {
        return webCamAxisName;
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

    public int[] getCrosshairPointTop() {
        return crosshairPointTop;
    }

    public int[] getCrosshairPointBottom() {
        return crosshairPointBottom;
    }

    public int[] getCrosshairPointLeft() {
        return crosshairPointLeft;
    }

    public int[] getCrosshairPointRight() {
        return crosshairPointRight;
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

}
