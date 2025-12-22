package com.k5;

import java.util.ArrayDeque;
import java.util.Deque;

public class BrowserHistory {
    private Deque<String> backStack;
    private Deque<String> forwardStack;


    public BrowserHistory(String homepage) {
        backStack = new ArrayDeque<>();
        forwardStack = new ArrayDeque<>();
        backStack.add(homepage);
    }

    public void visit(String url) {
        forwardStack.clear();
        backStack.push(url);
    }

    public String back(int steps) {
        if (!backStack.isEmpty()) {
            int x = Math.min(steps, backStack.size() - 1);
            for (int i = 0; i < x; i++) {
                if (!backStack.isEmpty()) {
                    String s = backStack.pop();
                    forwardStack.push(s);
                }
            }
        }
        return backStack.peek();
    }

    public String forward(int steps) {
        if (!forwardStack.isEmpty()) {
            int x = Math.min(steps, forwardStack.size());
            for (int i = 0; i < x; i++) {
                if (!forwardStack.isEmpty()) {
                    String s = forwardStack.pop();
                    backStack.push(s);
                }
            }
        }
        return backStack.peek();

    }
}
