package org.usfirst.frc2175.subsystem.shooter;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;
import org.usfirst.frc2175.config.RobotConfig;

import edu.wpi.first.wpilibj.HLUsageReporting;
import mockit.Expectations;
import mockit.Mocked;

public class CatapultShooterSubsystemTest extends TestBase {
    @Mocked
    private RobotConfig robotConfig;

    @Mocked
    private HLUsageReporting hlUsageReporting;

    @Test
    public void testCatapultShooterSubsystem()
            throws IllegalArgumentException, IllegalAccessException {
        new Expectations() {
            {
                // when this is called
                robotConfig.getCatapultShooterConfig().getShortShotDelay();
                // then return this result
                result = 1.0;
            }
        };

        CatapultShooterSubsystem sut =
                new CatapultShooterSubsystem(robotConfig);

        assertInstanceVariablesNotNull(sut);
        assertDoublesNotZero(sut);
    }
}
