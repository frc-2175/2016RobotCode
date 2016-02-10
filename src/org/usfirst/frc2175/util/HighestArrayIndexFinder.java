package org.usfirst.frc2175.util;

/**
 *
 * @author Max Haland
 *
 */

public class HighestArrayIndexFinder {

    /**
     * Determines the index of the largest value in an array.
     *
     * @param array
     *            Array to use
     * @return Index of the largest value in the array
     */

    public int determineLargestArrayItemIndex(double[] array)
            throws NullPointerException {

        int numberOfItems = array.length;
        double largestIndividualItem = 0;
        int largestItemIndex = 0;

        try {

            for (int i = 0; i < numberOfItems; i++) {
                if (array[i] > largestIndividualItem) {
                    largestIndividualItem = array[i];
                    largestItemIndex = i;
                }
            }

            if (numberOfItems == 0) {
                NullPointerException e =
                        new NullPointerException("Array is null!");
                throw e;
            }

        } catch (NullPointerException e) {
            largestItemIndex = -1;
            // TODO log the error message
            System.out.println(
                    "Error finding largest array member: " + e.getMessage());
        }

        return largestItemIndex;
    }
}