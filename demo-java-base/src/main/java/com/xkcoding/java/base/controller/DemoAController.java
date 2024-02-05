package com.xkcoding.java.base.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    public static void main(String[] args) {
        long startDownloadTime = 1706099081902L;
        long time = new Date().getTime();
        if (((time - startDownloadTime)) > (1000 * 60 * 60)) {
            System.out.println("超过1小时");
        }
        List list = new ArrayList();
        list.add(1);
        list.add("2");
        for (Object o : list) {
            System.out.println(o);
        }
    }

    private static void test2(int i) {
        if (i == 2) {
            return;
        }
        System.out.println(i);
    }

}
