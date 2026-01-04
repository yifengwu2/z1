package com.k6;

import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;

public class Solution21 {
    public int[] deckRevealedIncreasing(int[] deck) {
        Arrays.sort(deck);
        Deque<Integer> deque = new ArrayDeque<>();
        for (int i = deck.length - 1; i >= 0; i--) {
            int card = deck[i];
            if (!deque.isEmpty()) {
                deque.addFirst(deque.removeLast());
            }
            deque.addFirst(card);
        }
        int[] res = new int[deck.length];
        int idx = 0;
        for (Integer x : deque) {
            res[idx++] = x;
        }
        return res;
    }
}
