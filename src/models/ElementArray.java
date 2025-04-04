package models;

import java.util.Arrays;

public class ElementArray {
    private static final int DEFAULT_ELEMENT_ARRAY_SIZE = 50;
    Sort sorter;
    Element[] elements;

    public ElementArray() {
        generateNElements(DEFAULT_ELEMENT_ARRAY_SIZE);
        sorter = new Sort();
    }

    public Sort getSorter() {
        return sorter;
    }

    /**
     * This meth
     * @return
     */
    public Element[] getElements() {
        return elements;
    }

    /**
     * Creates and returns a new array of a given number of random elements.
     * @param n total number of elements in the array.
     * @return array of random elements.
     */
    public void generateNElements(int n) {
        elements = new Element[n];
        for (int i = 0; i < n; i++) {
            elements[i] = new Element((int)(Math.random()*530), 950/n);
        }
    }

    /**
     * This method calls other methods to begin sorting a copy of the elements array
     * @param sortType sorting algorithm to be used
     * @param speed adjusts speed/delay of visualizer
     */
    public void sort(String sortType, double speed) {
        sorter.setDelay(speed);
        sorter.startSort(sortType, Arrays.copyOf(elements, elements.length));
    }
}
