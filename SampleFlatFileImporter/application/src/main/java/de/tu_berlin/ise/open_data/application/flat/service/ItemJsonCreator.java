package de.tu_berlin.ise.open_data.application.flat.service;

import de.tu_berlin.ise.open_data.application.flat.model.Extra;
import de.tu_berlin.ise.open_data.application.flat.model.Item;
import de.tu_berlin.ise.open_data.library.model.Schema;
import de.tu_berlin.ise.open_data.library.service.JsonSchemaCreator;
import org.json.JSONException;
import org.springframework.stereotype.Service;

import de.tu_berlin.ise.open_data.library.service.JsonStringBuilder;

/**
 * Created by ahmadjawid on 6/9/17.
 * Implementation of {@link JsonSchemaCreator}
 */
@Service
public class ItemJsonCreator implements JsonSchemaCreator {

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

        jsonBuilder.setSourceId(item.getSourceId());
        jsonBuilder.setDevice(item.getSensorId());
        jsonBuilder.setTimestamp(item.getTimestamp());
        jsonBuilder.setLocation(item.getLat(), item.getLon());
        jsonBuilder.setLicense(item.getLicense());

        jsonBuilder.setSensor("pressure", item.getSensorType(), item.getPressure());
        jsonBuilder.setSensor("altitude", item.getSensorType(), item.getAltitude());
        jsonBuilder.setSensor("pressureSeaLevel", item.getSensorType(), item.getPressureSeaLevel());
        jsonBuilder.setSensor("temperature", item.getSensorType(), item.getTemperature());
        jsonBuilder.setSensor("humidity", item.getSensorType(), item.getHumidity());


        Extra extra = new Extra(item.getLocation());
        //A java object can be passed as the extra field of to be created json object.
        //'extra' object is automatically parsed to json and is appended to the main json object
        jsonBuilder.setExtra(extra);

        return jsonBuilder.build();
    }
}
