package com.xkcoding.java.base;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * LdapDemoApplication Ldap demo 启动类
 *
 * @author fxbin
 * @version v1.0
 * @since 2019-08-26 0:37
 */
@SpringBootApplication
@RestController
public class JavaBaseDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaBaseDemoApplication.class, args);
    }

    @RequestMapping(value="/get",method= RequestMethod.GET)
    public String get() throws InterruptedException {
        System.out.println("hello world");
        System.out.println(Thread.currentThread().getId());
        Thread.sleep(10000);
        return "hello world";
    }
}
