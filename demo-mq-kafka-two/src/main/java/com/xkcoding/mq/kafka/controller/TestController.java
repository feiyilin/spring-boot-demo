package com.xkcoding.mq.kafka.controller;

import com.xkcoding.mq.kafka.api.TestFeignInterface;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("${server.port}")
    private String serverPort;

    @Override
    @GetMapping("/getServerPort")
    public String getServerPort() {
        // 获取端口号
        return serverPort;
    }
}
