/*
 * Course: CS2852-071
 * Spring 2021
 * Lab 8 - Morse Code Encoder
 * Name: Derek Richards
 * Created: 5/1/2021
 */
package richardsde;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

/**
 * A class that uses hashing and chaining to implement the Map ADT.
 * @param <K> The variable type used for keys.
 * @param <V> The variable type used for values stored in the maps.
 */
public class HashMap<K, V> implements Map<K, V> {
    private LinkedList<HashMapEntry<K, V>>[] table;
    private static final int START_CAPACITY = 1024;
    private static final double LOAD_THRESHOLD = 3.0;
    private int numKeys;

    /**
     * Constructor for the HashMap class.
     */
    public HashMap(){
        table = new LinkedList[START_CAPACITY];
    }

    private static class HashMapEntry<K, V> {
        private final K key;
        private V value;

        public HashMapEntry(K key, V value){
            this.key = key;
            this.value = value;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public V setValue(V val) {
            V oldVal = value;
            value = val;
            return oldVal;
        }
    }

    /**
     * Clears the map of all values and keys.
     */
    @Override
    public void clear(){
        numKeys = 0;
        table = new LinkedList[table.length];
    }

    /**
     * Checks if the hashMap contains the key being passed.
     * @param key The key to be checked.
     * @return True if the key is in the map, false otherwise.
     */
    @Override
    public boolean containsKey(Object key){
        boolean found = false;
        int index = key.hashCode() % table.length;

        if (index < 0){
            index += table.length;
        }

        if (table[index] != null){
            for (HashMapEntry<K, V> nextItem : table[index]){
                if (nextItem.getKey().equals(key)){
                    found = true;
                }
            }
        }

        return found;
    }

    /**
     * Finds the value in the map that matches the key that
     * was passed in to the method, if the key is in the map.
     * @param key The key that is supposed to match a key in the map.
     * @return The value of the entry that has the matching key value if it exists. Null otherwise.
     */
    @Override
    public V get(Object key){

        int index = key.hashCode() % table.length;
        if (index < 0){
            index += table.length;
        }
        if (table[index] == null){
            return null;
        }

        for (HashMapEntry<K, V> nextItem : table[index]){
            if (nextItem.getKey().equals(key)){
                return nextItem.getValue();
            }
        }

        return null;
    }

    /**
     * Checks if the map has no entries.
     * @return True if there are no keys in the map, false otherwise.
     */
    @Override
    public boolean isEmpty(){
        return numKeys == 0;
    }

    /**
     * Adds a key and a value to the map. If the key already is in the map,
     * the value passed in the to the method replaces the old value for that key.
     * @param key The key to be added, if it isn't already in the map.
     * @param value The value to be put in the map for the key parameter.
     * @return The old value stored at the key passed in
     * if there is a value at that key. Null otherwise.
     */
    @Override
    public V put(K key, V value){

        int index = key.hashCode() % table.length;
        if (index < 0){
            index += table.length;
        }
        if (table[index] == null){
            table[index] = new LinkedList<>();
        }

        for (HashMapEntry<K, V> nextItem : table[index]){
            if (nextItem.getValue().equals(key)){
                V oldVal = nextItem.getValue();
                nextItem.setValue(value);
                return oldVal;
            }
        }

        table[index].addFirst(new HashMapEntry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length)){
            rehash();
        }
        return null;
    }

    /**
     * Removes the value at the key passed in, if there is a value at that key.
     * @param key The key to remove the value from.
     * @return The old value at the key being passed in if such a value exists. Null otherwise.
     */
    @Override
    public V remove(Object key) {

        int index = key.hashCode() % table.length;
        if (index < 0){
            index += table.length;
        }
        if (table[index] == null){
            return null;
        }

        for (HashMapEntry<K, V> nextItem : table[index]){

            if (nextItem.getKey().equals(key)){
                V oldVal = table[index].remove().getValue();
                numKeys--;
                if (table[index].isEmpty()){
                    table[index] = null;
                }
                return oldVal;
            }
        }

        return null;
    }

    @Override
    public int size() {
        return numKeys;
    }

    /**
     * Resets the hash table if it is necessary.
     */
    private void rehash(){

        LinkedList<HashMapEntry<K, V>>[] oldTable = table;
        table = new LinkedList[2 * oldTable.length + 1];

        numKeys = 0;

        for (int i = 0; i < oldTable.length; i++){
            if ((oldTable[i] != null) && (!oldTable[i].isEmpty())){
                for (HashMapEntry<K, V> nextItem : oldTable[i]){
                    put(nextItem.key, nextItem.value);
                    numKeys++;
                }
            }
        }
    }

    /* These methods don't need to be implemented fully */

    @Override
    public boolean containsValue(Object value) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<K> keySet() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }

    @Override
    public Collection<V> values() throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    @Override
    public Set<Entry<K, V>> entrySet() throws UnsupportedOperationException{
        throw new UnsupportedOperationException();
    }


}