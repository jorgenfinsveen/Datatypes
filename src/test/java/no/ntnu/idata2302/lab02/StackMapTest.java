package no.ntnu.idata2302.lab02;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;


/**
 * Junit testing-class for testing the functionality of the newHashMap collection, and that 
 * the collections distinct mechanics works as expected.
 * 
 * @author Jorgfinsv
 * @version 12.09.2022
 */
public class StackMapTest  {
    /**
     * Tests that a new StackMap instance is empty at the time of creation.
     * @asserts that the sequence is empty
     * @fails if sequence is not empty
     */
    @Test
    public void emptyStackMapHasLengthZero() {
        final StackMap<Object,Object> stackMap = new StackMap<>();
        assertEquals(0, stackMap.getLength());
    }

    /**
     * Tests that the first value to be appended to the collection is located
     * at the zero-index.
     * @asserts that <code>stackMap[0][0] == "k" && stackMap[1][0] == 18</code>
     * @fails if <code>stackMap[0][1] != "k" || stackMap[1][0] != 18</code>
     */
    @Test
    public void firstValueIsAtIndexZero() {
        final StackMap<Object,Object> stackMap = new StackMap<>();
        stackMap.put("k",18);
        assertEquals(18, stackMap.getValue("k"));
    }

    /** 
     * Tests that a new value is stored in the stackMap when using 
     * <code>StackMap.put(Object key, Object value)</code>>
     * @asserts that the key and value are stored in the stackMap
     * @fails if key and value is not in the stackMap or length has not incremented
    */
    @Test
    public void stackMapSucessfullyAddsItem() {
        final StackMap<Object,Object> stackMap = new StackMap<>();
        stackMap.put(3.14, "hello");
        assertEquals(1, stackMap.getLength());
        assertEquals("hello", stackMap.getValue(3.14));
    }

    /**
     * Tests that the capacity of the stackMap rises when the length of the stackMap
     * reaches the given capacity.
     * @asserts that the capacity doubles when the stackMap length reaches the capacity.
     * @fails if the capacity does not double when current maximum length is reached
     */
    @Test
    public void stackMapRisesCapacity() {
        final StackMap<Object,Object> stackMap = new StackMap<>();
        assertEquals(100, stackMap.getCapacity());
        for (int i = 1; i <= 100; i++) {stackMap.put(i,-i);}
        assertEquals(200, stackMap.getCapacity());
    }

    /**
     * Tests that the last key and value of the stackMap is successfully
     * removed when using <code>StackMap.pop()</code>
     * @asserts that last key and value of the stackMap is successfully removed
     * @fails if the key and value is not removed or the stackMap length does not decrement
     */
    @Test
    public void stackMapSucessfullyPopsItem() {
        final StackMap<Object,Object> stackMap = new StackMap<>();
        stackMap.put(false, 2.18);
        stackMap.pop();
        assertEquals(null, stackMap.getValue(false));
        assertEquals(null, stackMap.getKey(2.18));
        assertEquals(0, stackMap.getLength());
    }

    /**
     * Tests that the stackMap decreases its capacity by 50% if the capacity is 
     * 4 times greater or more than the length of the stackMap.
     * @asserts that the stackMap decreases its capacity by 50% 
     * @fails if the stackMap does not decrease
     */
    @Test
    public void stackMapReducesCapacity() {
        final StackMap<Object,Object> stackMap = new StackMap<>();
        for (int i = 0; i < 100; i++) {stackMap.put(i,"o");}
        for (int k = 0; k < 50; k++) {stackMap.pop();}
        assertEquals(50, stackMap.getLength());
        assertEquals(100, stackMap.getCapacity());
    }

    /**
     * Tests that the stackMap turns can return the
     * key of the first value matching input, or vice versa.
     * @asserts that the key/val of the first occurrence of the specified val/key is returned
     * @fails if the key/val is not found or doesnt correspond to the val/key
     */
    @Test
    public void searchingWithKeyOrValue() {
        final StackMap<Object,Object> stackMap = new StackMap<>();
        for (int i = 0; i < 100; i++) {stackMap.put(i,i+"");}
        assertEquals("9", stackMap.getValue(9));
        assertEquals(9, stackMap.getKey("9"));
    }

    /**
     * Tests that the stackMap returns returns null when 
     * key or val does not exist
     * @asserts that method returns <code>null</code> if <code>key || value == null</code does not exist in
     * @fails if method does not return <code>null</code>
     */
    @Test
    public void searchingWithNoneExistingKeyOrValueReturnsNull() {
        final StackMap<Object,Object> stackMap = new StackMap<>();
        for (int i = 0; i < 100; i++) {stackMap.put(i,i+"");}
        assertEquals(null, stackMap.getValue(1000));
        assertEquals(null, stackMap.getKey("1000"));
    }

    /**
     * Tests that <code>StackMap.pop()</code> throws an exception
     * when the stackMap is empty.
     * @asserts <code>ArrayIndexOutOfBoundsException</code> is thrown
     * @fails if exception is not thrown
     */
    @Test
    public void throwsExceptionWhenTryingToPopEmptyNewHashMap() {
        final StackMap<Object, Object> stackMap = new StackMap<Object, Object>();
        assertThrows(ArrayIndexOutOfBoundsException.class, stackMap::pop);
    }
}
