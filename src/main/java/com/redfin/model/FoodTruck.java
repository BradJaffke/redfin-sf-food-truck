package com.redfin.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

//NEED @JsonProperty to specify what variables each of mine apply to

@JsonIgnoreProperties(ignoreUnknown = true)
public class FoodTruck implements Serializable {

    @JsonProperty("dayorder")
    private String dayOrder;

    @JsonProperty("starttime")
    private String startTime;

    @JsonProperty("endtime")
    private String endTime;

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDayOrder() {
        return dayOrder;
    }

    public void setDayOrder(String dayOrder) {
        this.dayOrder = dayOrder;
    }
}
