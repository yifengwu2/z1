package com.a;

public class Solution02 {
    public int[] successfulPairs(int[] spells, int[] potions, long success) {
        int n1 = spells.length;
        int n2 = potions.length;
        int[] pairs = new int[n1];

        for (int i = 0; i < n1; i++) {
            int spell = spells[i];

            int count = isOver(spell, potions, success);
            pairs[i] = count;

        }
        return pairs;
    }

    public int isOver(int spell, int[] position, long success) {
        int count = 0;
        for (int j : position) {
            if (((long) spell * j >= success)) {
                count++;
            }
        }
        return count;

    }
}
