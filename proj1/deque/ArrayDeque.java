package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque() {
        items = (T[]) new Object[8];
        size = 0;
        nextFirst = 4;
        nextLast = 5;
    }

    public ArrayDeque(T item) {
        items = (T[]) new Object[8];
        size = 1;
        nextFirst = 4;
        nextLast = 6;
    }

    private int moveForward(int index) {
        if (index == 0) {
            return items.length - 1;
        }
        return index - 1;
    }

    private int moveBackward(int index) {
        if (index == items.length - 1) {
            return 0;
        }
        return index + 1;
    }

    private void resize(int capacity) {
        T[] a = (T[]) new Object[capacity];
        int p1 = moveBackward(nextFirst);
        for (int i = 0; i < size; i++) {
            a[i] = items[p1];
            p1 = moveBackward(p1);
        }
        items = a;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = moveForward(nextFirst);
        size++;
    }

    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = moveBackward(nextLast);
        size++;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        int p1 = moveBackward(nextFirst);
        for (int i = 0; i < size; i++) {
            System.out.print(items[p1]);
            if (i < size - 1) {
                System.out.print(" ");
            }
            p1 = moveBackward(p1);
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && size - 1 < items.length * 0.25) {
            resize(items.length / 2);
        }
        nextFirst = moveBackward(nextFirst);
        T x = items[nextFirst];
        items[nextFirst] = null;
        size--;
        return x;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        if (items.length >= 16 && size - 1 < items.length * 0.25) {
            resize(items.length / 2);
        }
        nextLast = moveForward(nextLast);
        T x = items[nextLast];
        items[nextLast] = null;
        size--;
        return x;
    }
}