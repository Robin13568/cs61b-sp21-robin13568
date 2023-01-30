package deque;

import java.util.Comparator;

public class CompareDouble implements Comparator<Double>{
    public int compare(Double x1, Double x2) {
        return (int)(x1 - x2);
    }
}
