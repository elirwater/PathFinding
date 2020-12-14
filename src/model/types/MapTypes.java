package model.types;

import model.maps.DepthFirstSearchMap;
import model.maps.MapInterface;

/**
 * Factory class for instantiating different types of maps.
 */
public class MapTypes {


    public MapInterface choose(String mapType, int blockSize, int numBlocks) {

        switch (mapType) {
            case "DepthFirstSearch":
                return new DepthFirstSearchMap(blockSize, numBlocks);
            default:
                throw new IllegalArgumentException("Must choose an existing map type");
        }
    }
}
