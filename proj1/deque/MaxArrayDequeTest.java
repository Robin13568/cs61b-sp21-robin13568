package deque;

import org.junit.Test;

import java.util.Comparator;
import java.util.Optional;

import static org.junit.Assert.*;

public class MaxArrayDequeTest {
    @Test
    public void addIsEmptySizeTest() {
        MaxArrayDeque<String> ad1 = new MaxArrayDeque<>(MaxArrayDequeTest.getStringComparator());

        assertTrue("A newly initialized LLDeque should be empty", ad1.isEmpty());
        ad1.addFirst("front");

        assertEquals(1, ad1.size());
        assertFalse("lld1 should now contain 1 item", ad1.isEmpty());

        ad1.addLast("middle");
        assertEquals(2, ad1.size());

        ad1.addLast("back");
        assertEquals(3, ad1.size());

        System.out.println("Printing out deque: ");
        ad1.printDeque();
    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {
        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(MaxArrayDequeTest.getIntegerComparator());
        assertTrue("lld1 should be empty upon initialization", ad1.isEmpty());

        ad1.addFirst(10);
        assertFalse("lld1 should contain 1 item", ad1.isEmpty());

        ad1.removeFirst();
        assertTrue("lld1 should be empty after removal", ad1.isEmpty());
    }

    @Test
    public void removeEmptyTest() {
        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(MaxArrayDequeTest.getIntegerComparator());
        ad1.addFirst(3);

        ad1.removeLast();
        ad1.removeFirst();
        ad1.removeLast();
        ad1.removeFirst();

        int size = ad1.size();
        String errorMsg = "  Bad size returned when removing from empty deque.\n";
        errorMsg += "  student size() returned " + size + "\n";
        errorMsg += "  actual size() returned 0\n";

        assertEquals(errorMsg, 0, size);
    }

    @Test
    public void multipleParamTest() {
        MaxArrayDeque<String>  ad1 = new MaxArrayDeque<>(MaxArrayDequeTest.getStringComparator());
        MaxArrayDeque<Double>  ad2 = new MaxArrayDeque<>(MaxArrayDequeTest.getDoubleComparator());
        MaxArrayDeque<Boolean> ad3 = new MaxArrayDeque<>(MaxArrayDequeTest.getBooleanComparator());

        ad1.addFirst("string");
        ad2.addFirst(3.14159);
        ad3.addFirst(true);

        String s = ad1.removeFirst();
        double d = ad2.removeFirst();
        boolean b = ad3.removeFirst();
    }

    @Test
    public void emptyNullReturnTest() {
        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(MaxArrayDequeTest.getIntegerComparator());

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals("Should return null when removeFirst is called on an empty Deque,", null, ad1.removeFirst());
        assertEquals("Should return null when removeLast is called on an empty Deque,", null, ad1.removeLast());
    }

    @Test
    public void bigLLDequeTest() {
        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(MaxArrayDequeTest.getIntegerComparator());
        for (int i = 0; i < 1000000; i++) {
            ad1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals("Should have the same value", i, (double) ad1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals("Should have the same value", i, (double) ad1.removeLast(), 0.0);
        }
    }

    @Test
    public void compareTest() {
        MaxArrayDeque<Integer> ad1 = new MaxArrayDeque<>(MaxArrayDequeTest.getIntegerComparator());
        ad1.addFirst(1);
        ad1.addFirst(5);
        ad1.addFirst(8);
        assertTrue("Should have the max value", ad1.max() == 8);
        assertTrue("Should have the max value", ad1.max(MaxArrayDequeTest.getIntegerComparator()) == 8);
    }

    private static class IntegerComparator implements Comparator<Integer> {
        public int compare(Integer x1, Integer x2) {
            return x1.compareTo(x2);
        }
    }

    public static Comparator<Integer> getIntegerComparator() {
        return new IntegerComparator();
    }

    private static class StringComparator implements Comparator<String> {
        public int compare(String s1, String s2) {
            return s1.compareTo(s2);
        }
    }

    public static Comparator<String> getStringComparator() {
        return new StringComparator();
    }

    private static class BooleanComparator implements Comparator<Boolean> {
        public int compare(Boolean b1, Boolean b2) {
            return b1.compareTo(b2);
        }
    }

    public static Comparator<Boolean> getBooleanComparator() {
        return new BooleanComparator();
    }

    private static class DoubleComparator implements Comparator<Double> {
        public int compare(Double x1, Double x2) {
            return x1.compareTo(x2);
        }
    }

    public static Comparator<Double> getDoubleComparator() {
        return new DoubleComparator();
    }
}
