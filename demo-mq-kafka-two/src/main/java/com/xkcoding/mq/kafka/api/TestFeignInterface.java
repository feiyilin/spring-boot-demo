package com.xkcoding.mq.kafka.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/9/18 14:42
 */
@FeignClient(name = "feiyilinDemo2", path = "/demo2/testController")
public interface TestFeignInterface {

    @GetMapping("/getServerPort")
    String getServerPort();
}
