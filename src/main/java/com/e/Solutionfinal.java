package com.e;

public class Solutionfinal {

    //插入
    public boolean insert(int[] nums, int size, int index, int value) {
        int n = nums.length;
        if (index > n || index < 0 || size == n) return false;
        for (int j = n - 1; j >= index; j--) {
            nums[j] = nums[j - 1];
        }
        nums[index] = value;
        return true;
    }

    //删除
    public int remove(int[] nums, int size, int index) {
        if (index >= nums.length || index < 0 || index >= size) return -1;
        int num = nums[index];

        for (int i = index; i + 1 < size; i++) {
            nums[i] = nums[i + 1];
        }

        return num;
    }

    //冒泡
    public void bubble(int[] nums) {
        int n = nums.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - 1 - i; j++) {
                if (nums[j] > nums[j + 1]) swap(nums, nums[j], nums[j + 1]);
            }

        }
    }
    private void swap(int[] nums, int a, int b) {
        int temp = a;
        a = b;
        b = temp;
    }

    //插入排序
    public void insert(int[] nums) {
        int n = nums.length;
        for (int i = 1; i < n; i++) {
            int num = nums[i];
            int j = i - 1;
            while (j >= 0 && num < nums[j]) {
                nums[j+1] = nums[j];
                j--;
            }
            nums[j+1]=num;
        }
    }

}
