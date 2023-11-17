package com.xkcoding.java.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/11/9 17:26
 */
@RestController
@RequestMapping("/test")
public class DemoAController {

    @RequestMapping(value="/get",method= RequestMethod.GET)
    public String get(){
        return "hello world";
    }

}
