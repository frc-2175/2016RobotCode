package org.usfirst.frc2175.controlloop;

import org.usfirst.frc2175.vision.CameraPublisher;

public class VisionProcessingLoop extends ControlLoop {
    private CameraPublisher cameraPublisher;

    public VisionProcessingLoop(CameraPublisher cameraPublisher) {
        this.cameraPublisher = cameraPublisher;
    }

    @Override
    protected long getPeriod() {
        return 50;
    }

    @Override
    protected void runTask() {
        cameraPublisher.updateCameraServer();
    }

}
