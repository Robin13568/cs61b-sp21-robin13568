package deque;

public class LinkedListDeque<T> {
    private class Node {
        public Node prev;
        public T item;
        public Node next;

        public Node(Node p, T i, Node n) {
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

    public LinkedListDeque(T item) {
        T a = (T) new Object();
        sentinel = new Node(null, a, null);
        sentinel.next = new Node(sentinel, item, sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }

    // Adds an item of type T to the front of the deque. You can assume that item is never null.
    public void addFirst(T item) {
        sentinel.next.prev = new Node(sentinel, item, sentinel.next);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    // Adds an item of type T to the back of the deque. You can assume that item is never null.
    public void addLast(T item) {
        sentinel.prev.next = new Node(sentinel.prev, item, sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void printDeque() {
        Node curr = sentinel;
        for (int i = 0; i < size; i++) {
            curr = curr.next;
            System.out.print(curr.item);
            System.out.print(" ");
        }
        System.out.println();
    }

    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        T rF = sentinel.next.item;
        sentinel.next = sentinel.next.next;
        size -= 1;
        return rF;
    }

    public T removeLast() {
        if (size == 0) {
            return null;
        }
        T rL = sentinel.prev.item;
        sentinel.prev = sentinel.prev.prev;
        size -= 1;
        return rL;
    }

    public T get(int index) {
        if (index < 0 | index >=size) {
            return null;
        }
        Node curr = sentinel;
        for (int i = 0; i <= index; i++) {
            curr = curr.next;
        }
        return curr.item;
    }

    public T getRecursive(int index) {
        if (index < 0 | index >=size) {
            return null;
        } else if (index == 0) {
            return sentinel.next.item;
        } else {
            return getRecursive(index - 1);
        }
    }

    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }
        LinkedListDeque<?> lld1 = (LinkedListDeque<?>) o;
        if (size != lld1.size) {
            return false;
        }
        Node curr = sentinel;
        LinkedListDeque<?>.Node curr1 = lld1.sentinel;
        for (int i = 0; i <= size; i++) {
            if (i > 0 && curr.item != curr1.item) {
                return false;
            }
            curr = curr.next;
            curr1 = curr1.next;
        }
        return true;
    }
}