package org.usfirst.frc2175.util;

public class HighestArrayIndexFinder {

    public int determineLargestArrayItemIndex(double[] array) {
        int numberOfItems = array.length;

        double largestIndividualItem = 0;
        int largestItemIndex = 0;

        for (int i = 0; i < numberOfItems; i++) {
            if (array[i] > largestIndividualItem) {
                largestIndividualItem = array[i];
                largestItemIndex = i;
            }
        }

        return largestItemIndex;
    }
}
