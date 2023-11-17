package hly.game.factory;

import hly.game.Items.Drawable;
import org.json.simple.JSONObject;

/**
 * @author Leo01234
 * @version 1.0
 */
public interface Factory {
    Drawable createDrawable(JSONObject jsonObject);
}
