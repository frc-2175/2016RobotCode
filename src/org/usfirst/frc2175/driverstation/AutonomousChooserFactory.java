package org.usfirst.frc2175.driverstation;

import org.usfirst.frc2175.command.autonomous.CrossChevalDeFriseAndShootForwardAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossChevalDeFriseAndShootSlightLeftAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossChevalDeFriseAndShootSlightRightAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossPortcullisAndShootForwardAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossPortcullisAndShootSlightLeftAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossPortcullisAndShootSlightRightAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossStaticDefenseAndShootForwardAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossStaticDefenseAndShootSlightLeftAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossStaticDefenseAndShootSlightRightAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossTwiceChevalDeFriseAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossTwicePortcullisAutonomous;
import org.usfirst.frc2175.command.autonomous.CrossTwiceStaticDefenseAutonomous;
import org.usfirst.frc2175.command.autonomous.DoNothingAutonomous;
import org.usfirst.frc2175.command.autonomous.TwoBallChevalDeFriseShootForwardAutonomous;
import org.usfirst.frc2175.command.autonomous.TwoBallChevalDeFriseShootSlightLeftAutonomous;
import org.usfirst.frc2175.command.autonomous.TwoBallChevalDeFriseShootSlightRightAutonomous;
import org.usfirst.frc2175.command.autonomous.TwoBallPortcullisShootForwardAutonomous;
import org.usfirst.frc2175.command.autonomous.TwoBallStaticShootForwardAutonomous;
import org.usfirst.frc2175.command.autonomous.TwoBallStaticShootSlightLeftAutonomous;
import org.usfirst.frc2175.command.autonomous.TwoBallStaticShootSlightRightAutonomous;
import org.usfirst.frc2175.command.autonomous.block.CrossChevalBlock;
import org.usfirst.frc2175.command.autonomous.block.CrossPortcullisBlock;
import org.usfirst.frc2175.command.autonomous.block.CrossStaticDefenseBlock;
import org.usfirst.frc2175.pid.RobotControllers;
import org.usfirst.frc2175.subsystem.RobotSubsystems;
import org.usfirst.frc2175.subsystem.vision.VisionProcessing;

import edu.wpi.first.wpilibj.command.CommandGroup;

public class AutonomousChooserFactory {

    private static CommandGroup selectedAuton;
    private static CommandGroup autonType;
    private static CommandGroup autonOuterwork;
    private static CommandGroup autonAngle;
    private static CommandGroup extraAuton;

    public static void decideOnAuton(
            SmartDashboardHandler smartDashboardHandler,
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        autonType = smartDashboardHandler.getChosenAutonType();
        autonOuterwork = smartDashboardHandler.getChosenAutonOuterwork();
        autonAngle = smartDashboardHandler.getChosenAutonAngle();
        extraAuton = smartDashboardHandler.getChosenExtraAuton();
        if (extraAuton.toString() == "None") {
            switch (autonType.toString()) {
            case "Do Nothing":
                selectedAuton = new DoNothingAutonomous();
                break;
            case "Cross":
                figureOutCrossAuton(robotSubsystems, robotControllers);
                break;
            case "Cross Twice":
                figureOutCrossTwiceAuton(robotSubsystems, robotControllers);
                break;
            case "Cross And Shoot":
                figureOutCrossAndShootAuton(robotSubsystems, robotControllers,
                        visionProcessing);
                break;
            case "Shoot Twice":
                figureOutShootTwiceAuton(robotSubsystems, robotControllers,
                        visionProcessing);
                break;
            }
        } else {
            selectedAuton = extraAuton;
        }
    }

    private static void figureOutCrossAuton(RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        switch (autonOuterwork.toString()) {
        case "Static":
            selectedAuton = new CrossStaticDefenseBlock(robotSubsystems,
                    robotControllers, true);
            break;
        case "Portcullis":
            selectedAuton = new CrossPortcullisBlock(robotSubsystems,
                    robotControllers, false);
            break;
        case "ChevalDeFrise":
            selectedAuton = new CrossChevalBlock(robotSubsystems,
                    robotControllers, false);
            break;
        }
    }

    private static void figureOutCrossTwiceAuton(
            RobotSubsystems robotSubsystems,
            RobotControllers robotControllers) {
        switch (autonOuterwork.toString()) {
        case "Static":
            selectedAuton = new CrossTwiceStaticDefenseAutonomous(
                    robotSubsystems, robotControllers);
            break;
        case "Portcullis":
            selectedAuton = new CrossTwicePortcullisAutonomous(robotSubsystems,
                    robotControllers);
            break;
        case "ChevalDeFrise":
            selectedAuton = new CrossTwiceChevalDeFriseAutonomous(
                    robotSubsystems, robotControllers);
            break;
        }
    }

    private static void figureOutCrossAndShootAuton(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        switch (autonOuterwork.toString()) {
        case "Static":
            switch (autonAngle.toString()) {
            case "Forwards":
                selectedAuton = new CrossStaticDefenseAndShootForwardAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            case "Slight Left":
                selectedAuton =
                        new CrossStaticDefenseAndShootSlightLeftAutonomous(
                                robotSubsystems, robotControllers,
                                visionProcessing);
                break;
            case "Slight Right":
                selectedAuton =
                        new CrossStaticDefenseAndShootSlightRightAutonomous(
                                robotSubsystems, robotControllers,
                                visionProcessing);
                break;
            }
            break;
        case "Portcullis":
            switch (autonAngle.toString()) {
            case "Forwards":
                selectedAuton = new CrossPortcullisAndShootForwardAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            case "Slight Left":
                selectedAuton = new CrossPortcullisAndShootSlightLeftAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            case "Slight Right":
                selectedAuton =
                        new CrossPortcullisAndShootSlightRightAutonomous(
                                robotSubsystems, robotControllers,
                                visionProcessing);
                break;
            }
            break;
        case "ChevalDeFrise":
            switch (autonAngle.toString()) {
            case "Forwards":
                selectedAuton = new CrossChevalDeFriseAndShootForwardAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            case "Slight Left":
                selectedAuton =
                        new CrossChevalDeFriseAndShootSlightLeftAutonomous(
                                robotSubsystems, robotControllers,
                                visionProcessing);
                break;
            case "Slight Right":
                selectedAuton =
                        new CrossChevalDeFriseAndShootSlightRightAutonomous(
                                robotSubsystems, robotControllers,
                                visionProcessing);
                break;
            }
            break;
        }
    }

    private static void figureOutShootTwiceAuton(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        switch (autonOuterwork.toString()) {
        case "Static":
            switch (autonAngle.toString()) {
            case "Forwards":
                selectedAuton = new TwoBallStaticShootForwardAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            case "Slight Left":
                selectedAuton = new TwoBallStaticShootSlightLeftAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            case "Slight Right":
                selectedAuton = new TwoBallStaticShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            }
            break;
        case "Portcullis":
            switch (autonAngle.toString()) {
            case "Forwards":
                selectedAuton = new TwoBallPortcullisShootForwardAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            case "Slight Left":
                selectedAuton = new TwoBallStaticShootSlightLeftAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            case "Slight Right":
                selectedAuton = new TwoBallStaticShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            }
            break;
        case "ChevalDeFrise":
            switch (autonAngle.toString()) {
            case "Forwards":
                selectedAuton = new TwoBallChevalDeFriseShootForwardAutonomous(
                        robotSubsystems, robotControllers, visionProcessing);
                break;
            case "Slight Left":
                selectedAuton =
                        new TwoBallChevalDeFriseShootSlightLeftAutonomous(
                                robotSubsystems, robotControllers,
                                visionProcessing);
                break;
            case "Slight Right":
                selectedAuton =
                        new TwoBallChevalDeFriseShootSlightRightAutonomous(
                                robotSubsystems, robotControllers,
                                visionProcessing);
                break;
            }
            break;

        }
    }

    public static CommandGroup getSelectedAuton() {
        return selectedAuton;
    }
}
