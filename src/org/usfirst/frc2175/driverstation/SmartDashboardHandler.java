package org.usfirst.frc2175.driverstation;

import java.util.logging.Logger;

import org.usfirst.frc2175.command.autonomous.DamageChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.DamageLowBarAutonomous;
import org.usfirst.frc2175.command.autonomous.DamageMoatAutonomous;
import org.usfirst.frc2175.command.autonomous.DamagePortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.DamageRockWallAutonomous;
import org.usfirst.frc2175.command.autonomous.DamageRoughTerrainAutonomous;
import org.usfirst.frc2175.command.autonomous.DoNothingAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenLowBarAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenMoatAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenPortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenRampartsAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenRockwallAutonomous;
import org.usfirst.frc2175.command.autonomous.WeakenRoughTerrainAutonomous;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardHandler {
    private final Logger log = Logger.getLogger(getClass().getName());

    private SendableChooser autonChooser;
    private RobotSubsystems robotSubsystems;
    private RobotControllers robotControllers;

    public SmartDashboardHandler(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        log.info("Configuring class=" + getClass());

        this.robotSubsystems = robotSubsystems;
        this.robotControllers = robotControllers;

        makeAutonChooser();
    }

    private void makeAutonChooser() {
        autonChooser = new SendableChooser();

        // TODO add all of the auto routines as they are made
        autonChooser.addDefault("Do nothing", new DoNothingAutonomous());
        autonChooser.addObject("Weaken low bar",
                new WeakenLowBarAutonomous(robotSubsystems, robotControllers));
        autonChooser.addObject("Weaken cheval de frise",
                new WeakenChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken rough terrain",
                new WeakenRoughTerrainAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken moat",
                new WeakenMoatAutonomous(robotSubsystems, robotControllers));
        autonChooser.addObject("Weaken ramparts", new WeakenRampartsAutonomous(
                robotSubsystems, robotControllers));
        autonChooser.addObject("Weaken portcullis",
                new WeakenPortcullisAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Weaken rock wall", new WeakenRockwallAutonomous(
                robotSubsystems, robotControllers));
        autonChooser.addObject("Damage low bar",
                new DamageLowBarAutonomous(robotSubsystems, robotControllers));
        autonChooser.addObject("Damage cheval de frise",
                new DamageChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Damage rough terrain",
                new DamageRoughTerrainAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Damage moat",
                new DamageMoatAutonomous(robotSubsystems, robotControllers));
        autonChooser.addObject("Damage ramparts", new WeakenRampartsAutonomous(
                robotSubsystems, robotControllers));
        autonChooser.addObject("Damage portcullis",
                new DamagePortcullisAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Damage rock wall", new DamageRockWallAutonomous(
                robotSubsystems, robotControllers));

        SmartDashboard.putData("Autonomous Routine", autonChooser);
    }

    public CommandGroup getAutonCommand() {
        return (CommandGroup) autonChooser.getSelected();
    }
}
