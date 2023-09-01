package com.xkcoding.mq.kafka.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/9/1 14:30
 */
@RestController
@RequestMapping("/testController")
public class TestController {

    @GetMapping("/getAgeByName")
    public String getAgeByName(String name) {
        if ("小明".equals(name)) {
            return "feiyilinDemo2 + 18";
        }
        if ("小红".equals(name)) {
            return "feiyilinDemo2 + 20";
        }
        return "feiyilinDemo2 + 0";
    }
}
