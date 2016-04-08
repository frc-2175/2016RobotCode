package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.camera.CameraSubsystem;

public class TurnOffLightCommand extends BaseCommand {
    private CameraSubsystem cameraSubsystem;

    public TurnOffLightCommand(RobotSubsystems robotSubsystems) {
        this.cameraSubsystem = robotSubsystems.getCameraSubsystem();
    }

    @Override
    protected void initialize() {
        cameraSubsystem.turnLightOff();
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
