/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab02;


/**
 * Represents a dynamic array class capable of storing values of type <code>int</code>. 
 * The array can store the given values by saving them to the collection in the order that
 * they are appended to the collection. The array will adjust its capacity based on the
 * number of values that are stored, which makes it more memory efficient.
 * 
 * @author Jorgfi
 * @version 06.09.2022
 */
public class Sequence {

    // Representing the capacity of the array
    private int capacity;
    // Representing the amount of values stored in the array
    private int length;
    // Declares an integer-array
    private int[] collection;


    /**
     * Creates an instance of Sequence
     */
    public Sequence() {
        this.capacity = 100;
        this.collection = new int[capacity];
    }


    /**
     * Returns item at spedified index position
     * @return int item i in sequence
     */
    public int get(int i) {return this.collection[i];}

    /**
     * Returns the length of the sequence
     * @return int length of sequence
     */
    public int getLength() { return this.length;}

    /**
     * Returns the capacity of the sequence
     * @return int capacity of sequence
     */
    public int getCapacity() {return this.capacity;}

    /**
     * Increments the length of the sequence by one
     */
    private void incrementLength() {this.length++;}

    /**
     * Decrements the length of the sequence by one, if length is greater than 0
     */
    private void decrementLength() {if (this.length > 0) {this.length--;}}


    /**
     * Fills a given int array with all of the elements in the sequence
     * @param intArray int[] to be filled with elements from sequence
     */
    private void updateIntArray(int[] intArray) {
        int i = 0;
        while (i < this.getLength()) {intArray[i] = this.get(i); i++;}
    }


    /**
     * Mutates the capacity of the sequence
     * @param newCapacity int new capacity value
     * @throws IllegalArgumentException if parameter <code>newCapacity</code> is negative or zero
     */
    private void setCapacity(int newCapacity) throws IllegalArgumentException {
        if (newCapacity <= 0) {throw new IllegalArgumentException("Must be positive");}
        this.capacity = newCapacity;
    }

    
    /**
     * Searches for a specific value in the sequence by checking each stored value
     * from index 0 to index n. Returns the index of the first element which matches the
     * parameter. Otherwise, returns -1.
     * @param item int value to search for
     * @return int index of first element that contains <code>item</code>, otherwise -1
     */
    public int search(int item) {
        int index = -1;
        for (int i = 0; i < this.getLength(); i++) {
            if (item == this.get(i)) {index = i; break;}
        }
        return index;
    }


    /**
     * Inserts an item at the last position in the sequence. If the
     * length of the sequence reaches its capacity, the capacity will
     * be expanded.
     * @param item int value to insert
     */
    public void insert(int item) {
        if (this.getCapacity() == (this.getLength()+1)) {this.setCapacity(this.getCapacity()*2);}
        this.incrementLength();
        final int[] intArray = new int[this.getCapacity()];
        this.updateIntArray(intArray);
        intArray[this.getLength()-1] = item;
        collection = intArray;
    }


    /**
     * Removes the last item from the sequence. It will also reduce the capacity of the
     * sequence if the length of the sequence is less than 1/4 of the capacity. 
     * @throws ArrayIndexOutOfBoundsException if the array already is empty
     */
    public void remove() throws ArrayIndexOutOfBoundsException {
        if (this.getLength() < 1) {throw new ArrayIndexOutOfBoundsException();}
        if ((this.getLength()-1)*4 <= this.getCapacity()) {this.setCapacity(this.getCapacity()/2);}
        final int[] intArray = new int[this.getCapacity()];
        this.updateIntArray(intArray);
        intArray[this.getLength()-1] = 0;
        this.decrementLength();
        collection = intArray;
    }
}
