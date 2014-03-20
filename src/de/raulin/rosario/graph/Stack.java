package de.raulin.rosario.graph;

import java.util.Iterator;

/**
 * Created by Rosario on 20.03.14.
 */
public class Stack<T> implements Iterable<T> {

    private static final int DEFAULT_CAPACITY = 8;

    private int capacity;
    private int size;
    private T[] data;

    @SuppressWarnings("unchecked")
    public Stack(int capacity) {
        this.capacity = capacity;
        this.size = 0;
        this.data = (T[]) new Object[capacity];
    }

    public Stack() {
        this(DEFAULT_CAPACITY);
    }

    public void push(T obj) {
        if (size >= capacity) resize(capacity*2);
        data[size++] = obj;
    }

    public T pop() {
        if (size > 0) {
            T obj = data[--size];
            return obj;
        } else {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    @SuppressWarnings("unchecked")
    private void resize(int to) {
        capacity = to;
        T[] newData = (T[])new Object[to];
        for (int i = 0; i < size; ++i) {
            newData[i] = data[i];
        }
        data = newData;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int curr = size;

            @Override
            public boolean hasNext() {
                return curr > 0;
            }

            @Override
            public T next() {
                return data[--curr];
            }
        };
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack<Integer>();

        for (Integer i = 0; i < 100; ++i) {
            stack.push(i);
        }

        System.out.println("stack size: " + stack.size());

        for (Integer x : stack) {
            System.out.println(x);
        }

        System.out.println("and again...");

        while (!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
