package com.xkcoding.mq.kafka.demo.template;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/8/17 14:30
 */
public abstract class AbstractTemplate implements TemplateInterface {

    @Override
    public int templateMethod(int num) {
        int num2 = this.commonMethod1(num);
        return this.abstractMethod(num2);
    }

    protected int abstractMethod(int num2) {
        return ++num2;
    }


    private int commonMethod1(int num) {
        return ++num;
    }
}
