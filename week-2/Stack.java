import java.util.NoSuchElementException;

public interface StackInterface<Type> {
    void push(Type val);

    Type pop();

    boolean isEmpty();

    int size();
}

public class StackLinkedList<Type> implements StackInterface<Type> {
    private int count;
    private Node current = null;

    private class Node {
        Type value;
        Node next;
    }

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
}

public class StackArray<Type> implements StackInterface<Type> {

}
