package org.usfirst.frc2175.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

import edu.wpi.first.wpilibj.SpeedController;

public class SpeedControllerGroupTest extends TestBase {
    @Test(expected = IllegalArgumentException.class)
    public void testConstructor_NoControllers_Exception() {
        new SpeedControllerGroup();
    }

    @Test
    public void testGet_SameSpeed_Success() {
        double speed = 2;
        final TestSpeedController controller1 = new TestSpeedController(speed);
        final TestSpeedController controller2 = new TestSpeedController(speed);
        final SpeedControllerGroup sut =
                new SpeedControllerGroup(controller1, controller2);
        double actualSpeed = sut.get();

        assertThat("Speeds do not match.", actualSpeed, equalTo(speed));
    }

    @Test(expected = IllegalStateException.class)
    public void testGet_DiffSpeed_Exception() {
        double speed1 = 1;
        double speed2 = 2;
        final TestSpeedController controller1 = new TestSpeedController(speed1);
        final TestSpeedController controller2 = new TestSpeedController(speed2);
        final SpeedControllerGroup sut =
                new SpeedControllerGroup(controller1, controller2);
        sut.get();
    }

    @Test
    public void testGetInverted_SameInverted_Success() {
        boolean inverted = true;
        final TestSpeedController controller1 =
                new TestSpeedController(inverted);
        final TestSpeedController controller2 =
                new TestSpeedController(inverted);
        final SpeedControllerGroup sut =
                new SpeedControllerGroup(controller1, controller2);
        boolean actualInverted = sut.getInverted();

        assertThat("Inverteds do not match.", actualInverted,
                equalTo(actualInverted));
    }

    @Test(expected = IllegalStateException.class)
    public void testGetInverted_DiffInverted_Exception() {
        boolean inverted1 = true;
        boolean inverted2 = false;
        final TestSpeedController controller1 =
                new TestSpeedController(inverted1);
        final TestSpeedController controller2 =
                new TestSpeedController(inverted2);
        final SpeedControllerGroup sut =
                new SpeedControllerGroup(controller1, controller2);
        sut.getInverted();
    }

    public static class TestSpeedController implements SpeedController {
        private double speed;

        private boolean inverted;

        public TestSpeedController(double speed) {
            this.speed = speed;
        }

        public TestSpeedController(boolean inverted) {
            this.inverted = inverted;
        }

        @Override
        public void pidWrite(double output) {
        }

        @Override
        public double get() {
            return speed;
        }

        @Override
        public void set(double speed, byte syncGroup) {
            this.speed = speed;
        }

        @Override
        public void set(double speed) {
            this.speed = speed;
        }

        @Override
        public void setInverted(boolean isInverted) {
            this.inverted = isInverted;
        }

        @Override
        public boolean getInverted() {
            return inverted;
        }

        @Override
        public void disable() {
        }

        @Override
        public void stopMotor() {
        }
    }
}
