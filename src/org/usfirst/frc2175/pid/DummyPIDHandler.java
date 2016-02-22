package org.usfirst.frc2175.pid;

import java.util.logging.Logger;

import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;

public class DummyPIDHandler implements PIDSource, PIDOutput {
    private final Logger log = Logger.getLogger(getClass().getName());

    @Override
    public void pidWrite(double output) {
        log.warning("Still using DummyPIDHandler!");
    }

    @Override
    public void setPIDSourceType(PIDSourceType pidSource) {
    }

    @Override
    public PIDSourceType getPIDSourceType() {
        return null;
    }

    @Override
    public double pidGet() {
        log.warning("Still using DummyPIDHandler!");
        return 0;
    }
}
