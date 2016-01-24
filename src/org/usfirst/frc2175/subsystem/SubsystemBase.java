package org.usfirst.frc2175.subsystem;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Subsystem;

public class SubsystemBase extends Subsystem {

    @Override
    protected void initDefaultCommand() {
        // No implementation
    }

    @Override
    public void setDefaultCommand(Command command) {
        super.setDefaultCommand(command);
    }

}
