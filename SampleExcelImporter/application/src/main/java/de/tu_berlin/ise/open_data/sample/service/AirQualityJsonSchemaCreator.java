package de.tu_berlin.ise.open_data.sample.service;

import de.tu_berlin.ise.open_data.sample.model.Item;

import de.tu_berlin.ise.open_data.library.model.Schema;
import de.tu_berlin.ise.open_data.library.service.JsonSchemaCreator;
import de.tu_berlin.ise.open_data.library.service.JsonStringBuilder;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;


/**
 * Created by ahmadjawid on 6/9/17.
 * Implementation of {@link JsonSchemaCreator}
 */
@Service
public class AirQualityJsonSchemaCreator implements JsonSchemaCreator {

    /**
     * Get an objects which is extended from {@link Schema} class
     * and converts it to json
     * @param schema
     * @return String
     * */
    @Override
    public String create(Schema schema) throws JSONException {
        Item item = (Item) schema;

        JsonStringBuilder jsonBuilder = new JsonStringBuilder();

        jsonBuilder.setSourceId("some source id here");
        jsonBuilder.setDevice(item.getLocation());
        jsonBuilder.setTimestamp(LocalDateTime.now().toString());
        jsonBuilder.setLocation("55", "10");

        jsonBuilder.setLicense("some license here");

        //Set sensor
        jsonBuilder.setSensor("someMeasurement", "some sensor", item.getMeanValue());

        return jsonBuilder.build();


    }
}
