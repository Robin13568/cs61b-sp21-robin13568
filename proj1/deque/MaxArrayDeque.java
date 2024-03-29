package deque;

import java.util.Comparator;

public class MaxArrayDeque<T> extends ArrayDeque<T> {
    private Comparator<T> comp;

    public MaxArrayDeque(Comparator<T> c) {
        super();
        comp = c;
    }

    public T max() {
        T m = get(0);
        for (int i = 0; i < size(); i++) {
            T curr = get(i);
            if (comp.compare(m, curr) < 0) {
                m = curr;
            }
        }
        return m;
    }

    public T max(Comparator<T> c) {
        T m = get(0);
        for (int i = 0; i < size(); i++) {
            T curr = get(i);
            if (c.compare(m, curr) < 0) {
                m = curr;
            }
        }
        return m;
    }
}
