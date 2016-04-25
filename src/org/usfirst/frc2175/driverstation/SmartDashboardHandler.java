package org.usfirst.frc2175.driverstation;

import java.util.logging.Logger;

import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class SmartDashboardHandler {
    private final Logger log = Logger.getLogger(getClass().getName());

    private final SendableChooser autonChooser;

    public SmartDashboardHandler(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        log.info("Configuring class=" + getClass());

        autonChooser = new AutonomousChooserFactory().make(robotSubsystems,
                robotControllers, visionProcessing);

        addAutonChooserToSmartDashboard();
    }

    public void addAutonChooserToSmartDashboard() {
        log.info("Adding auton chooser to SmartDashboard");
        SmartDashboard.putData("Autonomous Routine", autonChooser);
    }

    public CommandGroup getAutonCommand() {
        CommandGroup selectedAuton = (CommandGroup) autonChooser.getSelected();
        log.info("Selected auton='" + selectedAuton + "'");

        return selectedAuton;
    }
}
