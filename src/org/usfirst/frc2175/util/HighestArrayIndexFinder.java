package org.usfirst.frc2175.util;

/**
 *
 * @author Max Haland
 */
public class HighestArrayIndexFinder {
    public static final int NO_VALUES = -1;

    /**
     * Determines the index of the largest value in an array. Make sure anything
     * that calls this method has some way to handle it if the array is zero
     * length.
     *
     * @param array
     *            Array to use
     * @return Index of the largest value in the array or
     *         HighestArrayIndexFinder.NO_VALUES for null and zero element
     *         arrays.
     */
    public int determineLargestArrayItemIndex(double[] array) {
        int largestItemIndex = NO_VALUES;
        if (array != null) {
            int numberOfItems = array.length;
            double largestIndividualItem = 0;

            for (int i = 0; i < numberOfItems; i++) {
                if (array[i] > largestIndividualItem) {
                    largestIndividualItem = array[i];
                    largestItemIndex = i;
                }
            }
        }

        return largestItemIndex;
    }
}