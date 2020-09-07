package THUDataStructure.PriorityQueue;

/**
 * 定义了一个条目的概念，将一个对象和关键字结合为一个复合对象，但这个
 * 其实这个也不完全必要，因为可以针对不同的对象，提供不同的比较器，这就
 * 可以将条目的概念省去。
 */
public interface Entry {

    public Object getKey();

    // 修改条目关键字，返回原先的关键码
    public Object setKey(Object k);

    public Object getValue();

    // 修改条目的数据对象，返回原先的数据对象
    public Object setValue(Object v);
}
