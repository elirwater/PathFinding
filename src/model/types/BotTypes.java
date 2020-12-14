package model.types;

import model.bots.BasicBot;
import model.bots.BotInterface;
import model.bots.UserBot;
import model.maps.MapInterface;

/**
 * Factory class for instantiating different types of bots.
 */
public class BotTypes {

    public BotInterface choose(String mapType, MapInterface m) {

        switch (mapType) {
            case "BasicBot":
                return new BasicBot(m);
            case "UserBot":
                return new UserBot(m);
            default:
                throw new IllegalArgumentException("Must choose an existing map type");
        }

    }
}
