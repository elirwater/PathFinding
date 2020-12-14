package model.maps;

import model.blocks.BasicBlocks;

import java.util.ArrayList;

/**
 * The interface for representing a 2D map for a path-finding program.
 */
public interface MapInterface {

    void generateMapGrid();

    void generateMaze();

    ArrayList<ArrayList<BasicBlocks>> getMapGrid();

    ArrayList<BasicBlocks> getVisitedOrder();

    int getBlockDimensions();

    int getGridSize();

    ArrayList<BasicBlocks> getUnvisitedNeighbors(int x, int y);
}
