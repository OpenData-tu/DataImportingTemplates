package de.tu_berlin.ise.open_data.sample.batch;

import de.tu_berlin.ise.open_data.sample.model.Item;
import org.springframework.batch.item.excel.poi.PoiItemReader;

/**
 * Created by ahmadjawid on 7/1/17.
 * Reader which specifies how to read from the source
 *
 */
public class ExcelItemReader extends PoiItemReader<Item> {


    /**
     * Read the items one by one and return them.
     * Stop when null is returned
     * @return Item
     * */
    @Override
    public Item read() throws Exception {

        Item item = super.read();
        try {

            //Stop if reading empty line
            if (item.getLocation().equals(" ") || item.getLocation().equals("")) {
                return null;

            }
        } catch (NullPointerException e) {
            return null;
        }

        return item;
    }
}
