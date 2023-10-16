package com.xkcoding.java.base.leetcode;

import java.util.HashMap;
import java.util.Map;

/**
 * @author feiyilin
 * @date 2023/10/16 10:37
 */
/*
给定一个大小为 n 的数组 nums ，返回其中的多数元素。多数元素是指在数组中出现次数 大于 ⌊ n/2 ⌋ 的元素。

你可以假设数组是非空的，并且给定的数组总是存在多数元素。

示例 1：

输入：nums = [3,2,3]
输出：3
示例 2：

输入：nums = [2,2,1,1,1,2,2]
输出：2

提示：
n == nums.length
1 <= n <= 5 * 104
-109 <= nums[i] <= 109

进阶：尝试设计时间复杂度为 O(n)、空间复杂度为 O(1) 的算法解决此问题。
 */
public class 简单_多数元素_169 {

    public static void main(String[] args) {
        int[] nums = {1};
        my1_majorityElement(nums);
    }

    public static int my1_majorityElement(int[] nums) {

        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            int num = nums[i];
            Integer count = map.get(num);
            if (count == null) {
                count = 1;
            } else {
                count ++;
            }
            map.put(num, count);
        }
        int resultValue = 0;
        int resultKey = 0;
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            Integer key = entry.getKey();
            Integer value = entry.getValue();
            if (value > resultValue) {
                resultValue = value;
                resultKey = key;
            }
        }
        return map.get(resultKey);
    }

    /**
     *
     * Boyer-Moore投票算法是一种用于寻找数组中出现次数超过一半的主要元素（Majority Element）的高效算法。其基本思想是通过消除不同的元素，最终留下的元素就有可能是超过一半的主要元素。
     * <p>
     * 算法的主要步骤如下：
     * <p>
     * 初始化候选众数(candidate)和计数器(count)：将数组的第一个元素设为候选众数，计数器初始化为1。
     * <p>
     * 遍历数组：从数组的第二个元素开始遍历。
     * <p>
     * 如果当前元素与候选众数相同，将计数器加一。
     * 如果当前元素与候选众数不同，将计数器减一。
     * 如果计数器减到零，说明之前的候选众数不是主要元素，需要选择当前元素作为新的候选众数，并将计数器重置为1。
     * 返回候选众数：遍历完成后，留下的候选众数即为可能的主要元素。
     * <p>
     * Boyer-Moore投票算法的精髓在于，当某个元素的出现次数超过数组长度的一半时，它的出现次数会比其他所有元素的出现次数加起来还要多。所以，通过在遍历过程中不断消除不同的元素，最终剩下的元素有可能是主要元素。
     * <p>
     * 这个算法的时间复杂度是O(n)，空间复杂度是O(1)，非常高效。同时，该算法还可以应用于寻找数组中出现次数超过1/3或1/4等比例的主要元素。
     */
    public static int my2_majorityElement(int[] nums) {

        int count = 1;
        int result = nums[0];
        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (num == result) {
                count++;
            } else if (count == 0) {
                result = num;
                count = 1;
            } else {
                count--;
            }
        }
        return result;
    }
}
