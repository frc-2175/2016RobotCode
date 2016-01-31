package org.usfirst.frc2175.config;

import java.util.Properties;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessingConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "vision.properties";

    private NetworkTable contourReport;

    private double[] defaultValue = { 0 };

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        String contourReportName = getStringPropertyValue("GRIP.networktable.name", properties);

        SmartDashboard.putString("Vision table location", contourReportName);

        contourReport = NetworkTable.getTable(contourReportName);
    }

    public double[] getContourCenterX() {
        double[] value = contourReport.getNumberArray("centerX", defaultValue);

        System.out.println("Getting contourCenterX: " + value[0]);
        return value;
    }

    public double[] getContourCenterY() {
        return contourReport.getNumberArray("centerY", defaultValue);
    }

    public double[] getContourHeight() {
        return contourReport.getNumberArray("height", defaultValue);
    }

    public double[] getContourWidth() {
        return contourReport.getNumberArray("width", defaultValue);
    }

    public double getLargestContourCenterX() {
        double[] contourWidths = getContourWidth();
        double[] contourCenterXs = getContourCenterX();

        int largestContourIndex = determineLargestArrayItemIndex(contourWidths);

        return contourCenterXs[largestContourIndex];
    }

    public int determineLargestArrayItemIndex(double[] array) {
        int numberOfItems = array.length;

        double largestIndividualItem = 0;
        int largestItemIndex = 0;

        for (int i = 0; i < numberOfItems; i++) {
            if (array[i] > largestIndividualItem) {
                largestIndividualItem = array[i];
                largestItemIndex = i;
            }
        }

        return largestItemIndex;
    }
}
