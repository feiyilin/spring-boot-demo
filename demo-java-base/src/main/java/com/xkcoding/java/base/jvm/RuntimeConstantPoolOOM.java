package com.xkcoding.java.base.jvm;

import java.util.ArrayList;

/**
 * jdk6：‐Xms6M ‐Xmx6M ‐XX:PermSize=6M ‐XX:MaxPermSize=6M
 * jdk8：-Xms6M -Xmx6M -XX:MetaspaceSize=6M -XX:MaxMetaspaceSize=6M
 */
public class RuntimeConstantPoolOOM {
    public static void main(String[] args) {
        ArrayList<String> list = new ArrayList<>();
        for (int i = 0; i < 999999999; i++) {
            list.add("123");
        }
    }
}

