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
        // 当前时间戳
        long timestamp = System.currentTimeMillis() / 1000;
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
        String md5Sign = DigestUtils.md5DigestAsHex(signatureBase.toString().getBytes());

        // 使用 Base64 进行编码
        String base64Sign = Base64.encodeBase64URLSafeString(md5Sign.getBytes());

        Map<String, String> map = new HashMap<>();
        map.put("startTime", "2023-09-01 13:10:00");
        map.put("endTime", "2023-09-11 13:00:00");
        String body = HttpRequest.post("https://202.96.164.143:10443/car/open/lg-dbk/car/roadSegmentList?appID=" + appId + "&timestamp="+timestamp + "&sign=" + base64Sign)
                .header("Content-Type", "application/json")
                .body(JSON.toJSONString(map))
                .execute().body();
        System.out.println(body);
        // System.out.println("Signature: " + base64Signature);
    }
}
