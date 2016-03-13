package org.usfirst.frc2175.driverstation;

import org.usfirst.frc2175.config.WiringConfig;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.vision.AxisCamera;

public class ImageHandler implements Runnable {
    static NIVision.Image image1;
    static NIVision.Image image2;
    static NIVision.Image imageProcessed;

    static AxisCamera axis1;
    static AxisCamera axis2;

    static CameraServer cserver;

    public ImageHandler() {
        AxisCamera axis1 = new AxisCamera(WiringConfig.getCamera1IP());
        AxisCamera axis2 = new AxisCamera(WiringConfig.getCamera2IP());

        CameraServer cserver = CameraServer.getInstance();
        cserver.setImage(image1);
        cserver.startAutomaticCapture();
    }

    @Override
    public void run() {
        axis1.getImage(image1);
        axis2.getImage(image2);
        NIVision.imaqAdd(imageProcessed, image1, image2);
        // TODO Somehow add images(image1,image2,imageProcessed);

    }

}
