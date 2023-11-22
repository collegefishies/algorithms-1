interface StackInterface<Type> {
    void push(Type val);

    Type pop();

    boolean isEmpty();

    int size();
}
