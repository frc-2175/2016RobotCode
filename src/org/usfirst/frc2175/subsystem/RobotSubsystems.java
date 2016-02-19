package org.usfirst.frc2175.subsystem;

import org.usfirst.frc2175.command.single.ArcadeDriveWithJoysticksCommand;
import org.usfirst.frc2175.config.RobotConfig;
import org.usfirst.frc2175.driverstation.DriverStation;
import org.usfirst.frc2175.subsystem.intake.RollerbarIntakeSubsystem;
import org.usfirst.frc2175.subsystem.manipulator.ManipulatorSubsystem;
import org.usfirst.frc2175.subsystem.powertrain.PowertrainSubsystem;
import org.usfirst.frc2175.subsystem.shooter.CatapultShooterSubsystem;

public class RobotSubsystems {
    private final RobotConfig robotConfig;
    private final DriverStation driverStation;

    // TODO add remaining subsystems
    private final RollerbarIntakeSubsystem rollerbarIntakeSubsystem;
    private final PowertrainSubsystem powertrainSubsystem;
    private final CatapultShooterSubsystem catapultShooterSubsystem;
    private final ManipulatorSubsystem manipulatorSubsystem;

    public RobotSubsystems(RobotConfig robotConfig,
            DriverStation driverStation) {
        this.robotConfig = robotConfig;
        this.driverStation = driverStation;

        rollerbarIntakeSubsystem = new RollerbarIntakeSubsystem(robotConfig);
        powertrainSubsystem = new PowertrainSubsystem(robotConfig);
        catapultShooterSubsystem = new CatapultShooterSubsystem(robotConfig);
        manipulatorSubsystem = new ManipulatorSubsystem(robotConfig);

        setDefaultCommands();
    }

    public ManipulatorSubsystem getManipulatorSubsystem() {
        return manipulatorSubsystem;
    }

    protected void setDefaultCommands() {
        powertrainSubsystem.setDefaultCommand(
                new ArcadeDriveWithJoysticksCommand(driverStation, this));
    }

    public RollerbarIntakeSubsystem getRollerbarIntakeSubsystem() {
        return rollerbarIntakeSubsystem;
    }

    public PowertrainSubsystem getPowertrainSubsystem() {
        return powertrainSubsystem;
    }

    public CatapultShooterSubsystem getCatapultShooterSubsystem() {
        return catapultShooterSubsystem;
    }
}
