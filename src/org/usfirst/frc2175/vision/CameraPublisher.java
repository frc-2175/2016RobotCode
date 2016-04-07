package org.usfirst.frc2175.vision;

import org.usfirst.frc2175.config.RobotConfig;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraPublisher {
    private RobotConfig robotConfig;

    private Image cFrame;
    private int cSession;
    private ImageProcessor imageProcessor;

    private NIVision.Point crosshairPointTop;
    private NIVision.Point crosshairPointBottom;
    private NIVision.Point crosshairPointLeft;
    private NIVision.Point crosshairPointRight;

    public CameraPublisher(RobotConfig robotConfig) {
        this.robotConfig = robotConfig;
        int[] top =
                robotConfig.getVisionProcessingConfig().getCrosshairPointTop();
        int[] bottom = robotConfig.getVisionProcessingConfig()
                .getCrosshairPointBottom();
        int[] left =
                robotConfig.getVisionProcessingConfig().getCrosshairPointLeft();
        int[] right = robotConfig.getVisionProcessingConfig()
                .getCrosshairPointRight();

        crosshairPointTop = new NIVision.Point(top[0], top[1]);
        crosshairPointBottom = new NIVision.Point(bottom[0], bottom[1]);
        crosshairPointLeft = new NIVision.Point(left[0], left[1]);
        crosshairPointRight = new NIVision.Point(right[0], right[1]);
    }

    public void initCamera() {
        try {
            cFrame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
            cSession = NIVision.IMAQdxOpenCamera(
                    robotConfig.getVisionProcessingConfig().getWebCamAxisName(),
                    NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            NIVision.IMAQdxConfigureGrab(cSession);
            CameraServer.getInstance().setQuality(50);
            NIVision.IMAQdxStartAcquisition(cSession);
        } catch (VisionException e) {
            e.printStackTrace();
        }
        // imageProcessor.initProcessing();
    }

    public void updateCameraServer() {
        NIVision.IMAQdxGrab(cSession, cFrame, 1);
        // drawCrosshairs(cFrame);
        // imageProcessor.processImage(cFrame);
        CameraServer.getInstance().setImage(cFrame);
    }

    public void drawCrosshairs(Image cFrame) {

        NIVision.imaqDrawLineOnImage(cFrame, cFrame, DrawMode.PAINT_VALUE,
                crosshairPointTop, crosshairPointBottom, 3);
        NIVision.imaqDrawLineOnImage(cFrame, cFrame, DrawMode.PAINT_VALUE,
                crosshairPointLeft, crosshairPointRight, 3);
    }

    public Image getCFrame() {
        return cFrame;
    }

    public int getCSession() {
        return cSession;
    }

}
