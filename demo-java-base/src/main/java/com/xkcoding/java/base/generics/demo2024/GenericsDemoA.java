package com.xkcoding.java.base.generics.demo2024;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2024/2/4 14:15
 */
public class GenericsDemoA<T> {

    public GenericsDemoA(T t) {
        this.t = t;
    }

    public GenericsDemoA() {
    }

    private T t;

    public void setT(T t) {
        this.t = t;
    }

    public T getT() {
        return this.t;
    }

    public static <T> void getTest() {

    }

}
