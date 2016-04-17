package org.usfirst.frc2175.util;

import java.util.ArrayList;
import java.util.List;

import edu.wpi.first.wpilibj.SpeedController;

/**
 * Treats a collection of {@link #edu.wpi.first.wpilibj.SpeedController speed
 * controllers} as one. Controllers can be added to the collection with
 * {@link #addController(SpeedController controller) addController}. You
 * probably shouldn't mix speed controller types, as they have different
 * physical properties and work slightly differently. From a code standpoint, it
 * will work, but it's just a bad idea.
 *
 * @author Max Haland
 */
public class SpeedControllerGroup implements SpeedController {
    private final List<SpeedController> controllers =
            new ArrayList<SpeedController>();

    /**
     * Constructor for any number of speed controllers
     *
     * @param controllers
     *            - list of controllers to add.
     */
    public SpeedControllerGroup(SpeedController... controllers) {
        for (SpeedController controller : controllers) {
            addController(controller);
        }
    }

    /**
     * Add a new speed controller to the group.
     *
     * @param controller
     *            controller to add.
     */
    public synchronized void addController(SpeedController controller) {
        controllers.add(controller);
    }

    /**
     * Gets the size of the controller group.
     *
     * @return the size of the controller group.
     */
    public synchronized int getControllerCount() {
        return controllers.size();
    }

    /**
     * Writes a value to each controller in the group.
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
     * @return overall speed of the controller group.
     * @throws IllegalStateException
     *             if the speed controller gets do not match.
     */
    @Override
    public synchronized double get() {
        double referenceSpeed = controllers.get(0).get();

        for (SpeedController controller : controllers) {
            double currentSpeed = controller.get();
            if (currentSpeed != referenceSpeed) {
                throw new IllegalStateException(
                        "Controller speeds not equal! Something has gone terribly wrong!");
            }
        }
        return referenceSpeed;
    }

    /**
     * Sets the output of each controller in the group.
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
     *            and 1.0.
     */
    @Override
    public synchronized void set(double speed) {
        for (SpeedController controller : controllers) {
            controller.set(speed);
        }
    }

    /**
     * Sets each controller in the group to be inverted.
     *
     * @param isInverted
     *            Whether the group should be inverted.
     */
    @Override
    public synchronized void setInverted(boolean isInverted) {
        for (SpeedController controller : controllers) {
            controller.setInverted(isInverted);
        }
    }

    /**
     * Checks whether the group is in the inverted state.
     *
     * @return boolean representing whether the group is inverted.
     * @throws IllegalStateException
     *             if the speed controller gets do not match.
     */
    @Override
    public synchronized boolean getInverted() {
        boolean referenceInverted = controllers.get(0).getInverted();

        for (SpeedController controller : controllers) {
            boolean currentInverted = controller.getInverted();
            if (currentInverted != referenceInverted) {
                throw new IllegalStateException(
                        "Controllers set to run different directions! Something has gone terribly wrong!");
            }
        }
        return referenceInverted;
    }

    /**
     * Disables all controllers in the group.
     */
    @Override
    public synchronized void disable() {
        for (SpeedController controller : controllers) {
            controller.disable();
        }
    }

    /**
     * Stops all motors in the group by setting their speed to 0.
     */
    @Override
    public synchronized void stopMotor() {
        for (SpeedController controller : controllers) {
            controller.set(0);
        }
    }

    public synchronized boolean areItemsInListEqual(
            List<? extends Object> list) {
        boolean areBooleansEqual = true;
        for (Object b : list) {
            for (Object b2 : list) {
                if (!b.equals(b2)) {
                    areBooleansEqual = false;
                }
            }
        }
        return areBooleansEqual;
    }

}
