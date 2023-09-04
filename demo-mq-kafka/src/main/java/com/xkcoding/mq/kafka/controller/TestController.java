package com.xkcoding.mq.kafka.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/9/1 14:21
 */
@RestController
@RequestMapping("/testController")
public class TestController {

    @Autowired
    private WebClient.Builder webClient;

    @GetMapping("/testWebClient")
    public String testWebClient(String name) {

        // 测试提交
        String result = webClient
                .build()
                .get()
                .uri("http://feiyilinDemo2/demo2/testController/getAgeByName?name=" + name)
                .retrieve()
                .bodyToMono(String.class)
                .block();
        return "feiyilinDemo ---------- " + result;
    }
}
