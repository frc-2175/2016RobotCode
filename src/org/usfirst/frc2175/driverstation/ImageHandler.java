package org.usfirst.frc2175.driverstation;

import org.usfirst.frc2175.config.WiringConfig;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.vision.AxisCamera;

public class ImageHandler implements Runnable {
    static NIVision.Image image1;
    static NIVision.Image image2;
    static NIVision.Image imageProcessor;

    static AxisCamera axis1;
    static AxisCamera axis2;

    public ImageHandler() {
        AxisCamera axis1 = new AxisCamera(WiringConfig.getCamera1IP());
        AxisCamera axis2 = new AxisCamera(WiringConfig.getCamera2IP());
    }

    @Override
    public void run() {
        // TODO Somehow add images(image1,image2,imageProcessed);

    }

}
