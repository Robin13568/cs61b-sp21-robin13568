package deque;

import java.util.Comparator;

public class CompareBoolean implements Comparator<Boolean> {
    public int compare(Boolean b1, Boolean b2) {
        return Boolean.compare(b1, b2);
    }
}
