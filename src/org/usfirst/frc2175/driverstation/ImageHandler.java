package org.usfirst.frc2175.driverstation;

import org.usfirst.frc2175.config.WiringConfig;

import com.ni.vision.NIVision;

import edu.wpi.first.wpilibj.vision.AxisCamera;

public class ImageHandler implements Runnable {
    private NIVision.Image image1;
    private NIVision.Image image2;
    private NIVision.Image imageProcessor;

    private AxisCamera axis1;
    private AxisCamera axis2;

    public ImageHandler(WiringConfig wiringConfig) {
        String camera1ip = wiringConfig.getCamera1IP();
        String camera2ip = wiringConfig.getCamera2IP();

        axis1 = new AxisCamera(camera1ip);
        axis2 = new AxisCamera(camera2ip);
    }

    @Override
    public void run() {
        // TODO Somehow add images(image1,image2,imageProcessed);

    }
}
