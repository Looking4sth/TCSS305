/*
 * TCSS 305 - Fall 2019
 * Assignment 1 - Online Bookstore Application
 * Assignment 2- Interface Extensions and Unit Tests
 * This class implements Comparator interface.
 */
package model;
/**
 * This class implements Comparator interface for Item object.
 * @author Hung Vu
 * @version 27 10 2019
 */
public class PriceComparison implements Comparator<Item> {
    /**
     * This method implements Comparator interface to compare price
     * of two different Item.
     * @param theFirstItem first item
     * @param theSecondItem second item
     * @return an integer indicate the order of two price. 
     * -1: first price < second price
     *  0: first price = second price
     *  1: first price > second price
     */
    public int compare(final Item theFirstItem, final Item theSecondItem) {
        return theFirstItem.getPrice().compareTo(theSecondItem.getPrice());
    }
}
