package com.xkcoding.helloworld.beikong.demo;

import cn.hutool.http.HttpRequest;
import com.alibaba.fastjson.JSON;
import org.apache.commons.codec.binary.Base64;
import org.springframework.util.DigestUtils;

import java.util.*;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/9/19 11:23
 */
public class HttpPostDemo {

    public static void main(String[] args) {
        // 替换成你的实际参数值
        String appId = "1500007738";
        String secret = "tzl3V04ZdZdwzzDGOQmsTREHpALsiAAp";
        long timestamp = 1646921760; // 当前时间戳

        // 创建参数映射
        Map<String, String> params = new HashMap<>();
        params.put("appID", appId);
        params.put("timestamp", String.valueOf(timestamp));

        // 对参数进行字典排序
        List<String> paramList = new ArrayList<>(params.keySet());
        Collections.sort(paramList);

        // 构建待签名的字符串
        StringBuilder signatureBase = new StringBuilder();
        for (String paramName : paramList) {
            signatureBase.append(paramName).append("=").append(params.get(paramName)).append("&");
        }
        signatureBase.append("secret=").append(secret);

        // 计算 MD5 摘要
        // md5加密
        String signature = DigestUtils.md5DigestAsHex(signatureBase.toString().getBytes());

        // 使用 Base64 进行编码
        String base64Signature = Base64.encodeBase64URLSafeString(signature.getBytes());

        Map<String, String> map = new HashMap<>();
        map.put("startTime", "2023-08-10 13:10:00");
        map.put("endTime", "2023-09-11 13:00:00");
        String body = HttpRequest.post("https://202.96.164.143:10443/car/open/lg-dbk/car/roadSegmentList?appID=1500007738&timestamp=1695027425&sign=ODYxYzJjMzMxN2MxOTRiYmFjZDllM2ZmMmZhNmE5ZWU")
                .header("Content-Type", "application/json")
                .body(JSON.toJSONString(map))
                .execute().body();
        System.out.println(body);
        // System.out.println("Signature: " + base64Signature);
    }
}
