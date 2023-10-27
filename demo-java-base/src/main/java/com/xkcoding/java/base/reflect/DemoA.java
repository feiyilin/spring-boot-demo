package com.xkcoding.java.base.reflect;

import lombok.Data;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/10/26 11:39
 */
@Data
public class DemoA {

    private String a;

    private String b;

    private String getName() {
        return "myName";
    }

}
