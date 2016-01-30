package org.usfirst.frc2175.config;

import java.util.Properties;

import edu.wpi.first.wpilibj.networktables.NetworkTable;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessingConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "vision.properties";

    NetworkTable contourReport;

    double[] defaultValue;

    @Override
    public String getPropertyFileName() {
        return PROPERTY_FILE_NAME;
    }

    @Override
    protected void configure(Properties properties) {
        String contourReportName = getStringPropertyValue("GRIP.networktable.name", properties);
        SmartDashboard.putString("Vision table location", contourReportName);
        contourReport = NetworkTable.getTable(contourReportName);

        defaultValue = new double[0];
    }

    public double[] getContourCenterX() {
        return contourReport.getNumberArray("centerX", defaultValue);
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

}
