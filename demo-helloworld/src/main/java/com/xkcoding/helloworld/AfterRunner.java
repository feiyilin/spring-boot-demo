package com.xkcoding.helloworld;


import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component
@Order(value = Integer.MIN_VALUE)
public class AfterRunner implements ApplicationRunner {

//    @Autowired
//    RunBaseProperties runBaseProperties;


    @Override
    public void run(ApplicationArguments args) throws Exception {
        try {
            //String mqs = runBaseProperties.getMqServer();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
