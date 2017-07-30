package de.tu_berlin.ise.open_data.application.flat.model;

/**
 * Created by ahmadjawid on 7/18/17.
 * Used to create an object which can be added as the extra field of our importer schema.
 */
public class Extra {

    private String location;

    public Extra(String location) {

        this.location = location;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
