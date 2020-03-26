/*
 * TCSS 305 - Fall 2019
 * Assignment 0 - Orientation
 * This class contains instance methods for object Hobbies
 */

/**
 * This is class used to create object Hobbies and and modify
 * its properties.
 * @author Hung Huu Vu
 * @version 02 10 2019
 *
 */
public class Hobbies
{
    /**
     * Instance field myHobbies contains author' s hobby.
     */
    private String myHobbies;
    /**
     * This is class constructor.
     * @param theHobbies author's hobby
     */
    public Hobbies(String theHobbies) 
    {
        this.myHobbies = theHobbies;
    }
    /**
     * This is class mutator.
     * @param theNewHobbies another hobby of author.
     */
    public void changeHobbies(String theNewHobbies) 
    {
        this.myHobbies = theNewHobbies; 
    }

    /**
     * This is override method which make an object printable.
     * @return author's hobby 
     */
    public String toString()
    {
        return this.myHobbies;
    }
}
