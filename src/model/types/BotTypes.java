package model.types;

import model.bots.BFSBot;
import model.bots.DFSBot;
import model.bots.BotInterface;
import model.bots.UserBot;
import model.maps.MapInterface;

/**
 * Factory class for instantiating different types of bots.
 */
public class BotTypes {

    public BotInterface choose(String mapType, MapInterface m) {

        switch (mapType) {
            case "DFSBot":
                return new DFSBot(m);
            case "BFSBot":
                return new BFSBot(m);
            case "UserBot":
                return new UserBot(m);
            default:
                throw new IllegalArgumentException("Must choose an existing map type");
        }

    }
}
