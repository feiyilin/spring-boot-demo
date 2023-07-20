package com.xkcoding.java.base.functional;

import java.util.function.Function;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/7/20 15:27
 */
public class MyFunction implements Function<String, String> {

    private String name;

    private Integer age;

    public MyFunction(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String apply(String t) {
        return name + age;
    }
}
