package model;

import model.blocks.BasicBlocks;

import java.util.ArrayList;

/**
 * Interface for representing the model of a path-finding program.
 * @param <B> Type of Bot being used
 * @param <M> Type of Map being used
 */
public interface ModelInterface<B, M> {


    /**
     * Generates a bot for this model, using constructor parameters.
     */
    void generateBot();

    /**
     * Generates a map for this model, using constructor parameters.
     */
    void generateMap();

    /**
     * Grabs the bot for this model.
     *
     * @return bot
     */
    B getBot();

    /**
     * Grabs the map for this model.
     *
     * @return map
     */
    M getMap();

    /**
     * Runs the generation of the bot runs for this model.
     *
     * @param numRuns The number of runs that the bot should attempt
     */
    void generateBotRuns(int numRuns);



    /**
     * Grabs the current visited block from a Run
     *
     * @return BasicBlock (block visited during this tick)
     */
    BasicBlocks getRunVisit(int tick);

    /**
     *
     * Grabs the last tick of the state (of the run).
     * @return
     */
    int getFinalTick();
    
}
