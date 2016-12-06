package edu.mykytiuk.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Sensor {
    private String id;
    private String type;
    private String unit;
    private String outdoors;
    private String balcony;
    private List<Reading> readings;

    public Sensor() {
    }

    public Sensor(String id, String type, String unit, String outdoors, String balcony, List<Reading> readings) {
        this.id = id;
        this.type = type;
        this.unit = unit;
        this.outdoors = outdoors;
        this.balcony = balcony;
        this.readings = readings;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getOutdoors() {
        return outdoors;
    }

    public void setOutdoors(String outdoors) {
        this.outdoors = outdoors;
    }

    public String getBalcony() {
        return balcony;
    }

    public void setBalcony(String balcony) {
        this.balcony = balcony;
    }

    public List<Reading> getReadings() {
        if (readings == null) {
            List<Reading> temp = new ArrayList<>();
            return temp;
        }
        return readings;
    }

    public void setReadings(List<Reading> readings) {
        this.readings = readings;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", unit='" + unit + '\'' +
                ", outdoors='" + outdoors + '\'' +
                ", balcony='" + balcony + '\'' +
                ", readings=" + readings.toString() +
                '}';
    }
}
