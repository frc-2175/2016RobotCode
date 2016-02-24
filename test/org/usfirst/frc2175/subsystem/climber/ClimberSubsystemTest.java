package org.usfirst.frc2175.subsystem.climber;

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

public class ClimberSubsystemTest extends TestBase {
    @Parameters
    public static Collection<Object[]> input() {
        return Arrays
                .asList(new Object[][] {
                        { true, true, true, false }, { true, true, false,
                                true },
                        { true, false, true, false },
                        { true, false, false, false },
                        { false, true, true, true },
                        { false, true, false, true },
                        { false, false, true, false },
                        { false, false, false, false } });
    }

    @Parameter
    public boolean isClimberUp;

    @Parameter(1)
    public boolean isClimberExtended;

    @Parameter(2)
    public boolean isSpeedCommandedGreaterThan0;

    @Parameter(3)
    public boolean isSpeedExpectedToBeSame;

    @Test
    public void testClimberExtensionLogic() {
        testGenericClimberExtensionLogic(isClimberUp, isClimberExtended,
                isSpeedCommandedGreaterThan0, isSpeedExpectedToBeSame);
    }

    public void testGenericClimberExtensionLogic(boolean isClimberExtended,
            boolean isClimberUp, boolean isSpeedCommandedGreaterThan0,
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

        MockedClimberSubsystem mockedClimberSubsystem =
                new MockedClimberSubsystem(isClimberUp, isClimberExtended);

        ClimberSubsystem sut = mockedClimberSubsystem.getMockInstance();
        double speedSet =
                sut.determineSafetyCheckedClimberExtendSpeed(speedCommanded);

        assertThat("Speed set incorrectly! Climber destroyed!",
                speedSet == expectedSpeedSet);
    }

    public class MockedClimberSubsystem extends MockUp<ClimberSubsystem> {
        private boolean isClimberUp;
        private boolean isClimberExtended;

        public MockedClimberSubsystem(boolean isClimberUp,
                boolean isClimberExtended) {
            this.isClimberUp = isClimberUp;
            this.isClimberExtended = isClimberExtended;
        }

        @Mock
        public boolean isClimberUp() {
            return isClimberUp;
        }

        @Mock
        public boolean isClimberExtended() {
            return isClimberExtended;
        }

    }
}
