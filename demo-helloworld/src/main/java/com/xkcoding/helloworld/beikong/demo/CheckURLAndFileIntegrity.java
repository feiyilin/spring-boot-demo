package com.xkcoding.helloworld.beikong.demo;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class CheckURLAndFileIntegrity {

    public static boolean isURLReachable(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("HEAD");
            int responseCode = connection.getResponseCode();

            // 如果响应代码是2xx，则表示可以成功读取
            return (responseCode >= 200 && responseCode < 300);
        } catch (IOException e) {
            // 网络连接或读取错误
            e.printStackTrace();
        }
        return false;
    }

    public static boolean isFileComplete(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 获取文件总大小
            int totalSize = connection.getContentLength();

            // 获取输入流
            try (InputStream inputStream = connection.getInputStream()) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                int downloadedSize = 0;

                // 读取文件内容
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    downloadedSize += bytesRead;
                }

                // 判断文件是否完整
                return downloadedSize == totalSize;
            }
        } catch (IOException e) {
            // 网络连接或读取错误
            e.printStackTrace();
        }
        return false;
    }

    public static String calculateMD5(InputStream inputStream) throws NoSuchAlgorithmException, IOException {
        MessageDigest md5 = MessageDigest.getInstance("MD5");
        byte[] buffer = new byte[1024];
        int bytesRead;

        while ((bytesRead = inputStream.read(buffer)) != -1) {
            md5.update(buffer, 0, bytesRead);
        }

        // 转换为十六进制字符串
        StringBuilder md5StringBuilder = new StringBuilder();
        for (byte b : md5.digest()) {
            md5StringBuilder.append(String.format("%02x", b));
        }

        return md5StringBuilder.toString();
    }

    public static void main(String[] args) {
        String urlToCheck = "http://61.183.157.58:12000/320.mp4";

        if (isURLReachable(urlToCheck)) {
            System.out.println("URL is reachable and can be read.");

            if (isFileComplete(urlToCheck)) {
                System.out.println("File is complete and can be trusted.");
            } else {
                System.out.println("File is incomplete or encountered an issue during download.");
            }
        } else {
            System.out.println("URL is not reachable or cannot be read.");
        }
    }
}