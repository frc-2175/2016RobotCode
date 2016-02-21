package org.usfirst.frc2175.subsystem.manipulator;

import org.junit.Ignore;
import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.config.RobotConfig;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.HLUsageReporting;
import mockit.Mocked;

public class ManipulatorSubsystemTest extends TestBase {
    @Mocked
    private CANTalon canTalon;

    @Mocked
    private DigitalInput digitalInput;

    @Mocked
    private RobotConfig robotConfig;

    @Mocked
    private HLUsageReporting hlUsageReporting;

    @Ignore("WiringConfig needs valid bootSpeed from MockWiringConfig")
    @Test
    public void testManipulatorSubsystem()
            throws IllegalArgumentException, IllegalAccessException {
        ManipulatorSubsystem sut = new ManipulatorSubsystem(robotConfig);
        assertDoublesNotZero(sut);
        assertInstanceVariablesNotNull(sut);
    }
}
