package no.ntnu.idata2302.lab02;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


/**
 * Represents a dynamic, stacked map. The stack map stores data using the Key-Value
 * principle from the map-interface, but only allows putting and popping from the 
 * last element of the map. Its capacity adjusts itself based on the rules defined
 * in <code>Sequence.class</code> which this class inherits from.
 * 
 * @version 0.1
 * @author Jorgfi
 * @since 12.09.2022
 * 
 * Future update: Make the map hash its content
 */
public class StackMap<K,V> extends Sequence implements Map<K,V>, Cloneable {

    // Representing the capacity of the map
    private int capacity;
    // Declares a two-dimensional Object array
    private Object[][] collection;


    /**
     * Creates a new empty instance 
     */
    public StackMap() {
        this.capacity = 100;
        this.collection = new Object[2][capacity];
    }

    /**
     * Ad a new key/value pair to the Map
     * @param key a unique identifier for the data to be stored
     * @param value the data to be stored
     * @return value if key/value pair was successfully stored
     */
    public V put(K key, V value) {
        if (!this.containsKey(key)) { 
            this.checkCapacity(1);
            this.collection[0][this.getLength()] = key;
            this.collection[1][this.getLength()] = value;
            this.incrementLength();
        }
        return value;
    }

    /**
     * Pop the last key/value pair from the stack
     * @throws ArrayIndexOutOfBoundsException when trying to pop items from
     *  empty list
     */
    public void pop() {
        if (this.getLength() < 1) {throw new ArrayIndexOutOfBoundsException();}
        this.checkCapacity(2);
        this.decrementLength();
        this.collection[0][this.getLength()] = 0;
        this.collection[1][this.getLength()] = 0;
    }
   
    /**
     * Updates collection-values when map-capacity is mutated
     */
    @Override
    @SuppressWarnings("unchecked")
    protected void updateCollection() {
        final K[] tempKey = (K[]) new Object[this.getCapacity()];
        final V[] tempVal = (V[]) new Object[this.getCapacity()];
        
        for (int i = 0; i < collection[0].length; i++) {
            if (collection[0][i] == null || collection[1][i] == null) {break;}
            tempKey[i] = (K) collection[0][i];
            tempVal[i] = (V) collection[1][i];
        }
        this.setCollection(tempKey, tempVal);
    }


    /**
     * Sets arrays of keys and values to the collection
     * @param newKeys array of keys
     * @param newVals array of values
     */
    private void setCollection(K[] newKeys, V[] newVals) {
        this.collection[0] = newKeys;
        this.collection[1] = newVals;
    }


    /**
     * Get values from the collection when presenting key
     * @param key identificator of the value stored
     * @return value if found, else null
     */
    @SuppressWarnings("unchecked")
    public V getValue(K key) {
        for (int i = 0; i < this.collection[0].length; i++) {
            if (key.equals(this.collection[0][i])) {
                return (V) this.collection[1][i];
            }
        }
        return null;
    }

    /**
     * Get key of first element in the map which corresponds to param
     * @param val value you want to find the key for
     * @return key if found, null otherwise
     */
    @SuppressWarnings("unchecked")
    public K getKey(V val) {
        for (int i = 0; i < this.collection[1].length; i++) {
            if (val.equals(this.collection[1][i])) {
                return (K) this.collection[0][i];
            }
        }
        return null;
    }

    /**
     * Cheks if the map contains some key
     * @param key the key to check
     * @return true if present, otherwise false
     */
    @Override
    public boolean containsKey(Object key) {
        if (this.collection[0].length == 0) {return false;}
        for (Object k : this.collection[0]) {
            if (key.equals(k)) {return true;}
        }
        return false;
    } 

    /**
     * Cheks if the map contains some valye
     * @param value the value to check
     * @return true if present, otherwise false
     */
    @SuppressWarnings("unchecked")
    public boolean containsVal(V value) {
        if (this.collection[1].length == 0) {return false;}
        for (V v : (V[]) this.collection[1]) {
            if (value.equals(v)) {return true;}
        }
        return false;
    }

    /**
     * Get map as string representation
     * @return string
     */
    public String getContentAsString() {
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        for(int i = 0; i < this.collection[0].length; i++) {
            sb.append("[" + this.collection[0][i] + ", ");
            sb.append(this.collection[1][i] + "], ");
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append("]");
        return sb.toString();
    }

    /**
     * Get size of the map
     * @return amount of pairs
     */
    @Override
    public int size() {
        return this.getLength();
    }

    /**
     * Check if map is empty
     * @return true if map is empty, otherwise false
     */
    @Override
    public boolean isEmpty() {
        return (this.getLength() != 0); 
        
    }

    /**
     * Check if map contains a given value
     * @param value the value to check
     * @return true if map contains the given value, otherwise false
     */
    @Override
    public boolean containsValue(Object value) {
       return containsValue(value);
    }

    /**
     * Get value from key
     * @param key identifier of the value
     * @return value associated with given key, otherwise null
     */
    @Override
    @SuppressWarnings("unchecked")
    public V get(Object key) {
        return this.getValue((K) key);
    }


    /**
     * Add map data to this map
     * @param m map containing key/value pairs
     */
    @Override
    @SuppressWarnings("unchecked")
    public void putAll(Map<? extends K, ? extends V> m) {
        K[] tempK = (K[]) m.keySet().toArray();
        V[] tempV = (V[]) m.values().toArray();

        for (int i = 0; i < tempK.length; i++) {
            this.put(tempK[i], tempV[i]);
        }
    }

    /**
     * Add list of values to map
     * @param K array of keys
     * @param V array of values
     * @throws IllegalArgumentException if K and V are not of same length
     */
    public void putAll(K[] K, V[] V) {
        if (K.length != V.length) {throw new IllegalArgumentException();}
        for (int i = 0; i < K.length; i++) {
            this.put(K[i], V[i]);
        }
    }


    /**
     * Clones stack
     * @return clone if not CloneNotSupportedException, otherwise new StackMap
     */
    @Override
    @SuppressWarnings(value={"unchecked"})
    public StackMap<K,V> clone() {
        StackMap<K,V> newStack = new StackMap<K,V>();
        newStack.putAll((K[]) collection[0], (V[]) collection[1]);
        return newStack;
    }

    /**
     * @deprecated is not relevant for current implementation
     */
    @Override
    @Deprecated
    public V remove(Object key) {
        // This is a stack based HashMap, this will therefore not be an option
        return null;
    }

    /**
     * @deprecated is not relevant for current implementation
     */
    @Override
    @Deprecated
    public void clear() { /* Not in use */ }

    /**
     * @deprecated is not relevant for current implementation
     */
    @Override
    @Deprecated
    @SuppressWarnings(value={"unchecked"})
    public Set<K> keySet() {return (Set<K>) new HashSet<V>();}

    /**
     * @deprecated is not relevant for current implementation
     */
    @Override
    @Deprecated
    public Collection<V> values() {return new ArrayList<V>();}

    /**
     * @deprecated is not relevant for current implementation
     */
    @Override
    @Deprecated
    public Set<Entry<K, V>> entrySet() {return new HashSet<Entry<K, V>>();}
}