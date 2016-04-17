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
    private SendableChooser typeOfAuton;
    private SendableChooser turnAngle;
    private SendableChooser outerworkType;
    private SendableChooser extraAuton;

    public SmartDashboardHandler(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        log.info("Configuring class=" + getClass());
        typeOfAuton = new AutonomousTraitFactory().findAutonType();
        turnAngle = new AutonomousTraitFactory().findAutonAngle();
        outerworkType = new AutonomousTraitFactory().findOuterworkType();
        extraAuton = new AutonomousTraitFactory().extraAutons(robotSubsystems,
                robotControllers, visionProcessing);
        SmartDashboard.putData("Auton Type", typeOfAuton);
        SmartDashboard.putData("Turn Angle", turnAngle);
        SmartDashboard.putData("Outerwork Type", outerworkType);
        SmartDashboard.putData("Extra Autons", extraAuton);
    }

    public CommandGroup getChosenAutonType() {
        CommandGroup chosenAutonType = (CommandGroup) typeOfAuton.getSelected();
        log.info("ChosenAutonType'" + typeOfAuton + "'");
        return chosenAutonType;
    }

    public CommandGroup getChosenAutonOuterwork() {
        CommandGroup chosenAutonOuterwork =
                (CommandGroup) outerworkType.getSelected();
        log.info("ChosenAutonOuterwork'" + typeOfAuton + "'");
        return chosenAutonOuterwork;
    }

    public CommandGroup getChosenAutonAngle() {
        CommandGroup chosenAutonAngle = (CommandGroup) turnAngle.getSelected();
        log.info("ChosenAutonAngle'" + turnAngle + "'");
        return chosenAutonAngle;
    }

    public CommandGroup getChosenExtraAuton() {
        CommandGroup chosenExtraAuton = (CommandGroup) extraAuton.getSelected();
        log.info("ChosenExtraAuton'" + extraAuton + "'");
        return chosenExtraAuton;
    }
}
