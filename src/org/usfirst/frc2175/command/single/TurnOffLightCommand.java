package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.PhotonCannonSubsystem;

public class TurnOffLightCommand extends BaseCommand {
    private PhotonCannonSubsystem photonCannonSubsystem;

    public TurnOffLightCommand(RobotSubsystems robotSubsystems) {
        this.photonCannonSubsystem = robotSubsystems.getPhotonCannonSubsystem();
    }

    @Override
    protected void initialize() {
        photonCannonSubsystem.turnLightOff();
    }

    @Override
    protected void execute() {
        // No continuing actions needed
    }

    @Override
    protected boolean isFinished() {
        return true;
    }

    @Override
    protected void end() {

    }

    @Override
    protected void interrupted() {
        end();
    }

}
