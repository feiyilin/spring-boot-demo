package com.xkcoding.java.base.functional;

import java.util.Date;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/7/20 15:27
 */
public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("MyFunctionImpl.run" + new Date());
    }
}
