package org.usfirst.frc2175.command.single;

import org.usfirst.frc2175.command.BaseCommand;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.camera.CameraSubsystem;

public class ToggleLightCommand extends BaseCommand {
    private CameraSubsystem cameraSubsystem;

    public ToggleLightCommand(RobotSubsystems robotSubsystems) {
        this.cameraSubsystem = robotSubsystems.getCameraSubsystem();
    }

    @Override
    protected void initialize() {
        cameraSubsystem.toggleLight();
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