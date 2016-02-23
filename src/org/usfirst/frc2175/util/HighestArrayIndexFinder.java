package org.usfirst.frc2175.util;

/**
 *
 * @author Max Haland
 */
public class HighestArrayIndexFinder {
    public static final int NO_VALUES = -1;

    /**
     * Determines the index of the largest value in an array.
     *
     * @param array
     *            Array to use
     * @return Index of the largest value in the array
     */
    public int determineLargestArrayItemIndex(double[] array) {
        int numberOfItems = array.length;
        double largestIndividualItem = 0;
        int largestItemIndex = NO_VALUES;

        for (int i = 0; i < numberOfItems; i++) {
            if (array[i] > largestIndividualItem) {
                largestIndividualItem = array[i];
                largestItemIndex = i;
            }
        }

        return largestItemIndex;
    }
}