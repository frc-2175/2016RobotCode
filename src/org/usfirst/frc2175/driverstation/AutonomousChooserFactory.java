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
import org.usfirst.frc2175.command.autonomous.TwoBallPortcullisShootSlightLeftAutonomous;
import org.usfirst.frc2175.command.autonomous.TwoBallPortcullisShootSlightRightAutonomous;
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
    private static int autonomousType;
    private static int autonomousOuterwork;
    private static int autonomousAngle;

    public static void decideOnAuton(
            SmartDashboardHandler smartDashboardHandler,
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        autonType = smartDashboardHandler.getChosenAutonType();
        autonOuterwork = smartDashboardHandler.getChosenAutonOuterwork();
        autonAngle = smartDashboardHandler.getChosenAutonAngle();
        extraAuton = smartDashboardHandler.getChosenExtraAuton();
        if (extraAuton.toString() == "None") {
            selectedAuton = figureOutAutonomous(robotSubsystems,
                    robotControllers, visionProcessing);
        } else {
            selectedAuton = extraAuton;
        }
    }

    private static void figureOutAutonTraits() {
        String[] typeOfAuton = { "Do Nothing", "Cross", "Cross Twice",
                "Cross And Shoot", "ShootTwice" };
        for (int i = 0; i < typeOfAuton.length; i++) {
            if (autonType.toString() == typeOfAuton[i]) {
                autonomousType = i;
            }
        }
        String[] outerworkForAuton =
                { "Static", "Portcullis", "ChevalDeFrise" };
        for (int i = 0; i < outerworkForAuton.length; i++) {
            if (autonOuterwork.toString() == outerworkForAuton[i]) {
                autonomousOuterwork = i;
            }
        }
        String[] angleForAuton = { "Forwards", "Slight Left", "Slight Right" };
        for (int i = 0; i < angleForAuton.length; i++) {
            if (autonAngle.toString() == angleForAuton[i]) {
                autonomousAngle = i;
            }
        }
    }

    public static CommandGroup figureOutAutonomous(
            RobotSubsystems robotSubsystems, RobotControllers robotControllers,
            VisionProcessing visionProcessing) {
        figureOutAutonTraits();
        // Make Cross Auton List
        CommandGroup[] crossAuton = {
                (new CrossStaticDefenseBlock(robotSubsystems, robotControllers,
                        true)),
                (new CrossPortcullisBlock(robotSubsystems, robotControllers,
                        false)),
                (new CrossChevalBlock(robotSubsystems, robotControllers,
                        false)) };
        // Make Cross Twice Auton List
        CommandGroup[] crossTwiceAuton = {
                (new CrossTwiceStaticDefenseAutonomous(robotSubsystems,
                        robotControllers)),
                (new CrossTwicePortcullisAutonomous(robotSubsystems,
                        robotControllers)),
                (new CrossTwiceChevalDeFriseAutonomous(robotSubsystems,
                        robotControllers)) };
        // Make Subsections for Cross And Shoot Auton List

        CommandGroup[] crossAndShootStaticAuton = {
                (new CrossStaticDefenseAndShootForwardAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)),
                (new CrossStaticDefenseAndShootSlightLeftAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)),
                (new CrossStaticDefenseAndShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)) };
        CommandGroup[] crossAndShootPortcullisAuton = {
                (new CrossPortcullisAndShootForwardAutonomous(robotSubsystems,
                        robotControllers, visionProcessing)),
                (new CrossPortcullisAndShootSlightLeftAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)),
                (new CrossPortcullisAndShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)) };
        CommandGroup[] crossAndShootChevalAuton = {
                (new CrossChevalDeFriseAndShootForwardAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)),
                (new CrossChevalDeFriseAndShootSlightLeftAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)),
                (new CrossChevalDeFriseAndShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)) };

        // Make Cross And Shoot Auton List
        CommandGroup[] crossAndShootAuton =
                { crossAndShootStaticAuton[autonomousAngle],
                        crossAndShootPortcullisAuton[autonomousAngle],
                        crossAndShootChevalAuton[autonomousAngle] };
        // Make Subsections for Shoot Twice Auton List
        CommandGroup[] shootTwiceStaticAuton = {
                (new TwoBallStaticShootForwardAutonomous(robotSubsystems,
                        robotControllers, visionProcessing)),
                (new TwoBallStaticShootSlightLeftAutonomous(robotSubsystems,
                        robotControllers, visionProcessing)),
                (new TwoBallStaticShootSlightRightAutonomous(robotSubsystems,
                        robotControllers, visionProcessing)) };
        CommandGroup[] shootTwicePortcullisAuton = {
                (new TwoBallPortcullisShootForwardAutonomous(robotSubsystems,
                        robotControllers, visionProcessing)),
                (new TwoBallPortcullisShootSlightLeftAutonomous(robotSubsystems,
                        robotControllers, visionProcessing)),
                (new TwoBallPortcullisShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)) };
        CommandGroup[] shootTwiceChevalAuton = {
                (new TwoBallChevalDeFriseShootForwardAutonomous(robotSubsystems,
                        robotControllers, visionProcessing)),
                (new TwoBallChevalDeFriseShootSlightLeftAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)),
                (new TwoBallChevalDeFriseShootSlightRightAutonomous(
                        robotSubsystems, robotControllers, visionProcessing)) };
        // Make Shoot Twice Auton List
        CommandGroup[] shootTwice = { shootTwiceStaticAuton[autonomousAngle],
                shootTwicePortcullisAuton[autonomousAngle],
                shootTwiceChevalAuton[autonomousAngle] };
        CommandGroup[] autonFromTraits = { (new DoNothingAutonomous()),
                (crossAuton[autonomousOuterwork]),
                (crossTwiceAuton[autonomousOuterwork]),
                (crossAndShootAuton[autonomousOuterwork]),
                (shootTwice[autonomousOuterwork]) };
        CommandGroup autonomous = autonFromTraits[autonomousType];
        return autonomous;
    }

    public static CommandGroup getSelectedAuton() {
        return selectedAuton;
    }
}
