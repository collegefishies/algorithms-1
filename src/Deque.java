/*

Write a generic data type for a deque and a randomized queue. The goal of this
assignment is to implement elementary data structures using arrays and linked
lists, and to introduce you to generics and iterators.



Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization
of a stack and a queue that supports adding and removing items from either the
front or the back of the data structure. Create a generic data type Deque that
implements the following API:

public class Deque<Item> implements Iterable<Item> {

    // construct an empty deque
    public Deque()

    // is the deque empty?
    public boolean isEmpty()

    // return the number of items on the deque
    public int size()

    // add the item to the front
    public void addFirst(Item item)

    // add the item to the back
    public void addLast(Item item)

    // remove and return the item from the front
    public Item removeFirst()

    // remove and return the item from the back
    public Item removeLast()

    // return an iterator over items in order from front to back
    public Iterator<Item> iterator()

    // unit testing (required)
    public static void main(String[] args)

}

Corner cases.  Throw the specified exception for the following corner cases:

Throw an IllegalArgumentException if the client calls either addFirst() or
addLast() with a null argument.

Throw a java.util.NoSuchElementException if the client calls either removeFirst()
or removeLast when the deque is empty.

Throw a java.util.NoSuchElementException if the client calls the next() method
in the iterator when there are no more items to return.

Throw an UnsupportedOperationException if the client calls the remove() method
in the iterator.


Unit testing.  Your main() method must call directly every public constructor
and method to help verify that they work as prescribed (e.g., by printing results
to standard output).

Performance requirements.  Your deque implementation must support each deque
operation (including construction) in constant worst-case time. A deque
containing n items must use at most 48n + 192 bytes of memory. Additionally,
your iterator implementation must support each operation (including construction)
in constant worst-case time.
*/

import edu.princeton.cs.algs4.StdOut;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private DoublyLinkedList head;
    private DoublyLinkedList tail;
    private int length;
    public Deque() {
    }

    public static void main(String[] args) {
        Deque<Integer> q = new Deque<>();
        q.addLast(0);
        q.addLast(1);
        q.addLast(2);
        StdOut.println(q.size());
        for (int x : q) StdOut.print(x + " ");
        q.addFirst(0);
        q.addFirst(1);
        q.addFirst(2);
        for (int x : q) StdOut.print(x + " ");
        q.addFirst(0);
        q.addFirst(1);
        q.addFirst(2);
        StdOut.print(q.removeLast() + " ");
        StdOut.print(q.removeLast() + " ");
        StdOut.print(q.removeLast() + " ");
        StdOut.println(q.isEmpty());
    }

    public boolean isEmpty() {
        return head == null || tail == null;
    }

    public int size() {
        return length;
    }

    public void addFirst(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (head == null) {
            head = new DoublyLinkedList();
            head.value = item;
            tail = head;
        } else {
            DoublyLinkedList newHead = new DoublyLinkedList();
            newHead.value = item;
            newHead.back = head;
            head.next = newHead;
            head = newHead;
        }

        length++;
    }

    public void addLast(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item cannot be null");
        }
        if (tail == null) {
            tail = new DoublyLinkedList();
            tail.value = item;
            head = tail;
        } else {
            DoublyLinkedList newTail = new DoublyLinkedList();
            newTail.value = item;
            newTail.next = tail;
            tail.back = newTail;
            tail = newTail;
        }
        length++;
    }

    public Item removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("There's no element to remove.");
        }

        DoublyLinkedList initialHead = head;
        Item item = head.value;
        head = head.back;
        initialHead.back = null;
        if (head != null) {
            head.next = null;
        } else {
            tail = head;
        }
        length--;
        return item;
    }

    public Item removeLast() {
        if (tail == null) {
            throw new NoSuchElementException("There's no element to remove.");
        }

        DoublyLinkedList initialTail = tail;
        Item item = tail.value;
        tail = tail.next;
        initialTail.next = null;
        if (tail != null) {
            tail.back = null;
        } else {
            head = tail;
        }
        length--;
        return item;
    }

    public Iterator<Item> iterator() {
        return new Iterator<Item>() {
            public boolean hasNext() {
                return !isEmpty();
            }

            public Item next() {
                if (isEmpty()) throw new NoSuchElementException("Deque is empty.");
                return removeFirst();
            }
        };
    }

    public String toString() {
        return "Deque";
    }

    private class DoublyLinkedList {
        public Item value;
        public DoublyLinkedList next; // right
        public DoublyLinkedList back; // left, in my head.
    }
}

