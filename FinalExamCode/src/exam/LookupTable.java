package exam;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;

public class LookupTable<K, V> implements Map<K, V> {

    private LinkedList<TableEntry<K, V>>[] table;
    private static final int START_CAPACITY = 1024;
    private static final double LOAD_THRESHOLD = 3.0;
    private int numKeys;


    public LookupTable(){
        table = new LinkedList[START_CAPACITY];
    }

    private static class TableEntry<K, V> {
        private final K key;
        private V value;

        public TableEntry(K key, V value){
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


    @Override
    public void clear(){
        numKeys = 0;
        table = new LinkedList[table.length];
    }


    @Override
    public boolean containsKey(Object key){
        boolean found = false;
        int index = key.hashCode() % table.length;

        if (index < 0){
            index += table.length;
        }

        if (table[index] != null){
            for (TableEntry<K, V> nextItem : table[index]){
                if (nextItem.getKey().equals(key)){
                    found = true;
                }
            }
        }

        return found;
    }


    @Override
    public V get(Object key){

        int index = key.hashCode() % table.length;
        if (index < 0){
            index += table.length;
        }
        if (table[index] == null){
            return null;
        }

        for (TableEntry<K, V> nextItem : table[index]){
            if (nextItem.getKey().equals(key)){
                return nextItem.getValue();
            }
        }

        return null;
    }


    @Override
    public boolean isEmpty(){
        return numKeys == 0;
    }


    @Override
    public V put(K key, V value){

        int index = key.hashCode() % table.length;
        if (index < 0){
            index += table.length;
        }
        if (table[index] == null){
            table[index] = new LinkedList<>();
        }

        for (TableEntry<K, V> nextItem : table[index]){
            if (nextItem.getValue().equals(key)){
                V oldVal = nextItem.getValue();
                nextItem.setValue(value);
                return oldVal;
            }
        }

        table[index].addFirst(new TableEntry<>(key, value));
        numKeys++;
        if (numKeys > (LOAD_THRESHOLD * table.length)){
            rehash();
        }
        return null;
    }

    public void print(){
        for (LinkedList<TableEntry<K, V>> list : table){
            if (list != null){
                for (TableEntry<K, V> entry : list){
                    System.out.println(entry.getKey() + ", " + entry.getValue());
                }
            }

        }
    }

    @Override
    public V remove(Object key) {

        int index = key.hashCode() % table.length;
        if (index < 0){
            index += table.length;
        }
        if (table[index] == null){
            return null;
        }

        for (TableEntry<K, V> nextItem : table[index]){

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

    private void rehash(){

        LinkedList<TableEntry<K, V>>[] oldTable = table;
        table = new LinkedList[2 * oldTable.length + 1];

        numKeys = 0;

        for (int i = 0; i < oldTable.length; i++){
            if ((oldTable[i] != null) && (!oldTable[i].isEmpty())){
                for (TableEntry<K, V> nextItem : oldTable[i]){
                    put(nextItem.key, nextItem.value);
                    numKeys++;
                }
            }
        }
    }



    @Override
    public int size() {
        return numKeys;
    }

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
