package com.xkcoding.helloworld;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class DbEntity {
    public  String sourceDistrictCode;
    public  String sourceStreetCode;
    public  String traportCompanyCode;
    public  String transportId;
    public  String transportCompany;
    public  String sourceStreet;
    public  String sourceDistrict;
    public  Double outGarbagePlantWeight;
    public LocalDateTime outGarbagePlantTime;
    public  Double inGarbagePlantWeight;
    public  LocalDateTime inGarbagePlantTime;
    public  String icNo;
    public  String garbagePlantType;
    public  String garbageSmallType;
    public  String garbagePlantCode;
    public  String garbagePlantName;
    public  Double garbageNetWeight;
    public  String garbageBigType;
    public  String carNo;
    public  String carType;
}
