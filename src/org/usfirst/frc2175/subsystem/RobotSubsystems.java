package org.usfirst.frc2175.subsystem;

import org.usfirst.frc2175.DriverStation;
import org.usfirst.frc2175.command.single.ArcadeDriveWithJoysticks;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.subsystem.drivetrain.DrivetrainSubsystem;
import org.usfirst.frc2175.subsystem.intake.DreamIntakeSubsystem;

public class RobotSubsystems {
    private final RobotConfig robotConfig;
    private final DriverStation driverStation;

    // TODO add remaining subsystems
    private final DreamIntakeSubsystem dreamIntakeSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;

    public RobotSubsystems(RobotConfig robotConfig,
            DriverStation driverStation) {
        this.robotConfig = robotConfig;
        this.driverStation = driverStation;

        // TODO instantiate subsystems w/robotConfig, driverStation as needed
        dreamIntakeSubsystem = new DreamIntakeSubsystem();
        drivetrainSubsystem = new DrivetrainSubsystem();

        setDefaultCommands();
    }

    protected void setDefaultCommands() {
        // TODO set default commands for all subsystems
        drivetrainSubsystem.setDefaultCommand(
                new ArcadeDriveWithJoysticks(driverStation, this));
    }

    public DreamIntakeSubsystem getDreamIntakeSubsystem() {
        return dreamIntakeSubsystem;
    }

    public DrivetrainSubsystem getDrivetrainSubsystem() {
        return drivetrainSubsystem;
    }
}
