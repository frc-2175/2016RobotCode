package org.usfirst.frc2175.util;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.usfirst.frc2175.TestBase;

public class SpeedControllerGroupTest extends TestBase {

    private final SpeedControllerGroup sut = new SpeedControllerGroup();

    @Test
    public void testAreDoublesInListEqual() {
        List<Double> testList = new ArrayList<Double>();
        testList.add(new Double(1));
        testList.add(new Double(1));
        testList.add(new Double(1));
        testList.add(new Double(1));

        assertThat("List was equal! Method performed incorrectly",
                sut.areDoublesInListEqual(testList), is(true));

        testList.add(new Double(0));

        assertThat("List was notequal! Method performed incorrectly",
                sut.areDoublesInListEqual(testList), is(false));
    }

    @Test
    public void testAreBooleansInListEqual() {
        List<Boolean> testList = new ArrayList<Boolean>();
        testList.add(new Boolean(true));
        testList.add(new Boolean(true));
        testList.add(new Boolean(true));
        testList.add(new Boolean(true));

        assertThat("List was equal! Method performed incorrectly",
                sut.areBooleansInListEqual(testList), is(true));

        testList.add(new Boolean(false));

        assertThat("List was not equal! Method performed incorrectly",
                sut.areBooleansInListEqual(testList), is(false));

    }

}
