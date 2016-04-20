package org.usfirst.frc2175.pid.motionprofiles;

public class MotionProfilePoint {
    private double time;
    private double velocity;

    public MotionProfilePoint(double time, double velocity) {
        this.time = time;
        this.velocity = velocity;
    }

    public double getTime() {
        return time;
    }

    public double getVelocity() {
        return velocity;
    }

    private double[] getPointAsArray() {
        double[] pointAsArray = { time, velocity };
        return pointAsArray;
    }

}