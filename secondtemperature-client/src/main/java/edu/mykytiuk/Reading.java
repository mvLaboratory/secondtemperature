package edu.mykytiuk;

import java.util.Date;

public class Reading implements Comparable<Reading> {
    private Long timestamp;
    private Double value;

    public Reading() {
    }

    public Reading(Long timestamp, Double value) {
        this.timestamp = timestamp;
        this.value = value;
    }

    public Long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Long timestamp) {
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


    @Override
    public int compareTo(Reading o) {
        if (o.getTimestamp() > this.getTimestamp())
            return 1;
        else if (o.getTimestamp() > this.getTimestamp())
            return -1;

        return 0;
    }
}
