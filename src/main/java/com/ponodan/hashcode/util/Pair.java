package com.ponodan.hashcode.util;

public class Pair<T, B> {
    private B right;
    private T left;

    public Pair(T left, B right) {
        this.right = right;
        this.left = left;
    }

    public B getRight() {
        return right;
    }

    public T getLeft() {
        return left;
    }
}
