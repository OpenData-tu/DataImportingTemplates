package de.tu_berlin.ise.open_data.application.flat.model;

import de.tu_berlin.ise.open_data.library.model.Schema;

/**
 * Created by ahmadjawid on 5/21/17.
 * All necessary fields for parsing data from source are defined here.
 * Final fields are not used when parsing from file.
 */

public class Item extends Schema {

    /**
     * The order of declaring fields should be the same as in file to parse correctly
     */

    private String sensorId;
    private String sensorType;
    private String location;
    private String lat;
    private String lon;
    private String timestamp;
    private String pressure;
    private String altitude;
    private String pressureSeaLevel;
    private String temperature;
    private String humidity;

    /**
     * Declared as final to skip the field when parsing csv file
     */
    private final String sourceId = "luftdaten_info";

    /**
     * Declared as final to skip the field when parsing csv file
     */
    private final String license = "find out";



    public String getSensorId() {
        return sensorId;
    }

    public void setSensorId(String sensorId) {
        this.sensorId = sensorId;
    }

    public String getSensorType() {
        return sensorType;
    }

    public void setSensorType(String sensorType) {
        this.sensorType = sensorType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLon() {
        return lon;
    }

    public void setLon(String lon) {
        this.lon = lon;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getPressure() {
        return pressure;
    }

    public void setPressure(String pressure) {
        this.pressure = pressure;
    }

    public String getAltitude() {
        return altitude;
    }

    public void setAltitude(String altitude) {
        this.altitude = altitude;
    }

    public String getPressureSeaLevel() {
        return pressureSeaLevel;
    }

    public void setPressureSeaLevel(String pressureSeaLevel) {
        this.pressureSeaLevel = pressureSeaLevel;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public String getHumidity() {
        return humidity;
    }

    public void setHumidity(String humidity) {
        this.humidity = humidity;
    }


    public String getSourceId() {
        return sourceId;
    }

    public String getLicense() {
        return license;
    }

    @Override
    public String getDelimiter() {
        return ";";
    }

}
