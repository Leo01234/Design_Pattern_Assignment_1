package hly.game.factory;

import hly.game.Items.Drawable;
import hly.game.Items.ball.Ball;
import hly.game.ballbuilder.ConcreteBallBuilder;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.util.Iterator;

/**
 * @author Leo01234
 * @version 1.0
 */
public class BallFactory implements Factory{
    Iterator iterator = null;
    /**
     *
     * @param jsonObject parsed json object from config file
     * @return Return a Ball at a time. If there is no Ball left, then return null.
     */
    @Override
    public Drawable createDrawable(JSONObject jsonObject) {
        if (iterator==null) {
            // reading the "Balls" section:
            JSONObject jsonBalls = (JSONObject) jsonObject.get("Balls");

            // reading the "Balls: ball" array:
            JSONArray jsonBallsBall = (JSONArray) jsonBalls.get("ball");
            iterator = jsonBallsBall.iterator();
        }

        if(!iterator.hasNext()){
            return null;
        }
        // reading next Ball from the array:
        JSONObject jsonBall = (JSONObject) iterator.next();

        ConcreteBallBuilder cbb = new ConcreteBallBuilder();
        // the ball colour is a String
        cbb.createBall((String) jsonBall.get("colour"));

        // the ball position, velocity, mass are all doubles
        JSONObject jsonPosition = (JSONObject) jsonBall.get("position");
        Double xPos = (Double) (jsonPosition).get("x");
        Double yPos = (Double) (jsonPosition).get("y");
        cbb.setPosition(xPos,yPos);

        cbb.setRadius();

        JSONObject jsonVelocity = (JSONObject) jsonBall.get("velocity");
        Double xVel = (Double) (jsonVelocity).get("x");
        Double yVel = (Double) (jsonVelocity).get("y");
        cbb.setPosition(xVel,yVel);

        Double mass = (Double) jsonBall.get("mass");
        cbb.setMass(mass);

        return cbb.getResult();
    }
}
