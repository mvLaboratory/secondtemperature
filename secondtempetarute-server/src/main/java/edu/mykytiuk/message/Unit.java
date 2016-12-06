package edu.mykytiuk.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Date;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Unit {
    private String location;
    private Measurement measurements;
    private Date timestamp;

    public Unit() {
    }

    public Unit(String location, Measurement measurements, Date timestamp) {
        this.location = location;
        this.measurements = measurements;
        this.timestamp = timestamp;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Measurement getMeasurements() {
        return measurements;
    }

    public void setMeasurements(Measurement measurements) {
        this.measurements = measurements;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "Unit{" +
                "location='" + location + '\'' +
                ", measurements=" + measurements +
                ", timestamp=" + timestamp +
                '}';
    }
}
