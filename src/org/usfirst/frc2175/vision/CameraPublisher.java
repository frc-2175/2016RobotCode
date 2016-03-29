package org.usfirst.frc2175.vision;

import org.usfirst.frc2175.config.RobotConfig;

import com.ni.vision.NIVision;
import com.ni.vision.NIVision.Image;
import com.ni.vision.VisionException;

import edu.wpi.first.wpilibj.CameraServer;

public class CameraPublisher {
    private RobotConfig robotConfig;

    private Image cFrame;
    private int cSession;

    public CameraPublisher(RobotConfig robotConfig) {
        this.robotConfig = robotConfig;
    }

    public void initCamera() {
        try {
            cFrame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
            cSession = NIVision.IMAQdxOpenCamera(
                    robotConfig.getVisionProcessingConfig().getWebCamName(),
                    NIVision.IMAQdxCameraControlMode.CameraControlModeController);
            NIVision.IMAQdxConfigureGrab(cSession);
            CameraServer.getInstance().setQuality(50);
            NIVision.IMAQdxStartAcquisition(cSession);

        } catch (VisionException e) {
            e.printStackTrace();
        }
    }

    public void updateCameraServer() {
        NIVision.IMAQdxGrab(cSession, cFrame, 1);
        CameraServer.getInstance().setImage(cFrame);
    }

    public Image getCFrame() {
        return cFrame;
    }

    public int getCSession() {
        return cSession;
    }

}
