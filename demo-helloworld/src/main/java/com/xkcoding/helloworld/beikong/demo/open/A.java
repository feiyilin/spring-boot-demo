package com.xkcoding.helloworld.beikong.demo.open;

import okhttp3.*;

import java.io.IOException;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2024/4/23 17:11
 */
public class A {

    public static void main(String[] args) throws IOException {
        OkHttpClient client = new OkHttpClient().newBuilder()
                .build();
        MediaType mediaType = MediaType.parse("application/json");
        RequestBody body = RequestBody.create(mediaType, "{\r\n    \"siteCode\":\"123\",\r\n\"longitude\":\"123.22\",\r\n\"latitude\":\"222.33\"\r\n}");
        Request request = new Request.Builder()
                .url("http://121.32.24.86:40002/3.0/sweeper/v1/open/robot/job/sweep/fixed?appID=1500007772&timestamp=1713860317&sign=ZDdjODUzNGFmMDk0OWZmYTBkZjY4YWQ0OTRmOWY5MGU")
                .method("POST", body)
                .addHeader("Authorization", "0b02b914a14564c15fcb607b9125bcf1")
                .addHeader("User-Agent", "Apifox/1.0.0 (https://apifox.com)")
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "*/*")
                .addHeader("Host", "121.32.24.86:40002")
                .addHeader("Connection", "keep-alive")
                .build();
        Response response = client.newCall(request).execute();
    }
}
