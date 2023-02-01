package deque;
import edu.princeton.cs.algs4.StdRandom;
import java.util.LinkedList;
import static org.junit.Assert.*;

public class LinkedListDequeRandomizedTest {
    public static void randomizedTest() {
        LinkedList<Integer> l1 = new LinkedList<>();
        LinkedListDeque<Integer> l2 = new LinkedListDeque<>();

        int N = 1000000;
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 5);
            int randVal = StdRandom.uniform(0, 100);
            if (operationNumber == 0) {
                l1.addLast(randVal);
                l2.addLast(randVal);
            } else if (operationNumber == 1) {
                l1.addFirst(randVal);
                l2.addFirst(randVal);
            } else if (l1.size() > 0 && operationNumber == 2) {
                assertEquals(l1.removeFirst(), l2.removeFirst());
            } else if (l1.size() > 0 && operationNumber == 3) {
                assertEquals(l1.removeLast(), l2.removeLast());
            } else if (l1.size() > 0 && operationNumber == 4) {
                assertEquals(l1.get(l1.size() - 1), l2.getRecursive(l2.size() - 1));
            }
        }
//        l2.isEmpty();
//        l2.printDeque();
//        l2.addFirst(1);
//        l2.printDeque();
//        l2.removeLast();
//        l2.printDeque();
//        l2.isEmpty();
//        l2.printDeque();
    }

    public static void main(String[] args) {
        randomizedTest();
    }
}
