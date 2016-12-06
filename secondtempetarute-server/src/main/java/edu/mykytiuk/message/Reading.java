package edu.mykytiuk.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reading {
    private Date timestamp;
    private Double value;

    public Reading() {
    }

    public Reading(Date timestamp, Double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Reading{" +
                "timestamp=" + timestamp +
                ", value=" + value +
                '}';
    }
}
