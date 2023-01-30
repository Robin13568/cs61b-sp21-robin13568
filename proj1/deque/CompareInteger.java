package deque;

import java.util.Comparator;

public class CompareInteger implements Comparator<Integer> {
    public int compare(Integer x1, Integer x2) {
        return x1 - x2;
    }
}