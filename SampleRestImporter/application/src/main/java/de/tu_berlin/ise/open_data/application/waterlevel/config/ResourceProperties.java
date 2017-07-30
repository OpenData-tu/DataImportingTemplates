package de.tu_berlin.ise.open_data.application.waterlevel.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ahmadjawid on 6/30/17.
 * *Properties of resource are provided here.
 * These values are overridden by application.properties overridden by environment variables
 */

@Configuration
@ConfigurationProperties("resource")
public class ResourceProperties {

    private String url = "https://www.pegelonline.wsv.de/webservices/rest-api/v2/stations/BONN/W/measurements.json?start=P15D";

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
