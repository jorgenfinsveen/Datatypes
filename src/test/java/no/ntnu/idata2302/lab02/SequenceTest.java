/*
 * This file is part of NTNU's IDATA2302 Lab02.
 *
 * Copyright (C) NTNU 2022
 * All rights reserved.
 *
 */
package no.ntnu.idata2302.lab02;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;


/**
 * Junit testing-class for testing the functionality of the sequence collection, and that the collections 
 * distinct mechanics works as expected.
 * 
 * @author Jorgfinsv
 * @version 06.09.2022
 */
public class SequenceTest {
    /**
     * Tests that a new sequence instance is empty at the time of creation.
     * @asserts that the sequence is empty
     * @fails if sequence is not empty
     */
    @Test
    public void emptySequenceHasLengthZero() {
        final Sequence sequence = new Sequence();
        assertEquals(0, sequence.getLength());
    }

    /**
     * Tests that the first value to be appended to the collection is located
     * at the zero-index.
     * @asserts that <code>sequence[0] == 3</code>
     * @fails if <code>sequence[0] != 3</code>
     */
    @Test
    public void firstValueIsAtIndexZero() {
        final Sequence sequence = new Sequence();
        sequence.insert(3);
        assertEquals(3, sequence.get(0));
    }

    /** 
     * Tests that a new value is stored in the sequence when using 
     * <code>Sequence.insert(int item)</code>
     * @asserts that the value is stored in the sequence
     * @fails if value is not in the sequence or length has not incremented
    */
    @Test
    public void sequenceSucessfullyAddsItem() {
        final Sequence sequence = new Sequence();
        sequence.insert(3);
        assertEquals(1, sequence.getLength());
        assertEquals(3, sequence.get(0));
    }

    /**
     * Tests that the capacity of the sequence rises when the length of the sequence
     * reaches the given capacity.
     * @asserts that the capacity doubles when the sequence length reaches the capacity.
     * @fails if the capacity does not double when current maximum length is reached
     */
    @Test
    public void sequenceRisesCapacity() {
        final Sequence sequence = new Sequence();
        assertEquals(100, sequence.getCapacity());
        for (int i = 0; i < 100; i++) {sequence.insert(i);}
        assertEquals(200, sequence.getCapacity());
    }

    /**
     * Tests that the last item of the sequence is successfully removed when using 
     * <code>Sequence.remove()</code>
     * @asserts that last item of the sequence is successfully removed
     * @fails if the value is not removed or the sequence length does not decrement
     */
    @Test
    public void sequenceSucessfullyRemovesItem() {
        final Sequence sequence = new Sequence();
        sequence.insert(5);
        sequence.remove();
        assertEquals(0, sequence.get(0));
        assertEquals(0, sequence.getLength());
    }

    /**
     * Tests that the sequence decreases its capacity by 50% if the capacity is 
     * 4 times greater or more than the length of the sequence.
     * @asserts that the sequence decreases its capacity by 50% 
     * @fails if the capacity does not decrease
     */
    @Test
    public void sequenceReducesCapacity() {
        final Sequence sequence = new Sequence();
        for (int i = 0; i < 100; i++) {sequence.insert(i);}
        for (int k = 0; k < 50; k++) {sequence.remove();}
        assertEquals(50, sequence.getLength());
        assertEquals(100, sequence.getCapacity());
    }

    /**
     * Tests that <code>Sequence.search(int item)</code> returns the
     * index of the first occurrence of the specified value.
     * @asserts that the index of the first occurrence of the specified value is returned
     * @fails if the index is not found or doesnt correspond to the value stored at the index
     */
    @Test
    public void returnsIndexOfExistingValueInSequence() {
        final Sequence sequence = new Sequence();
        for (int i = 0; i < 100; i++) {sequence.insert(i);}
        assertEquals(9, sequence.get(9));
        assertEquals(9, sequence.search(9));
    }

    /**
     * Tests that <code>Sequence.search(int item)</code> returns
     * <code>-1</code> if <code>item</code> does not exist in the sequence. 
     * @asserts that method returns <code>-1</code> if <code>item</code does not exist in the sequence
     * @fails if method does not return <code>-1</code>
     */
    @Test
    public void returnsInvalidValueWhenItemIsNotExistentInSequence() {
        final Sequence sequence = new Sequence();
        for (int i = 0; i < 100; i++) {sequence.insert(i);}
        assertEquals(-1, sequence.search(1000));
    }

    /**
     * Tests that <code>Sequence.remove()</code> throws an exception
     * when the sequence is empty.
     * @asserts <code>ArrayIndexOutOfBoundsException</code> is thrown
     * @fails if exception is not thrown
     */
    @Test
    public void throwsExceptionWhenTryingToRemoveItemFromEmptySequence() {
        final Sequence sequence = new Sequence();
        assertThrows(ArrayIndexOutOfBoundsException.class, sequence::remove);
    }
}
