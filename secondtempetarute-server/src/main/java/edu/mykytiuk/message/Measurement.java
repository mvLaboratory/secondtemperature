package edu.mykytiuk.message;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Arrays;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Measurement {
    private SensorGroup[] sensors;

    //always empty, suppose String
    private String[] alerts;

    public Measurement() {
    }

    public Measurement(SensorGroup[] sensors, String[] alerts) {
        this.sensors = sensors;
        this.alerts = alerts;
    }

    public SensorGroup[] getSensors() {
        if (sensors == null) {
            SensorGroup[] temp = new SensorGroup[0];
            return temp;
        }
        return sensors;
    }

    public void setSensors(SensorGroup[] sensors) {
        this.sensors = sensors;
    }

    public String[] getAlerts() {
        return alerts;
    }

    public void setAlerts(String[] alerts) {
        this.alerts = alerts;
    }

    @Override
    public String toString() {
        return "Measurement{" +
                "sensors=" + Arrays.toString(sensors) +
                ", alerts=" + Arrays.toString(alerts) +
                '}';
    }
}
