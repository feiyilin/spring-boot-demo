package com.xkcoding.mq.kafka;

import com.xkcoding.mq.kafka.config.NacosWeightRuleConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * <p>
 * 启动器
 * </p>
 *
 * @author yangkai.shen
 * @date Created in 2019-01-07 14:43
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients(basePackages = "com.xkcoding.mq.kafka.api")
@LoadBalancerClients({
        @LoadBalancerClient(name = "feiyilinDemo2", configuration = NacosWeightRuleConfiguration.class)
})
public class SpringBootDemoMqKafkaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootDemoMqKafkaApplication.class, args);
    }

}

