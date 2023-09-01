package com.xkcoding.mq.kafka.demo;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/8/11 9:36
 */
public class MyDemo {

    public static void main(String[] args) {

        a();
        System.out.println();
    }

    private static void a() {
        return;
    }

    public int lengthOfLongestSubstring(String s) {

        String[] strings = s.split("");
        int currentLength = 1;
        int resultLength = 1;
        for (int i = 0; i < strings.length - 1; i++) {
            String current = strings[i];
            String next = strings[i + 1];
            if (!current.equals(next)) {
            }


        }
        return 1;
    }

    public static int[] maxSlidingWindow(int[] nums, int k) {

        for (int i = 0; i < nums.length; i++) {

        }
        return null;
    }

}
