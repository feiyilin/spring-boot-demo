package com.xkcoding.java.base.leetcode;

/**
 * @author feiyilin
 * @date 2023/10/16 16:38
 */
/*
给定一个整数数组 nums，将数组中的元素向右轮转 k 个位置，其中 k 是非负数。

示例 1:

输入: nums = [1,2,3,4,5,6,7], k = 3
输出: [5,6,7,1,2,3,4]
解释:
向右轮转 1 步: [7,1,2,3,4,5,6]
向右轮转 2 步: [6,7,1,2,3,4,5]
向右轮转 3 步: [5,6,7,1,2,3,4]
示例 2:

输入：nums = [-1,-100,3,99], k = 2
输出：[3,99,-1,-100]
解释:
向右轮转 1 步: [99,-1,-100,3]
向右轮转 2 步: [3,99,-1,-100]

提示：

1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105

进阶：

尽可能想出更多的解决方案，至少有 三种 不同的方法可以解决这个问题。
你可以使用空间复杂度为 O(1) 的 原地 算法解决这个问题吗？
 */
public class 中等_轮转数组_189 {

    public static void main(String[] args) {
        int[] nums = {1,2};
        int k = 3;
        my_rotate(nums, k);
    }

    public static void my_rotate(int[] nums, int k) {

        if (nums.length < k) {
            k %= nums.length;
        }
        int[] result = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            int l = i - k;
            if (l < 0) {
                // 从后面拿
                result[i] = nums[nums.length - k + i];
            } else {
                result[i] = nums[l];
            }
        }
        // 可以直接用
        // System.arraycopy(result, 0, nums,0, nums.length);
        for (int i = 0; i < result.length; i++) {
            nums[i] = result[i];
        }
    }
    /*
       反转三次，实现要求
     */
    public static void other_rotate(int[] nums, int k) {

        int length = nums.length;
        k %= length;
        // 反转整个数组
        reverse(nums, 0, length -1);
        // 反转0，k-1元素
        reverse(nums, 0, k - 1);
        // 反转k-1，length-1元素
        reverse(nums, k, length -1);
    }

    private static void reverse(int[] nums, int start, int end) {

        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}
