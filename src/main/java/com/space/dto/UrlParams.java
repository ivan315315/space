package com.space.dto;

import com.space.controller.ShipOrder;
import com.space.model.ShipType;

import java.util.Date;

public class UrlParams {
    private static long timeKoeff = 3600000;
    private Integer id;
    private String name;              //list   count
    private String planet;            //list   count
    private ShipType shipType;        //list   count
    private Date after;               //list   count
    private Date before;              //list   count
    private Boolean isUsed;           //list   count
    private Double minSpeed;          //list   count
    private Double maxSpeed;          //list   count
    private Integer minCrewSize;          //list   count
    private Integer maxCrewSize;          //list   count
    private Double minRating;         //list   count
    private Double maxRating;         //list   count
    private Integer pageNumber;           //list
    private Integer pageSize;             //list
    private ShipOrder order;          //list

    public UrlParams(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed,
                     Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating,
                     Double maxRating, Integer pageNumber, Integer pageSize, ShipOrder order) {
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        if (after != null){
            this.after = new Date(after - timeKoeff);
        }
        if (before != null){
            this.before = new Date(before - timeKoeff);
        }
        this.isUsed = isUsed;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.minCrewSize = minCrewSize;
        this.maxCrewSize = maxCrewSize;
        this.minRating = minRating;
        this.maxRating = maxRating;
        this.pageNumber = pageNumber;
        this.pageSize = pageSize;
        this.order = order;
    }

    public UrlParams(String name, String planet, ShipType shipType, Long after, Long before, Boolean isUsed,
                     Double minSpeed, Double maxSpeed, Integer minCrewSize, Integer maxCrewSize, Double minRating, Double maxRating) {
        this.name = name;
        this.planet = planet;
        this.shipType = shipType;
        if (after != null){
            this.after = new Date(after - timeKoeff);
        }
        if (before != null){
            this.before = new Date(before - timeKoeff);
        }
        this.isUsed = isUsed;
        this.minSpeed = minSpeed;
        this.maxSpeed = maxSpeed;
        this.minCrewSize = minCrewSize;
        this.maxCrewSize = maxCrewSize;
        this.minRating = minRating;
        this.maxRating = maxRating;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPlanet() {
        return planet;
    }

    public void setPlanet(String planet) {
        this.planet = planet;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public void setShipType(ShipType shipType) {
        this.shipType = shipType;
    }

    public Date getAfter() {
        return after;
    }

    public void setAfter(Date after) {
        this.after = after;
    }

    public Date getBefore() {
        return before;
    }

    public void setBefore(Date before) {
        this.before = before;
    }

    public Boolean getUsed() {
        return isUsed;
    }

    public void setUsed(Boolean used) {
        isUsed = used;
    }

    public Double getMinSpeed() {
        return minSpeed;
    }

    public void setMinSpeed(Double minSpeed) {
        this.minSpeed = minSpeed;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Integer getMinCrewSize() {
        return minCrewSize;
    }

    public void setMinCrewSize(Integer minCrewSize) {
        this.minCrewSize = minCrewSize;
    }

    public Integer getMaxCrewSize() {
        return maxCrewSize;
    }

    public void setMaxCrewSize(Integer maxCrewSize) {
        this.maxCrewSize = maxCrewSize;
    }

    public Double getMinRating() {
        return minRating;
    }

    public void setMinRating(Double minRating) {
        this.minRating = minRating;
    }

    public Double getMaxRating() {
        return maxRating;
    }

    public void setMaxRating(Double maxRating) {
        this.maxRating = maxRating;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public ShipOrder getOrder() {
        return order;
    }

    public void setOrder(ShipOrder order) {
        this.order = order;
    }

    @Override
    public String toString() {
        return "UrlParams{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", planet='" + planet + '\'' +
                ", shipType=" + shipType +
                ", after=" + after +
                ", before=" + before +
                ", isUsed=" + isUsed +
                ", minSpeed=" + minSpeed +
                ", maxSpeed=" + maxSpeed +
                ", minCrewSize=" + minCrewSize +
                ", maxCrewSize=" + maxCrewSize +
                ", minRating=" + minRating +
                ", maxRating=" + maxRating +
                ", pageNumber=" + pageNumber +
                ", pageSize=" + pageSize +
                ", order=" + order +
                '}';
    }
}
