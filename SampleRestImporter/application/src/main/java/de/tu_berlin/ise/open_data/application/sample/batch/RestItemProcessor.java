package de.tu_berlin.ise.open_data.application.sample.batch;

import de.tu_berlin.ise.open_data.application.sample.model.Item;
import de.tu_berlin.ise.open_data.library.service.JsonSchemaCreator;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by ahmadjawid on 7/2/17.
 * Processing includes converting Java Objects to json string objects according our defined schema
 */
public class RestItemProcessor implements ItemProcessor<Item, String> {


    @Autowired
    @Qualifier("itemJsonCreator")
    private JsonSchemaCreator jsonSchemaCreator;

    @Override
    public String process(Item item) throws Exception {

        //A valid json objects is created and returned
        return jsonSchemaCreator.create(item);

    }
}
