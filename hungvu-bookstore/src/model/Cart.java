/*
 * TCSS 305 - Fall 2019
 * Assignment 1 - Online Bookstore Application
 * Assignment 2- Interface Extensions and Unit Tests
 * This class creates a cart for online bookstore.
 */

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Objects;

/**
 * This class will put item in cart and compute its
 * price based on membership status.
 * @author Hung Vu
 * @version 27 10 2019
 */

public class Cart {
    /**
     * Instance field myCartItem is where item is stored.
     */
    private final ArrayList<ItemOrder> myCartItem;
    /**
     * Instance field myMembershipStatus will store membership information.
     */
    private boolean myMembershipStatus;
    /**
     * This is class default constructor.
     */
    public Cart() {
        myCartItem = new ArrayList<ItemOrder>();
    }
    
    /**
     * This method will add item into cart and replace
     * the equivalent item with the new order (re-calculate price).
     * @param theOrder the new order that is going to be put into cart
     */
    public void add(final ItemOrder theOrder) {
        if (theOrder == null) {
            throw new NullPointerException("Invalid order.");
        }
        for (ItemOrder i : myCartItem) {
            if (i.getItem().equals(theOrder.getItem())) {
                myCartItem.set(myCartItem.indexOf(i), theOrder);
                return;
            }
        }
        myCartItem.add(theOrder);
    }

    /**
     * Command for setting membership status.
     * @param theMembership membership status of customer (true is yes, false otherwise)
     */
    public void setMembership(final boolean theMembership) {
        myMembershipStatus = theMembership;
    }

    /**
     * This method will calculate total price of item in cart based on membership status.
     * @return total price
     */
    public BigDecimal calculateTotal() {
        BigDecimal finalPrice = BigDecimal.ZERO;
        
        for (ItemOrder i : myCartItem) {
            final BigDecimal regularPrice = i.getItem().getPrice();
            final BigDecimal regularQty = BigDecimal.valueOf(i.getQuantity());
            final BigDecimal bulkPrice = i.getItem().getBulkPrice();
            final BigDecimal bulkQty = BigDecimal.valueOf(i.getItem().getBulkQuantity());
            /**
             * The statement will check whether customer has membership and item has
             * bulk price at the same time or not.
             */
            if (myMembershipStatus && i.getItem().isBulk()) {
                final BigDecimal bulkNumber = regularQty.divide(bulkQty, RoundingMode.DOWN);
                final BigDecimal remainQty = regularQty.subtract(bulkQty.multiply(bulkNumber));
                finalPrice = finalPrice.add(bulkPrice.multiply(bulkNumber));
                finalPrice = finalPrice.add(regularPrice.multiply(remainQty));
            } else {
                finalPrice = finalPrice.add(regularPrice.multiply(regularQty));
            }
            
        }
        return finalPrice.setScale(2, RoundingMode.HALF_EVEN);
    }
    
    /**
     * Command to empty the cart.
     */
    public void clear() {
        myCartItem.clear();
    }
    
    /**
     * Return size of the cart.
     * @return size of the cart
     */
    public int getCartSize() {
        return myCartItem.size();
    }


    @Override
    /**
     * This method will override toString method with required print statement.
     * General format: My cart: ['Item 1's name', 'US-currency price', qty: 'Item's quantity'
     * , 'Item 2's name, ...)
     */
    public String toString() {
        return "My cart: " + myCartItem.toString();
    }
    @Override
    /**
     * This method will override default equals method. It will compare two objects together.
     * They are only equals when all given fields are the same, 
     * or they have the same reference. It will compare their reference, 
     * class type, Cart's order and membership status.
     */
    public boolean equals(final Object theOther) {
        if (this == theOther) {
            return true;
        }
        if (theOther == null || !(theOther instanceof Cart)) {
            return false;
        }  else {
            final Cart otherOrder = (Cart) theOther;
            return myCartItem.equals(otherOrder.myCartItem) 
                            && myMembershipStatus == otherOrder.myMembershipStatus; 
        }
    }
    @Override
    /**
     * This method will override default hashCode method 
     * and provide specific hash code for Cart
     */
    public int hashCode() {
        return Objects.hash(myCartItem, myMembershipStatus);
    }
}
