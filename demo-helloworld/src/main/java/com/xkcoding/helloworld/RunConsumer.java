package com.xkcoding.helloworld;


import org.apache.consumer.sdk.RunTyMqListener;
import org.apache.consumer.sdk.web.MsgResult;
import org.springframework.stereotype.Component;

/**
 * 接收数据
 *
 * @author Admin
 */
@Component
public class RunConsumer implements RunTyMqListener {
    @Override
    public void onMessage(MsgResult message) {
        // message 就是获取到的数据
        //System.out.print(message);
    }
}
