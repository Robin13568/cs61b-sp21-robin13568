package tester;

import edu.princeton.cs.algs4.StdRandom;
import static org.junit.Assert.*;
import org.junit.Test;
import student.StudentArrayDeque;

public class TestArrayDequeEC {
    public static void randomizedTest() {
        ArrayDequeSolution<Integer> ad1 = new ArrayDequeSolution<>();
        StudentArrayDeque<Integer> ad2 = new StudentArrayDeque<>();

        int N = 5000;
        StringBuilder message = new StringBuilder();
        for (int i = 0; i < N; i++) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                int randVal = StdRandom.uniform(0, 100);
                ad1.addFirst(randVal);
                ad2.addFirst(randVal);
                message.append(String.format("addFirst(%d)", randVal));
                message.append("\n");
            } else if (operationNumber == 1) {
                int randVal = StdRandom.uniform(0, 100);
                ad1.addLast(randVal);
                ad2.addLast(randVal);
                message.append(String.format("addLast(%d)", randVal));
                message.append("\n");
            } else if (ad1.size() > 0 && operationNumber == 2) {
                message.append("removeFirst()");
                message.append("\n");
                assertEquals(message.toString(), ad1.removeFirst(), ad2.removeFirst());
            } else if (ad1.size() > 0 && operationNumber == 3) {
                message.append("removeLast()");
                message.append("\n");
                assertEquals(message.toString(), ad1.removeLast(), ad2.removeLast());
            }
        }
    }

    public static void main(String[] args) {
        randomizedTest();
    }
}
