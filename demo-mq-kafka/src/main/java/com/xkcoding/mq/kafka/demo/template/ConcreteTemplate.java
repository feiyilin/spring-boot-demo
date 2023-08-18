package com.xkcoding.mq.kafka.demo.template;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/8/17 14:37
 */
public class ConcreteTemplate extends AbstractTemplate {

    @Override
    protected int abstractMethod(int num2) {
        return num2 + 50;
    }

    public static void main(String[] args) {
        AbstractTemplate concreteTemplate = new ConcreteTemplate();
        System.out.println(concreteTemplate.templateMethod(10));
    }
}


