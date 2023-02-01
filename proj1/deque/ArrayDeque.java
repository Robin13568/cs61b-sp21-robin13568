package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
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

    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextFirst] = item;
        nextFirst = moveForward(nextFirst);
        size++;
    }

    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(size * 2);
        }
        items[nextLast] = item;
        nextLast = moveBackward(nextLast);
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
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

    @Override
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

    @Override
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

    @Override
    public T get(int index) {
        if (index < 0 | index >= size) {
            return null;
        }
        int curr = moveBackward(nextFirst);
        for (int i = 0; i < index; i++) {
            curr = moveBackward(curr);
        }
        return items[curr];
    }

    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    private class ArrayDequeIterator implements Iterator<T> {
        private int pos;
        ArrayDequeIterator() {
            pos = 0;
        }

        public boolean hasNext() {
            return pos < size;
        }

        public T next() {
            T returnItem = get(pos);
            pos += 1;
            return returnItem;
        }

    }

    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        }
        if (this == o) {
            return true;
        }
        if (!(o instanceof Deque)) {
            return false;
        }

        if (size != ((Deque) o).size()) {
            return false;
        }
        Iterator<T> i1 = this.iterator();
        Iterator<T> i2 = null;

        if (o instanceof ArrayDeque) {
            ArrayDeque<T> other = (ArrayDeque<T>) o;
            i2 = other.iterator();
        } else if (o instanceof LinkedListDeque) {
            LinkedListDeque<T> other = (LinkedListDeque<T>) o;
            i2 = other.iterator();
        }

        while (i1.hasNext() && i2.hasNext()) {
            if (i1.next() != i2.next()) {
                return false;
            }
        }
        return true;
    }
}
