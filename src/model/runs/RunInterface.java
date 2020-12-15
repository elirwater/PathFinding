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



}
