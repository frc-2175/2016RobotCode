package org.usfirst.frc2175.config;

import java.util.Properties;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class VisionProcessingConfig extends BaseConfig {
    private static final String PROPERTY_FILE_NAME = "vision.properties";

    private String contourReportName;

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

        this.webCamQuality =
                getIntPropertyValue("webcam.usb.quality", properties);
        this.webCamName = getStringPropertyValue("webcam.usb.name", properties);
        this.cameraFOV = getDoublePropertyValue("camera.fov", properties);
        this.cameraHorizontalRes =
                getDoublePropertyValue("camera.res.horizontal", properties);
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

    public String getContourReportName() {
        return contourReportName;
    }
}
