package de.tu_berlin.ise.open_data.application.waterlevel.service;

import de.tu_berlin.ise.open_data.library.model.Schema;
import de.tu_berlin.ise.open_data.library.service.JsonStringBuilder;
import de.tu_berlin.ise.open_data.application.waterlevel.model.Item;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import de.tu_berlin.ise.open_data.library.service.JsonSchemaCreator;

/**
 * Created by ahmadjawid on 7/2/17.
 * * Implementation of {@link JsonSchemaCreator}
 */
@Service
public class ItemJsonCreator implements JsonSchemaCreator {


    /**
     * Get an objects which is extended from {@link de.tu_berlin.ise.open_data.library.model.Schema} class
     * and converts it to json
     * @param schema
     * @return String
     * */
    @Override
    public String create(Schema schema) throws JSONException {
        Item item = (Item) schema;

        JsonStringBuilder jsonBuilder = new JsonStringBuilder();

        jsonBuilder.setSourceId("some source id here");
        jsonBuilder.setDevice("some device name here");
        jsonBuilder.setTimestamp(item.getTimestamp());
        jsonBuilder.setLocation(55 + "", 11 + "");
        jsonBuilder.setLicense("license here");

        jsonBuilder.setSensor("someMeasurement", "someSensor", item.getValue() + "");


        return jsonBuilder.build();
    }
}
