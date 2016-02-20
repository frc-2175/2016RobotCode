package org.usfirst.frc2175.driverstation;

import java.util.logging.Logger;

import org.usfirst.frc2175.command.autonomous.CrossChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossLowBarAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossMoatAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossPortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossRampartsAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossRockwallAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossRoughTerrainAutonomous;
import org.usfirst.frc2175.command.autonomous.DoNothingAutonomous;
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
        autonChooser.addObject("Cross low bar",
                new CrossLowBarAutonomous(robotSubsystems, robotControllers));
        autonChooser.addObject("Cross cheval de frise",
                new CrossChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Cross rough terrain",
                new CrossRoughTerrainAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Cross moat",
                new CrossMoatAutonomous(robotSubsystems, robotControllers));
        autonChooser.addObject("Cross ramparts",
                new CrossRampartsAutonomous(robotSubsystems, robotControllers));
        autonChooser.addObject("Cross portcullis",
                new CrossPortcullisAutonomous(robotSubsystems,
                        robotControllers));
        autonChooser.addObject("Cross rock wall",
                new CrossRockwallAutonomous(robotSubsystems, robotControllers));

        SmartDashboard.putData("Autonomous Routine", autonChooser);
    }

    public CommandGroup getAutonCommand() {
        return (CommandGroup) autonChooser.getSelected();
    }
}
