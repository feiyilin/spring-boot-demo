package com.xkcoding.java.base.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/10/26 11:38
 */
public class ReflectDemo {

    public static void main(String[] args) throws Exception {
        Class<?> clazz = Class.forName("com.xkcoding.java.base.reflect.DemoA");
        DemoA demoA = (DemoA) clazz.newInstance();
        demoA.setA("a");
        demoA.setB("b");
        System.out.println(demoA);
        Method[] methods = clazz.getMethods();
        for (Method method : methods) {
            String name = method.getName();
        }
        Method[] declaredMethods = clazz.getDeclaredMethods();
        Method getName = clazz.getDeclaredMethod("getA");
        getName.setAccessible(true);
        String invoke = (String)getName.invoke(demoA);
        Field a = clazz.getDeclaredField("a");
        Object o = a.get(demoA);
    }
}
