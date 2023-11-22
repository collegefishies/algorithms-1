import java.util.NoSuchElementException;

public class StackLinkedList<Type> implements StackInterface<Type> {
    private int count;
    private Node current = null;

    public StackLinkedList() {
        count = 0;
    }

    @Override
    public void push(Type val) {
        Node newNode = new Node();
        newNode.next = current;
        newNode.value = val;
        current = newNode;
        count++;
    }

    @Override
    public Type pop() {
        if (count == 0) {
            throw new NoSuchElementException("No element to pop.");
        }

        Type val = current.value;
        current = current.next;
        count--;
        return val;
    }

    @Override
    public boolean isEmpty() {
        return current == null;
    }

    @Override
    public int size() {
        return count;
    }

    private class Node {
        Type value;
        Node next;
    }
}


