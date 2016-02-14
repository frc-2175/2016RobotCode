package org.usfirst.frc2175.pid;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public abstract class PIDControllerComplete extends PIDController implements
        PIDSource, PIDOutput {
    /**
     * How often, in ms, the PID controller will update when enabled. Hardcoded
     * because there's no reason to run it any slower.
     */
    private static final int PID_PERIOD = 10;

    private PIDSourceType pidSource;

    public PIDControllerComplete() {
        this(1, 1, 1);
    }

    public PIDControllerComplete(double p, double i, double d) {
        super(p, i, d, new DummyPIDHandler(), new DummyPIDHandler(), PID_PERIOD);
        this.m_pidInput = this;
        this.m_pidOutput = this;
    }

    @Override
    @SuppressWarnings("all")
    public void setPIDSourceType(PIDSourceType pidSource) {
        this.pidSource = pidSource;
    }

    @Override
    @SuppressWarnings("all")
    public PIDSourceType getPIDSourceType() {
        return pidSource;
    }
}
