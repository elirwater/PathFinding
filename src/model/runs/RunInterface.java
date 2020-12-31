package model.runs;

import model.Pair;
import model.blocks.BasicBlocks;

import java.util.ArrayList;

/**
 * Interface for representing a single path-finding run for a bot.
 */

public interface RunInterface {

    /**
     * Generates a run for a bot.
     */
    void generateRun();

    /**
     * Grabs the given run.
     * @return List of Basic Blocks
     */
    ArrayList<Pair> getRun();

    /**
     * Generates the path from the goal to the starting block that is displayed by the view
     * to visualize the best path that the algorithm takes
     */
    void generatePathFromGoal();

    /**
     * Clears the block cache, resetting all of the block values used in pathfinding to their
     * default state.
     */
    void clearBlockCache();



}
