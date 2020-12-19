package model.types;

import model.Pair;
import model.maps.DepthFirstSearchMap;
import model.maps.MapInterface;

/**
 * Factory class for instantiating different types of maps.
 */
public class MapTypes {


    public MapInterface choose(String mapType, Pair gridSize) {

        switch (mapType) {
            case "DepthFirstSearch":
                return new DepthFirstSearchMap(gridSize);
            default:
                throw new IllegalArgumentException("Must choose an existing map type");
        }
    }
}
