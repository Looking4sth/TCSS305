/*
 * TCSS 305 - Fall 2019
 * Assignment 1 - Online Bookstore Application
 * Assignment 2- Interface Extensions and Unit Tests
 * This class creates an item for online bookstore.
 */

package model;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

/**
 * This class will create all properties of an item.
 * @author Hung Vu
 * @version 27 10 2019
 */
public final class Item implements Comparable<Item> {
    /**
     * Instance field myName contains item's name.
     */
    private final String myName;
    /**
     * Instance field myRegularPrice contains item's non-bulk price.
     */
    private final BigDecimal myRegularPrice;
    /**
     * Instance field myBulkPrice contains item's bulk price.
     */
    private final BigDecimal myBulkPrice; 
    /**
     * Instance field myBulkQty contains amount of item for a bulk.
     */
    private final int myBulkQty;
    /**
     * Instance field myBulkStatus show whether amount of item reaches myBulkQty.
     */
    private final boolean myBulkStatus;
    
    /**
     * Constructor for non-bulk item.
     * @param theName name of item
     * @param thePrice normal item's price
     */
    public Item(final String theName, final BigDecimal thePrice) {
        if (theName == null || thePrice == null) {
            throw new IllegalArgumentException();                
        }
        if (thePrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new IndexOutOfBoundsException("Price should be greater or equal 0.");
        }
        myName = theName;
        myRegularPrice = thePrice;
        myBulkPrice = BigDecimal.ZERO;
        myBulkQty = -1;
        myBulkStatus = false;        
    }

    /**
     * Constructor for bulk item.
     * @param theName name of item
     * @param thePrice normal item's price
     * @param theBulkQuantity amount for item to be considered a bulk
     * @param theBulkPrice bulk price of item
     */
    public Item(final String theName, final BigDecimal thePrice, final int theBulkQuantity,
                final BigDecimal theBulkPrice) {
        if (theName == null || thePrice == null || theBulkPrice == null) {
            throw new NullPointerException("Invalid item");                
        }
        if (thePrice.compareTo(BigDecimal.ZERO) == -1 
                        || theBulkPrice.compareTo(BigDecimal.ZERO) == -1) {
            throw new IndexOutOfBoundsException("Price/Quantity is greater or equal 0.");
        }
        if (theBulkQuantity < 0) {
            throw new IndexOutOfBoundsException("Quantity is greater or equal 0.");
        }
        myName = theName;
        myRegularPrice = thePrice;
        myBulkPrice = theBulkPrice;
        myBulkQty = theBulkQuantity;
        myBulkStatus = true;
    }

    /**
     * Return normal item's price.
     * @return normal item's price
     */
    public BigDecimal getPrice() {
        return myRegularPrice;
    }

    /**
     * Return amount of item to be considered as a bulk.
     * @return amount of item to be considered as a bulk
     */
    public int getBulkQuantity() {
        return myBulkQty;
    }

    /**
     * Return bulk price of item.
     * @return bulk price of item
     */
    public BigDecimal getBulkPrice() {
        return myBulkPrice;
    }

    /**
     * Return bulk status of item (true means it is bulk, false otherwise).
     * @return bulk status of item
     */
    public boolean isBulk() {
        return myBulkStatus;
    }


    @Override
    /**
     * This method will override toString method with required print statement.
     * General format: 'Item's name', 'US-currency price' ('bulk qty' for 'US-currency price')
     * The one in parentheses appears only when item has bulk quantity
     */
    public String toString() {
        final NumberFormat nf = NumberFormat.getCurrencyInstance(Locale.US);
        String display = myName + ", " + nf.format(myRegularPrice);
        if (myBulkStatus) {
            display += " (" + myBulkQty + " for " + nf.format(myBulkPrice) + ")";
        } 
        return display;
        
    }

    @Override
    /**
     * This method will compare two object together.
     * They are only equals when all given fields and class are the same, 
     * or they have the same reference. It will compare their reference, 
     * class type, item's name, item's normal price. When an object has bulk amount, 
     * it will also compare bulk price and bulk quantity.
     */
    public boolean equals(final Object theOther) {
        //Refactoring 01
        if (this == theOther) {
            return true;
        }
        if (theOther == null || this.getClass() != theOther.getClass()) {
            return false;
        } else {
            final Item otherItem = (Item) theOther;
            if (myBulkStatus) {
                return myName == otherItem.myName
                                && myRegularPrice.compareTo(otherItem.getPrice()) == 0
                                && myBulkPrice.compareTo(otherItem.getBulkPrice()) == 0
                                && myBulkQty == otherItem.getBulkQuantity(); 
            } else {
                return myName == otherItem.myName 
                                && myRegularPrice.compareTo(otherItem.getPrice()) == 0;
            } 
        }
    }

    @Override
    /**
     * This method will override default hashCode method 
     * and provide specific hash code for Item
     */
    public int hashCode() {
        final int hashResult;
        if (myBulkStatus) {
            hashResult = myName.hashCode() + myRegularPrice.hashCode()
                            + myBulkPrice.hashCode() + myBulkQty;
        } else {
            hashResult = myName.hashCode() + myRegularPrice.hashCode();
        }
        return hashResult;
    }
    
    /**
     * This method is implement of Comparable<T> interface. 
     * It will return an integer showing lexicographical order
     * between name of item which called the method and other item's name.
     * @param theOtherItem the other Item to be compared
     * @return an integer represents lexicographical order
     * between given item's name and other item's name.
     * return < 0 then item called the method is lexicographically first
     * return = 0 then both item are lexicographically equivalent
     * return > 0 then item called the method is lexicographically second
     */
    public int compareTo(final Item theOtherItem) {
        return myName.compareTo(theOtherItem.myName);
    }
}
