package com.xkcoding.java.base.generics;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/11/9 11:04
 */
public class DemoA implements InterfaceDemoA<String> {


    @Override
    public void get(String s) {
        System.out.println(this.getClass().getName());
    }

    public static void main(String[] args) {
        InterfaceDemoA<String> demoA = new DemoA();
        demoA.get("123");
    }
}
