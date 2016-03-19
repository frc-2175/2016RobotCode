package org.usfirst.frc2175.subsystem.shooter;

import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

import mockit.Mock;
import mockit.MockUp;

public class CatapultShooterSubsystemTest extends TestBase {

    @Test
    public void testCatapultSafety_IntakeOut() {
        boolean shouldFire = true;
        MockCatapultShooterSubsystem mockCatapultShooterSubsystem =
                new MockCatapultShooterSubsystem(true);
        CatapultShooterSubsystem sut =
                mockCatapultShooterSubsystem.getMockInstance();

        boolean willFire = sut.isSafeToFireCatapult();

        assertThat("Catapult did not fire with intake out! No points for us!",
                willFire == shouldFire);
    }

    @Test
    public void testCatapultSaety_IntakeNotOut() {
        boolean shouldFire = false;
        MockCatapultShooterSubsystem mockCatapultShooterSubsystem =
                new MockCatapultShooterSubsystem(false);
        CatapultShooterSubsystem sut =
                mockCatapultShooterSubsystem.getMockInstance();

        boolean willFire = sut.isSafeToFireCatapult();

        assertThat("Catapult did not fire with intake out! No points for us!",
                willFire == shouldFire);
    }

    public static class MockCatapultShooterSubsystem
            extends MockUp<CatapultShooterSubsystem> {
        private boolean isIntakeOut;

        public MockCatapultShooterSubsystem(boolean isIntakeOut) {
            this.isIntakeOut = isIntakeOut;
        }

        @Mock
        public boolean isIntakeOut() {
            return isIntakeOut;
        }
    }
}
