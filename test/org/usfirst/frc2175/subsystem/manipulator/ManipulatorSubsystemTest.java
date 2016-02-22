package org.usfirst.frc2175.subsystem.manipulator;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.config.RobotConfig;

import edu.wpi.first.wpilibj.CANTalon;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.HLUsageReporting;
import mockit.Expectations;
import mockit.Mocked;

public class ManipulatorSubsystemTest extends TestBase {
    @Mocked
    private RobotConfig robotConfig;

    @Mocked
    private CANTalon canTalon;

    @Mocked
    private DigitalInput digitalInput;

    @Mocked
    private HLUsageReporting hlUsageReporting;

    @Test
    public void testManipulatorSubsystem()
            throws IllegalArgumentException, IllegalAccessException {
        new Expectations() {
            {
                // when this is called
                robotConfig.getManipulatorConfig().getBootSpeed();
                // then return this result
                result = 1.0;
            }
        };

        ManipulatorSubsystem sut = new ManipulatorSubsystem(robotConfig);

        assertDoublesNotZero(sut);
        assertInstanceVariablesNotNull(sut);
    }
}
