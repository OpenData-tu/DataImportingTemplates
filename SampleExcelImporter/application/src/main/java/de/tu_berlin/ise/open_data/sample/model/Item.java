package de.tu_berlin.ise.open_data.sample.model;

import de.tu_berlin.ise.open_data.library.model.Schema;

/**
 * Created by ahmadjawid on 6/29/17.
 *All necessary fields for parsing data from source are defined here.
 * Final fields are not used when parsing from file.
 */
public class Item extends Schema {


    private String location;
    private String meanValue;

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getMeanValue() {
        return meanValue;
    }

    public void setMeanValue(String meanValue) {
        this.meanValue = meanValue;
    }

    @Override
    public String getDelimiter() {
        return null;
    }
}
