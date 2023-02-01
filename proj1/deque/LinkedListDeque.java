package deque;
import java.util.Iterator;

public class LinkedListDeque<T> implements Deque<T>, Iterable<T> {
    private class Node {
        private Node prev;
        private T item;
        private Node next;

        private Node(Node p, T i, Node n) {
            prev = p;
            item = i;
            next = n;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        T a = (T) new Object();
        sentinel = new Node(null, a, null);
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    @Override
    // Adds an item of type T to the front of the deque. You can assume that item is never null.
    public void addFirst(T item) {
        sentinel.next.prev = new Node(sentinel, item, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    @Override
    // Adds an item of type T to the back of the deque. You can assume that item is never null.
    public void addLast(T item) {
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node curr = sentinel;
        for (int i = 0; i < size; i++) {
            curr = curr.next;
            System.out.print(curr.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T rF = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return rF;
    }

    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T rL = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return rL;
    }

    @Override
    public T get(int index) {
        if (index < 0 | index >= size) {
            return null;
        }
        Node curr = sentinel;
        for (int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        return getRecursiveHelp(index, sentinel);
    }

    private T getRecursiveHelp(int index, Node curr) {
        if (index == 0) {
            return curr.next.item;
        } else {
            return getRecursiveHelp(index - 1, curr.next);
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();
    }

    private class LinkedListDequeIterator implements Iterator<T> {
        private int pos;
        LinkedListDequeIterator() {
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
        Deque<T> other = (Deque<T>) o;
        if (size != other.size()) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (!this.get(i).equals(other.get(i))) {
                return false;
            }
        }
        return true;
    }
}
