package org.usfirst.frc2175.util;

import java.util.ArrayList;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Treats a collection of speed controllers as one. Controllers can be added to
 * the collection with addController(SpeedController controller).
 *
 * @author Max Haland
 *
 */

public class SpeedControllerGroup implements SpeedController {

    private final ArrayList<SpeedController> controllers;

    public SpeedControllerGroup() {
        controllers = new ArrayList<SpeedController>();
    }

    /**
     * Add a new speed controller to the group.
     *
     * @param controller
     *            controller to add
     */
    public void addController(SpeedController controller) {
        controllers.add(controller);
    }

    /**
     * Gets the size of the controller group.
     * 
     * @return the size of the controller group
     */
    public int getSize() {
        return controllers.size();
    }

    /**
     * Writes a value to each controller in the group
     */
    @Override
    public synchronized void pidWrite(double output) {
        for (SpeedController controller : controllers) {
            controller.set(output);
        }
    }

    /**
     * Gets the speed of the controllers in the group. To do this, the speed of
     * each controller is checked against the speed of each other controller. If
     * any of the speeds do not match, and IllegalStateException is thrown.
     *
     * @return overall speed of the controller group
     */
    @Override
    public double get() {
        ArrayList<Double> speeds = new ArrayList<Double>();
        double overallSpeed;

        for (SpeedController controller : controllers) {
            Double controllerSpeed = controller.get();
            speeds.add(controllerSpeed);
        }

        for (Double speed : speeds) {
            for (Double speed2 : speeds) {
                if (!speed.equals(speed2)) {
                    throw new IllegalStateException(
                            "Controller gets not matching! Something has gone terribly wrong!");
                }
            }
        }
        overallSpeed = speeds.get(0).doubleValue();

        return overallSpeed;
    }

    /**
     * Sets the output of each controller in the group
     */
    @Override
    public synchronized void set(double speed, byte syncGroup) {
        for (SpeedController controller : controllers) {
            controller.set(speed, syncGroup);
        }
    }

    /**
     * Sets the speed of each controller in the group.
     *
     * @param speed
     *            Speed to set the group to. This value should be between -1.0
     *            and 1.0
     */
    @Override
    public synchronized void set(double speed) {
        for (SpeedController controller : controllers) {
            controller.set(speed);
        }
    }

    /**
     * Sets each controller in the group to be inverted
     *
     * @param isInverted
     *            Whether the group should be inverted
     */
    @Override
    public synchronized void setInverted(boolean isInverted) {
        for (SpeedController controller : controllers) {
            controller.setInverted(isInverted);
        }
    }

    @Override
    public boolean getInverted() {
        ArrayList<Boolean> values = new ArrayList<Boolean>();
        boolean returnValue;

        for (SpeedController controller : controllers) {
            Boolean value = controller.getInverted();
            values.add(value);
        }

        for (Boolean value : values) {
            for (Boolean value2 : values) {
                if (value != value2) {
                    throw new IllegalStateException(
                            "Controller gets not matching! Something has gone terribly wrong!");
                }
            }
        }
        returnValue = values.get(0).booleanValue();

        return returnValue;
    }

    /**
     * Disables all controllers in the group
     */
    @Override
    public synchronized void disable() {
        for (SpeedController controller : controllers) {
            controller.disable();
        }
    }

    /**
     * Stops all motors in the group by setting their speed to 0
     */
    @Override
    public synchronized void stopMotor() {
        for (SpeedController controller : controllers) {
            controller.set(0);
        }
    }

}
