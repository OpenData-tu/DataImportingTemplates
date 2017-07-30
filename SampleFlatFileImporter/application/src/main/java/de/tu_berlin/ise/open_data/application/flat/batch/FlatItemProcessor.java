package de.tu_berlin.ise.open_data.application.flat.batch;

import de.tu_berlin.ise.open_data.application.flat.model.Item;
import de.tu_berlin.ise.open_data.application.flat.service.JsonSchemaCreator;
import de.tu_berlin.ise.open_data.library.service.ApplicationService;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by ahmadjawid on 5/21/17.
 * Processing includes converting Java Objects to json string objects according our defined schema
 */

public class FlatItemProcessor implements ItemProcessor<Item, String> {


    @Autowired
    private ApplicationService applicationService;

    @Autowired
    private JsonSchemaCreator jsonSchemaCreator;

    @Override
    public String process(Item item) throws Exception {
        item.setTimestamp(applicationService.toISODateTimeFormat(item.getTimestamp()));

        //A valid json objects is created and returned
        return jsonSchemaCreator.create(item);
    }

}