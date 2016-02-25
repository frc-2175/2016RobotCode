package org.usfirst.frc2175.subsystem.shooter;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;
import org.usfirst.frc2175.TestBase;

import mockit.Mock;
import mockit.MockUp;

@RunWith(Parameterized.class)
public class CatapultShooterSubsystemTest extends TestBase {
    @Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][] { { true, true, true, true, false },
                { true, true, true, false, false },
                { true, true, false, true, false },
                { true, true, false, false, false },
                { true, false, true, true, true },
                { true, false, true, false, false },
                { true, false, false, true, false },
                { true, false, false, false, false },
                { false, true, true, true, false },
                { false, true, true, false, true },
                { false, true, false, true, false },
                { false, true, false, false, false },
                { false, false, true, true, true },
                { false, false, true, false, true },
                { false, false, false, true, false },
                { false, false, false, false, false } });
    }

    @Parameter
    public boolean isCatapultDown;

    @Parameter(1)
    public boolean isCatapultUp;

    @Parameter(2)
    public boolean isIntakeOut;

    @Parameter(3)
    public boolean isSpeedCommandedGreaterThan0;

    @Parameter(4)
    public boolean isSpeedExpectedToBeSame;

    @Test
    public void testCatapultShooterMovementLogic() {

        double speedCommanded;
        if (isSpeedCommandedGreaterThan0) {
            speedCommanded = 1;
        } else {
            speedCommanded = -1;
        }

        double expectedSpeedSet;
        if (isSpeedExpectedToBeSame) {
            expectedSpeedSet = speedCommanded;
        } else {
            expectedSpeedSet = 0;
        }

        MockedCatapultShooterSubsystem mockedCatapultShooterSubsystem =
                new MockedCatapultShooterSubsystem(isCatapultDown, isCatapultUp,
                        isIntakeOut);

        CatapultShooterSubsystem sut =
                mockedCatapultShooterSubsystem.getMockInstance();
        double speedSet = sut.determineSafeCatapultShooterSpeed(speedCommanded);

        assertThat("Speed set incorrectly! Catapult destroyed!",
                speedSet == expectedSpeedSet);
    }

    public class MockedCatapultShooterSubsystem
            extends MockUp<CatapultShooterSubsystem> {
        private boolean isCatapultDown;
        private boolean isCatapultUp;
        private boolean isIntakeOut;

        public MockedCatapultShooterSubsystem(boolean isCatapultDown,
                boolean isCatapultUp, boolean isIntakeOut) {
            this.isCatapultDown = isCatapultDown;
            this.isCatapultUp = isCatapultUp;
            this.isIntakeOut = isIntakeOut;
        }

        @Mock
        public boolean isCatapultDown() {
            return isCatapultDown;
        }

        @Mock
        public boolean isCatapultUp() {
            return isCatapultUp;
        }

        @Mock
        public boolean isIntakeOut() {
            return isIntakeOut;
        }
    }
}
