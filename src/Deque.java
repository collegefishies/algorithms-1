/*

Write a generic data type for a deque and a randomized queue. The goal of this
assignment is to implement elementary data structures using arrays and linked
lists, and to introduce you to generics and iterators.



Dequeue. A double-ended queue or deque (pronounced “deck”) is a generalization
of a stack and a queue that supports adding and removing items from either the
front or the back of the data structure. Create a generic data type Deque that
implements the following API:



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

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Deque<Item> implements Iterable<Item> {
    private DoublyLinkedList<Item> head;
    private DoublyLinkedList<Item> tail;
    private int length;

    public Deque() {}

    public boolean isEmpty() {
        return head == null || tail == null;
    };

    public int size() { return length; }

    public void addFirst(Item item) {
        if (item == null) { throw new IllegalArgumentException("Item cannot be null"); }
        if (head == null) {
            head = new DoublyLinkedList<Item>();
            head.value = item;
            tail = head;
        } else {
            DoublyLinkedList<Item> newHead = new DoublyLinkedList<Item>();
            newHead.value = item;
            newHead.back = head;
            head.next = newHead;
            head = newHead;
        }

        length++;
    }

    public void addLast(Item item) {
        if (item == null) { throw new IllegalArgumentException("Item cannot be null"); }
        if (tail == null) {
            tail = new DoublyLinkedList<Item>();
            tail.value = item;
            head = tail;
        } else {
            DoublyLinkedList<Item> newTail = new DoublyLinkedList<Item>();
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

        DoublyLinkedList<Item> initialHead = head;
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

        DoublyLinkedList<Item> initialTail = tail;
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

    public Iterator<Item> iterator();
    public String toString() {
        return "Deque";
    }
    public static void main(String[] args) {

    }
}

private class DoublyLinkedList<Item> {
    Item value;
    DoublyLinkedList<Item> next; // right
    DoublyLinkedList<Item> back; // left, in my head.
}
