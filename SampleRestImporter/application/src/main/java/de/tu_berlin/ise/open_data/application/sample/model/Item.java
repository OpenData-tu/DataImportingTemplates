package de.tu_berlin.ise.open_data.application.sample.model;

import de.tu_berlin.ise.open_data.library.model.Schema;

/**
 * Created by ahmadjawid on 7/2/17.
 *All necessary fields for parsing data from source are defined here.
 * Final fields are not used when parsing from file.
 */

public class Item extends Schema {

    /**
     * The order of declaring fields should be the same as in response entity to parse correctly
     */
    private String timestamp;
    private double value;

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String getDelimiter() {
        return null;
    }
}
