package com.xkcoding.java.base;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.Executors;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/11/9 11:37
 */
public class BaseDemo {

    private static String b;

    public static void main(String[] args) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse("2024-03-04 16:16:15.71667");
        } catch (Exception e) {
        }
        float i = 1.1F;
        float j = 1.1F;
        System.out.println(i == j);
        //test();
        String a = "Hello";
        System.out.println("a.hashCode() = " + a.hashCode());

        String key1 = "hello";
        String key2 = "world";

        int hash1 = key1.hashCode();
        int hash2 = key2.hashCode();

        System.out.println("Key 1: " + key1 + ", Hash Code: " + hash1);
        System.out.println("Key 2: " + key2 + ", Hash Code: " + hash2);

        List<String> list = Arrays.asList("1", "2");
//        list.add("123");
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.addLast("123");
        for (String s : linkedList) {

        }
        // 获取 Java 线程管理 MXBean
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        // 不需要获取同步的 monitor 和 synchronizer 信息，仅获取线程和线程堆栈信息
        ThreadInfo[] threadInfos = threadMXBean.dumpAllThreads(false, false);
        // 遍历线程信息，仅打印线程 ID 和线程名称信息
        for (ThreadInfo threadInfo : threadInfos) {
            System.out.println("[" + threadInfo.getThreadId() + "] " + threadInfo.getThreadName());
        }
        Executors.newCachedThreadPool();
    }

    public static String test() {
        HashMap<String, String > map  = new HashMap<String, String>(){{
            put("a","b");
            put("b","b");
        }};
        String put = map.put("1", "1");
        map.put("2", "3");
        return b;
    }
}
