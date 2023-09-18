package com.xkcoding.mq.kafka.controller;

import com.xkcoding.mq.kafka.api.TestFeignInterface;
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
public class TestController implements TestFeignInterface {
    @Override
    @GetMapping("/getAgeByName")
    public String getAgeByName() {
        // 获取端口号
        return System.getProperty("server.port");
    }
}
