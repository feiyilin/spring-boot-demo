package com.xkcoding.java.base.generics.demo2024;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2024/2/4 14:16
 */
public class GenericsDemoTest {

    public static final String NUMBER = "123";

    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();
        List<Apple> apples = new ArrayList<>();
        fruits.add(new Apple());
        print(fruits);
        print(apples);
        test(new Apple(), new Orange());
        // test(new Apple(), new Apple2());
        Apple apple = new Apple();
        apple.test();
        Class<?> appleClass = Apple.class;

    }

    public static void print(List<?> fruits) {
        String a = NUMBER;
        fruits.add(null);
        //fruits.add(new Apple());
        for (Object fruit : fruits) {

        }
//        for (Fruit fruit : fruits) {
//            System.out.println(fruit);
//        }
    }

    public static <T extends Fruit> void test(T dest, T src) {
        String string = dest.toString();
    }
}
