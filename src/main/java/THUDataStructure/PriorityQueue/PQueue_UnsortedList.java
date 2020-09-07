package THUDataStructure.PriorityQueue;

import THUDataStructure.Iterator.Iterator;
import THUDataStructure.List.List;
import THUDataStructure.List.List_DLNode;
import THUDataStructure.Position;
import THUDataStructure.PriorityQueue.Exception.ExceptionKeyInvaild;
import THUDataStructure.PriorityQueue.Exception.ExceptionPQueueEmpty;
import THUDataStructure.Sequence.Sequence;

/**
 * 基于无需列表实现优先队列
 */
public class PQueue_UnsortedList implements PQueue {

    private List L;
    private Comparator C;

    public PQueue_UnsortedList(Comparator c, Sequence s) {
        L = new List_DLNode();
        C = c;
        if(null != s) {
            while (!s.isEmpty()) {
                Entry e =(Entry) s.removeFirst();
                insert(e.getKey(), e.getValue());
            }
        }
    }

    @Override
    public int getSize() {
        return L.getSize();
    }

    @Override
    public boolean isEmpty()
    {
        return L.isEmpty();
    }

    @Override
    public Entry getMin() throws ExceptionPQueueEmpty {
        if (L.isEmpty()) {
            throw new ExceptionPQueueEmpty("error: empty");
        }

        Position minPos = L.first();
        Position curPos = L.getNext(minPos);
        while (null != curPos) {
            if (0 < C.compare(((Entry) minPos.getElem()).getKey(),
                    ((Entry) curPos.getElem()).getKey())) {
                minPos = curPos;
            }
        }
        return (Entry) minPos.getElem();
    }

    @Override
    public Entry insert(Object key, Object obj) throws ExceptionKeyInvaild {
        Entry entry = new EntryDefault(key, obj);
        L.insertLast(entry);
        return entry;
    }

    @Override
    public Entry delMin() throws ExceptionPQueueEmpty {
        if (L.isEmpty()) {
            throw new ExceptionPQueueEmpty("error: empty");
        }
        Position minPos = L.first();
        Iterator it = L.positions();
        while (it.hasNext()) {
            Position curPos = (Position) (it.getNext());
            if (0 < C.compare(((Entry) minPos.getElem()).getKey(),
                    ((Entry) curPos.getElem()).getKey())) {
                minPos = curPos;
            }
        }
        return (Entry) L.remove(minPos);

    }
}
