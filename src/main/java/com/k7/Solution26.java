package com.k7;

public class Solution26 {
    public String mapWordWeights(String[] words, int[] weights) {
        int n = words.length;
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            String word = words[i];
            int sum = 0;
            for (int j = 0; j < word.length(); j++) {
                char c = word.charAt(j);
                int i1 = c - 'a';
                sum += weights[i1];
            }
            int num = sum % 26;
            char c = (char) ((25 - num) + 'a');
            sb.append(c);
        }
        return sb.toString();
    }
}
