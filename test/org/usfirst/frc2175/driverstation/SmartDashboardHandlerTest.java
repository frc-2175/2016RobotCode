package org.usfirst.frc2175.driverstation;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import mockit.Mocked;

public class SmartDashboardHandlerTest extends TestBase {
    @Mocked
    private RobotSubsystems robotSubsystems;

    @Mocked
    private RobotControllers robotControllers;

    @Mocked
    private VisionProcessing visionProcessing;

    @Test
    public void testConstructor() {
        SmartDashboardHandler sut = new SmartDashboardHandler(robotSubsystems,
                robotControllers, visionProcessing);
    }
}
