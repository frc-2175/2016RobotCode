package org.usfirst.frc2175.pid.motionprofiles;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class MotionProfileTest extends TestBase {

    @Test(expected = IllegalArgumentException.class)
    public void testConstructWithIllegalDTime() {
        MotionProfile sut = new MotionProfile(0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMotionProfilePointAtTime_TooLarge() {
        MotionProfile sut = new MotionProfile(50);
        sut.addMotionProfilePoint(new MotionProfilePoint(0, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(50, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(100, 0));

        sut.getMotionProfilePointAtTime(150);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetMotionProfilePointAtTime_Negative() {
        MotionProfile sut = new MotionProfile(50);
        sut.addMotionProfilePoint(new MotionProfilePoint(0, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(50, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(100, 0));

        sut.getMotionProfilePointAtTime(-50);
    }

    @Test
    public void testAddMotionProfilePoint_LegalPoints() {
        MotionProfile sut = new MotionProfile(50);
        sut.addMotionProfilePoint(new MotionProfilePoint(0, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(50, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(100, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(150, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMotionProfilePoint_NonDTimeMultipleTimestamp() {
        MotionProfile sut = new MotionProfile(50);
        sut.addMotionProfilePoint(new MotionProfilePoint(40, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMotionProfilePoint_NegativeTimestamp() {
        MotionProfile sut = new MotionProfile(50);
        sut.addMotionProfilePoint(new MotionProfilePoint(-50, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddMotionProfilePoint_DuplicateTimestamp() {
        MotionProfile sut = new MotionProfile(50);
        sut.addMotionProfilePoint(new MotionProfilePoint(0, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(50, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(100, 0));
        sut.addMotionProfilePoint(new MotionProfilePoint(100, 0));
    }
}
