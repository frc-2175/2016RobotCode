package org.usfirst.frc2175.subsystem;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;

import edu.wpi.first.wpilibj.HLUsageReporting;
import mockit.Mocked;

public class RobotSubsystemsTest extends TestBase {
    @Mocked
    private RobotConfig robotConfig;

    @Mocked
    private DriverStation driverStation;

    @Mocked
    private HLUsageReporting hLUsageReporting;

    @Mocked
    private PowertrainSubsystem powertrainSubsystem;

    @Test
    public void testRobotSubsystems()
            throws IllegalArgumentException, IllegalAccessException {
        RobotSubsystems sut = new RobotSubsystems(robotConfig, driverStation);

        assertInstanceVariablesNotNull(sut);
    }
}
