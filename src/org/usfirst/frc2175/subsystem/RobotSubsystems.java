package org.usfirst.frc2175.subsystem;

import org.usfirst.frc2175.command.single.ArcadeDriveWithJoysticksCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.drivetrain.DrivetrainSubsystem;
import org.usfirst.frc2175.subsystem.intake.RollerbarIntakeSubsystem;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;

public class RobotSubsystems {
    private final RobotConfig robotConfig;
    private final DriverStation driverStation;

    // TODO add remaining subsystems
    private final RollerbarIntakeSubsystem rollerbarIntakeSubsystem;
    private final DrivetrainSubsystem drivetrainSubsystem;
    private final CatapultShooterSubsystem catapultShooterSubsystem;

    public RobotSubsystems(RobotConfig robotConfig,
            DriverStation driverStation) {
        this.robotConfig = robotConfig;
        this.driverStation = driverStation;

        rollerbarIntakeSubsystem = new RollerbarIntakeSubsystem(robotConfig);
        drivetrainSubsystem = new DrivetrainSubsystem(robotConfig);
        catapultShooterSubsystem = new CatapultShooterSubsystem(robotConfig);

        setDefaultCommands();
    }

    protected void setDefaultCommands() {
        drivetrainSubsystem.setDefaultCommand(
                new ArcadeDriveWithJoysticksCommand(driverStation, this));
    }

    public RollerbarIntakeSubsystem getRollerbarIntakeSubsystem() {
        return rollerbarIntakeSubsystem;
    }

    public DrivetrainSubsystem getDrivetrainSubsystem() {
        return drivetrainSubsystem;
    }

    public CatapultShooterSubsystem getCatapultShooterSubsystem() {
        return catapultShooterSubsystem;
    }
}
