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

    public static void main(String[] args) {
        List<Fruit> fruits = new ArrayList<>();
        List<Apple> apples = new ArrayList<>();
        fruits.add(new Apple());
        print(fruits);
        print(apples);
        test(new Apple(), new Orange());
        // test(new Apple(), new Apple2());
    }

    public static void print(List<? extends Fruit> fruits) {
        for (Fruit fruit : fruits) {
            System.out.println(fruit);
        }
    }

    public static <T extends Fruit> void test(T dest, T src) {

    }
}
