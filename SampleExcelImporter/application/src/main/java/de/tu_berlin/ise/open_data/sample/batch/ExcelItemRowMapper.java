package de.tu_berlin.ise.open_data.sample.batch;

import de.tu_berlin.ise.open_data.sample.model.Item;
import org.springframework.batch.item.excel.RowMapper;
import org.springframework.batch.item.excel.support.rowset.RowSet;

/**
 * Created by ahmadjawid on 7/1/17.
 * /**
 * Excel document cannot be read using the Item class.
 * custom row mapper to map columns of excel file to attributes of the class {@link Item}
 * @return ExcelItemRowMapper
 */
public class ExcelItemRowMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(RowSet rowSet) throws Exception {

        Item item = new Item();

        //Index 0 and 1 each indicating a column of excel file.
        //Read each index with its respected attribute.
        item.setLocation(rowSet.getColumnValue(0));
        item.setMeanValue(rowSet.getColumnValue(1));

        return item;
    }
}
