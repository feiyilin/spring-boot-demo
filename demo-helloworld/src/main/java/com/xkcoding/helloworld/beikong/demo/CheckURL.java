package com.xkcoding.helloworld.beikong.demo;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class CheckURL {

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

    public static boolean isFileDownloading(String urlString) {
        try {
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            // 获取文件总大小
            int totalSize = connection.getContentLength();

            // 获取已下载大小
            int downloadedSize = connection.getInputStream().available();

            // 如果已下载大小小于总大小，可以认为文件正在下载中
            return downloadedSize < totalSize;
        } catch (IOException e) {
            // 网络连接或读取错误
            e.printStackTrace();
        }
        return false;
    }

    public static void main(String[] args) {
        String urlToCheck = "http://61.183.157.58:12000/319.mp4";

        if (isURLReachable(urlToCheck)) {
            System.out.println("URL is reachable and can be read.");

            if (isFileDownloading(urlToCheck)) {
                System.out.println("File is currently being downloaded.");
            } else {
                System.out.println("File is not in the downloading state.");
            }
        } else {
            System.out.println("URL is not reachable or cannot be read.");
        }
    }
}