package THUDataStructure.PriorityQueue;

public class EntryDefault implements Entry {
    protected Object key;
    protected Object value;

    public EntryDefault(Object k, Object v) {
        key = k;
        value = v;
    }

    public Object getKey() {
        return key;
    }

    public Object setKey(Object k) {
        Object oldKey = key;
        key = k;
        return oldKey;
    }

    public Object getValue() {
        return value;
    }

    public Object setValue(Object v) {
        Object oldValue = value;
        value = v;
        return oldValue;
    }
}
