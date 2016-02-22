package org.usfirst.frc2175.pid;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public abstract class PIDControllerComplete extends PIDController
        implements PIDSource, PIDOutput {
    private final Logger log = Logger.getLogger(getClass().getName());

    private PIDSourceType pidSourceType = PIDSourceType.kDisplacement;

    /**
     * How often, in seconds, the PID controller will update when enabled.
     * Hardcoded because there's no reason to configure this in a properties
     * file.
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

        this.m_pidInput = this;
        this.m_pidOutput = this;
    }

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

    @Override
    public void setPIDSourceType(PIDSourceType type) {
        pidSourceType = type;
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return pidSourceType;
    }
}
