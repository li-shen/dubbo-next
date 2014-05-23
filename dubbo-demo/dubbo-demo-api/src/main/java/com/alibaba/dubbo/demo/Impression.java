package com.alibaba.dubbo.demo;

import java.io.Serializable;

/**
 * @author lishen
 */
public class Impression implements Serializable {

    private String id;
    private double bidFloor;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getBidFloor() {
        return bidFloor;
    }

    public void setBidFloor(double bidFloor) {
        this.bidFloor = bidFloor;
    }
}
