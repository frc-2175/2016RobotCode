package org.usfirst.frc2175.config;

import java.util.Properties;

import org.usfirst.frc2175.util.HighestArrayIndexFinder;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessingConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "vision.properties";

    private NetworkTable contourReport;
    private HighestArrayIndexFinder indexFinder;

    private double[] defaultValue = { 0 };

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        String contourReportName =
                getStringPropertyValue("GRIP.networktable.name", properties);

        SmartDashboard.putString("Vision table location", contourReportName);

        contourReport = NetworkTable.getTable(contourReportName);
        indexFinder = new HighestArrayIndexFinder();
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

        int largestContourIndex =
                indexFinder.determineLargestArrayItemIndex(contourWidths);

        return contourCenterXs[largestContourIndex];
    }

}
