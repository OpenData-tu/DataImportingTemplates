package de.tu_berlin.ise.open_data.application.sample.batch;

import de.tu_berlin.ise.open_data.application.sample.model.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemReader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

/**
 * Created by ahmadjawid on 7/2/17.
 * Includes a read method which specifies how to read data from the api
 */
class RestItemReader implements ItemReader<Item> {

    private static final Logger LOGGER = LoggerFactory.getLogger(RestItemReader.class);

    private final String apiUrl;
    private final RestTemplate restTemplate;

    private int nextWaterLevelIndex;
    private List<Item> itemList;

    //Set parameters
    RestItemReader(String apiUrl, RestTemplate restTemplate) {
        this.apiUrl = apiUrl;
        this.restTemplate = restTemplate;
        nextWaterLevelIndex = 0;
    }

    /**
     * Read the items one by one and return them.
     * Stop when null is returned
     * @return Item
     * */
    @Override
    public Item read() throws Exception {

        if (waterLevelDataIsNotInitialized()) {
            itemList = waterLevelDataFromAPI();
        }

        Item nextItem = null;

        //If next item exists get it.
        if (nextWaterLevelIndex < itemList.size()) {
            nextItem = itemList.get(nextWaterLevelIndex);
            nextWaterLevelIndex++;
        }

        return nextItem;
    }

    private boolean waterLevelDataIsNotInitialized() {
        return this.itemList == null;
    }

    private List<Item> waterLevelDataFromAPI() {

        //Get the whole list of items using an array of Item from the apiUrl
        ResponseEntity<Item[]> response = restTemplate.getForEntity(apiUrl, Item[].class);
        //Put all items inside an array
        Item[] items = response.getBody();
        LOGGER.debug("Found {} items", items.length);

        //Return a list of items
        return Arrays.asList(items);
    }
}
