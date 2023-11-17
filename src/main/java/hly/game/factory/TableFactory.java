package hly.game.factory;

import hly.game.Items.Drawable;
import hly.game.Items.Table;
import org.json.simple.JSONObject;

/**
 * @author Leo01234
 * @version 1.0
 */
public class TableFactory implements Factory{
    @Override
    public Drawable createDrawable(JSONObject jsonObject) {
        // reading the Table section:
        JSONObject jsonTable = (JSONObject) jsonObject.get("Table");

        // reading a value from the table section
        String tableColour = (String) jsonTable.get("colour");

        // reading a coordinate from the nested section within the table
        // note that the table x and y are of type Long (i.e. they are integers)
        Long tableX = (Long) ((JSONObject) jsonTable.get("size")).get("x");
        Long tableY = (Long) ((JSONObject) jsonTable.get("size")).get("y");

        // getting the friction level.
        // This is a double which should affect the rate at which the balls slow down
        Double tableFriction = (Double) jsonTable.get("friction");

        return new Table(tableX,tableY,tableColour,tableFriction);
    }
}
