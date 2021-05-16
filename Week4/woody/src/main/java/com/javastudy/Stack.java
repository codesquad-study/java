package com.javastudy;

import java.util.EmptyStackException;

public class Stack {
    private static final int INIT_SIZE = 16;

    private int[] stack = new int[INIT_SIZE];
    private int elementCount = 0;

    public void push(int data) {
        checkSize();
        stack[size()] = data;
        elementCount++;
    }

    public int pop() {
        int target = stack[lastIndexOf()];
        elementCount--;
        return target;
    }

    public int size() {
        return elementCount;
    }

    public int lastIndexOf() {
        if (elementCount == 0) {
            throw new EmptyStackException();
        }
        return elementCount - 1;
    }

    private void checkSize() {
        // 현재 stack 배열이 모두 찼을 경우 배열 사이즈를 두 배로 늘려준다.
        if (stack.length <= size()) {
            int[] extended = new int[stack.length * 2];
            for (int i = 0; i < stack.length; i++) {
                extended[i] = stack[i];
            }
            stack = extended;
        }
    }

}
