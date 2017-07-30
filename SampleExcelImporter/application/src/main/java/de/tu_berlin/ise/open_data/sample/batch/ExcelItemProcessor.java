package de.tu_berlin.ise.open_data.sample.batch;

import de.tu_berlin.ise.open_data.sample.model.Item;
import de.tu_berlin.ise.open_data.library.service.JsonSchemaCreator;
import org.json.JSONException;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ahmadjawid on 7/1/17.
 * Processing includes converting Java Objects to json string objects according our defined schema
 */

public class ExcelItemProcessor implements ItemProcessor<Item, String> {

    @Autowired
    private JsonSchemaCreator jsonSchemaCreator;

    @Override

    public String process(Item item) throws JSONException {



        //A valid json objects is created and returned
        return jsonSchemaCreator.create(item);
    }
}
