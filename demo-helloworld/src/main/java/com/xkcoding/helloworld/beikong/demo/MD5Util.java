package com.xkcoding.helloworld.beikong.demo;

import com.beust.jcommander.internal.Lists;
import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.stream.Collectors;

public class MD5Util {
    public static void main(String[] args) {
        genSign("appID=1500007761&timestamp=1699857098", "QOTJtm171eGG3NunuWqVmirh2yRxf82e");
    }
    private MD5Util() {
    }

    public static boolean verifySign(String queryStr, String secret, String sign, boolean isStrict) {
        if (!StringUtils.isBlank(queryStr) && !StringUtils.isBlank(secret) && !StringUtils.isBlank(sign)) {
            String sorted = sort(queryStr, isStrict);
            String encrypt = sorted + "&secret=" + secret;
            return sign.equals(encrypt(encrypt));
        } else {
            return false;
        }
    }

    public static String genSign(String queryStr, String secret) {
        return genSign(queryStr, secret, true);
    }

    public static String genSign(String queryStr, String secret, boolean isStrict) {
        if (!StringUtils.isBlank(queryStr) && !StringUtils.isBlank(secret)) {
            String sorted = sort(queryStr, isStrict);
            String encrypt = sorted + "&secret=" + secret;
            return encrypt(encrypt);
        } else {
            return null;
        }
    }

    public static String encrypt(String str) {
        if (StringUtils.isBlank(str)) {
            return null;
        } else {
            String md5 = DigestUtils.md5Hex(str);
            return Base64.encodeBase64URLSafeString(md5.getBytes(StandardCharsets.UTF_8));
        }
    }

    private static String sort(String str, boolean isStrict) {
        if (StringUtils.isBlank(str)) {
            return null;
        } else {
            List<String> params = Lists.newArrayList(StringUtils.split(str, "&"));
            String sorted;
            if (isStrict) {
                sorted = (String)params.stream().filter((o) -> {
                    return !o.contains("sign");
                }).sorted().collect(Collectors.joining("&"));
            } else {
                sorted = (String)params.stream().filter((o) -> {
                    return o.contains("appID") || o.contains("timestamp");
                }).sorted().collect(Collectors.joining("&"));
            }

            return sorted;
        }
    }
}
