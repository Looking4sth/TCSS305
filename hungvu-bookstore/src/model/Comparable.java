/*
 * TCSS 305 - Fall 2019
 * Assignment 1 - Online Bookstore Application
 * Assignment 2- Interface Extensions and Unit Tests
 * This is Comparable interface.
 */
package model;
/**
 * This interface is used to compare a given object to another one.
 * @author Hung Vu
 * @version 27 10 2019
 * @param <T> generic object for Comparable
 */
public interface Comparable<T> extends java.lang.Comparable<T> {
    /**
     * This method will compare an object, which call this method,
     * to another one.
     * @param theOther another object
     * @return an integer showing order of two objects
     */
    int compareTo(T theOther);
}
