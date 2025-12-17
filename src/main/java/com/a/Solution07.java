package com.a;

public class Solution07 {
    public int maxBottlesDrunk(int numBottles, int numExchange) {
        if (numBottles < numExchange) return numBottles;

        int BottlesDrunk = 0;

        BottlesDrunk += numBottles;
        int EmptyBottles = numBottles;

        while (EmptyBottles >= numExchange) {
            EmptyBottles -= numExchange;
            numExchange++;
            BottlesDrunk++;
            EmptyBottles++;
        }

        return BottlesDrunk;
    }
}
