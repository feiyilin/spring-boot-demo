package com.xkcoding.helloworld.beikong.demo.geo;

import lombok.Getter;

/**
 * @createDate 2021-12-3
 * @author 梁朝伟
 * @updateDate 2021-12-3
 * @desc 边的实体（线段） (两点)
 */
@Getter
public class Boundary {
    private static final double EARTH_RADIUS = 6378.137;
    //前点经度
    private final double frontLongitude;

    //前点纬度
    private final double frontLatitude;

    //后点经度
    private final double afterLongitude;

    //后点纬度
    private final double afterLatitude;

    public Boundary(double frontLongitude, double frontLatitud, double afterLongitude, double afterLatitude) {
        this.frontLongitude = frontLongitude;
        this.frontLatitude = frontLatitud;
        this.afterLongitude = afterLongitude;
        this.afterLatitude = afterLatitude;
    }

    /**
     * 求长度
     * @return
     */
    public double getDistance() {
//        GlobalCoordinates source = new GlobalCoordinates(frontLongitude, frontLatitude);
//        GlobalCoordinates target = new GlobalCoordinates(afterLongitude, afterLatitude);
//
//        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.WGS84, source, target).getEllipsoidalDistance();
//        return HttpRequest.getDistance(new Point2D.Double(frontLongitude, frontLatitude), new Point2D.Double(afterLongitude, afterLatitude));
//        return LngLonUtil.getSphereDistance(frontLongitude,frontLatitude,afterLongitude,afterLatitude);
        //Latitude 存放的是 经度
        return LngLonUtil.getGeodesyDistance(frontLatitude,frontLongitude,afterLatitude,afterLongitude);
    }

    public double getGeodesyDistance() {
//        GlobalCoordinates source = new GlobalCoordinates(frontLongitude, frontLatitude);
//        GlobalCoordinates target = new GlobalCoordinates(afterLongitude, afterLatitude);
//
//        return new GeodeticCalculator().calculateGeodeticCurve(Ellipsoid.WGS84, source, target).getEllipsoidalDistance();
//        return HttpRequest.getDistance(new Point2D.Double(frontLongitude, frontLatitude), new Point2D.Double(afterLongitude, afterLatitude));
        return LngLonUtil.getGeodesyDistance(frontLatitude,frontLongitude,afterLatitude,afterLongitude);
    }

}