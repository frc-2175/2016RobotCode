package org.usfirst.frc2175.subsystem.intake;

import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameter;
import org.junit.runners.Parameterized.Parameters;

import mockit.Mock;
import mockit.MockUp;

@RunWith(Parameterized.class)
public class RollerbarIntakeSubsystemTest {

    @Parameters
    public static Collection<Object[]> input() {
        return Arrays.asList(new Object[][] { { true, false, true, true, true },
                { true, false, true, false, false },
                { true, false, false, true, true },
                { true, false, false, false, false },
                { false, true, true, true, false },
                { false, true, true, false, true },
                { false, true, false, true, false },
                { false, true, false, false, false },
                { false, false, true, true, true },
                { false, false, true, false, true },
                { false, false, false, true, true },
                { false, false, false, false, true } });
        // This array represents the states the intake-catapult system can be in
        // and how it should behave under those circumstances. It was determined
        // by writing truth tables on a whiteboard (something that should be
        // done for any similar system)
    }

    @Parameter
    public boolean isIntakeIn;

    @Parameter(1)
    public boolean isIntakeOut;

    @Parameter(2)
    public boolean isCatapultDown;

    @Parameter(3)
    public boolean isSpeedCommandedGreaterThan0;

    @Parameter(4)
    public boolean isSpeedExpectedToBeSame;

    @Test
    public void testIntakeMovementLogic() {
        testGenericIntakeMovementLogic(isIntakeIn, isIntakeOut, isCatapultDown,
                isSpeedCommandedGreaterThan0, isSpeedExpectedToBeSame);
    }

    public void testGenericIntakeMovementLogic(boolean isIntakeIn,
            boolean isIntakeOut, boolean isCatapultDown,
            boolean isSpeedCommandedGreaterThan0,
            boolean isSpeedExpectedToBeSame) {

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

        MockedRollerbarIntakeSubsystem mockedRollerbarIntakeSubsystem =
                new MockedRollerbarIntakeSubsystem(isIntakeIn, isIntakeOut,
                        isCatapultDown);

        RollerbarIntakeSubsystem sut =
                mockedRollerbarIntakeSubsystem.getMockInstance();
        double speedSet =
                sut.determineSafetyCheckedRollerbarLiftSpeed(speedCommanded);

        assertThat("Speed set incorrectly! Intake destroyed!",
                speedSet == expectedSpeedSet);

    }

    public static class MockedRollerbarIntakeSubsystem
            extends MockUp<RollerbarIntakeSubsystem> {

        private boolean isIntakeIn;
        private boolean isIntakeOut;
        private boolean isCatapultDown;

        public MockedRollerbarIntakeSubsystem(boolean isIntakeIn,
                boolean isIntakeOut, boolean isCatapultDown) {
            this.isIntakeIn = isIntakeIn;
            this.isIntakeOut = isIntakeOut;
            this.isCatapultDown = isCatapultDown;
        }

        @Mock
        public boolean isIntakeCompletelyIn() {
            return isIntakeIn;
        }

        @Mock
        public boolean isIntakeCompletelyOut() {
            return isIntakeOut;
        }

        @Mock
        public boolean isCatapultDown() {
            return isCatapultDown;
        }

    }
}
