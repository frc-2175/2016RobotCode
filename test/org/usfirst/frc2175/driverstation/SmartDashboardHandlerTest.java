package org.usfirst.frc2175.driverstation;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import mockit.Mocked;

public class SmartDashboardHandlerTest extends TestBase {
    @Mocked
    private RobotSubsystems robotSubsystems;

    @Mocked
    private RobotControllers robotControllers;

    @Test
    public void testConstructor() {
        SmartDashboardHandler sut =
                new SmartDashboardHandler(robotSubsystems, robotControllers);
    }
}
