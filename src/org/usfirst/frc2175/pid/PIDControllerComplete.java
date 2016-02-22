package org.usfirst.frc2175.pid;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public abstract class PIDControllerComplete extends PIDController {
    private final Logger log = Logger.getLogger(getClass().getName());

    /**
     * How often, in seconds, the PID controller will update when enabled.
     * Hardcoded because there's no reason to run it any slower.
     */
    private static final double PID_PERIOD = 0.01;

    public PIDControllerComplete() {
        this(0, 0, 0);
    }

    public PIDControllerComplete(double p, double i, double d) {
        super(p, i, d, new PIDSource() {
            @Override
            public double pidGet() {
                return 0;
            }

            @Override
            public void setPIDSourceType(PIDSourceType pidSource) {
            }

            @Override
            public PIDSourceType getPIDSourceType() {
                return PIDSourceType.kDisplacement;
            }
        }, new PIDOutput() {
            @Override
            public void pidWrite(double output) {
            }
        }, PID_PERIOD);

        PIDHandler handler = new PIDHandler();
        this.m_pidInput = handler;
        this.m_pidOutput = handler;
    }

    public abstract double pidGet();

    public abstract void pidWrite(double output);

    @Override
    public void enable() {
        super.enable();
        log.info("Enabled PID Controller = " + getClass().getName());
    }

    @Override
    public void disable() {
        super.disable();
        log.info("Disabled PID Controller = " + getClass().getName());
    }

    private class PIDHandler implements PIDSource, PIDOutput {
        private final Logger log = Logger.getLogger(getClass().getName());

        private PIDSourceType pidSource = PIDSourceType.kDisplacement;

        @Override
        public void setPIDSourceType(PIDSourceType pidSource) {
            this.pidSource = pidSource;
        }

        @Override
        public PIDSourceType getPIDSourceType() {
            return pidSource;
        }

        @Override
        public double pidGet() {
            double value = PIDControllerComplete.this.pidGet();
            log.info("Got value of " + value + " for pidGet()");
            return value;
        }

        @Override
        public void pidWrite(double output) {
            log.info("Wrote value of " + output + " for pidWrite()");
            PIDControllerComplete.this.pidWrite(output);
        }
    }
}
