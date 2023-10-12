package com.xkcoding.helloworld.beikong.demo;

import org.springframework.web.client.RestTemplate;

import java.util.Base64;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2023/10/10 15:03
 */
public class UrlUpload {

    private static RestTemplate restTemplate = new RestTemplate();
    public static void main(String[] args) {
        String url = "https://minio.sciento.cn/st-insect/867814046366494/202304/20230420053559.jpg";
        byte[] responseBytes = restTemplate.getForObject(url, byte[].class);
        String encode = Base64.getEncoder().encodeToString(responseBytes);
//        UploadBase64ReqDto reqDto = new UploadBase64ReqDto();
//        reqDto.setBase64Data("");
//        // ResultDto<UploadResDto> bkhCarInfo = iossApi.uploadBase64File("bkh_carInfo", "/2023-10-09/20230420053559.png", reqDto);
//        // ResultDto<UploadResDto> carInfo = iossApi.uploadForContent("carInfo", "a/b/20230420053559.jpg", encode, "111.png");
//        ResultDto<UploadResDto> carInfo = iossApi.uploadForByte("carInfo", "car/dddd2.png", responseBytes, "");
    }
}
