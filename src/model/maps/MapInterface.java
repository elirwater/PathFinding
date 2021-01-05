package model.maps;

import model.Pair;
import model.blocks.BasicBlocks;

import java.util.ArrayList;

/**
 * The interface for representing a 2D map for a path-finding program.
 */
public interface MapInterface {

    /**
     * Generates the map-grid.
     */
    void generateMapGrid();

    /**
     * Transforms the map-grid into a maze.
     */
    void generateMaze();

    /**
     * Returns the map-grid.
     * @return 2D array list that represents the map-grid.
     */
    ArrayList<ArrayList<BasicBlocks>> getMapGrid();

    /**
     * Returns the visited order of how the maze was created for visualization purposes.
     * @return the order that the blocks were recurses to when generating the maze (w/o back-tracking)
     */
    ArrayList<BasicBlocks> getVisitedOrder();

    /**
     * Returns the size of the map-grid.
     * @return size of map-grid as a pair
     */
    Pair getGridSize();

    /**
     * Grabs a list of basic blocks that represent the neighbors of a given basic block that,
     * used by the bots when traversing through the maze.
     * have not yet been visited.
     * @param x row pos of selected basic block to search neighbors
     * @param y column pos of selected basic block to search neighbors
     * @return  list of unvisited neighbors
     */
    ArrayList<BasicBlocks> getUnvisitedNeighbors(int x, int y);

    /**
     * Grabs a block in the map-grid at the specified position.
     * @param x row position
     * @param y column position
     * @return selected block if it exists.
     * @throws IllegalArgumentException if the basic block doesn't exist within this map-grid.
     */
    BasicBlocks getBlock(int x, int y);

}
