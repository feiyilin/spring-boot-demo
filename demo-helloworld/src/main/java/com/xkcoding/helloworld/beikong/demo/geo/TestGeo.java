package com.xkcoding.helloworld.beikong.demo.geo;

/**
 * TODO
 *
 * @author feiyilin
 * @date 2024/7/15 14:40
 */
public class TestGeo {

    public static void main(String[] args) {
        Boundary boundary = new Boundary(Double.parseDouble("113.9229337689279"),
                Double.parseDouble("22.670080045211478"), Double.parseDouble("119.01401464750289"),Double.parseDouble("25.436870057123347"));

        double distance = boundary.getDistance();
    }
}
