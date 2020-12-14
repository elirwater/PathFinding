package model.bots;

import model.blocks.BasicBlocks;

import java.util.ArrayList;

/**
 * Interface for representing a path-finding bot in a path-finding program.
 *
 * @param <R> The type of Run the bot creates
 */
public interface BotInterface<R> {

    /**
     * Generates a randomized valid starting point for the bot in the map, called by default constructor automatically.
     */
    void generateRandomValidStartingPoint();

    /**
     * Generates a run for this bot.
     */
    void generateRun();

    /**
     * Grabs the list of runs for a bot.
     * @return
     */
    ArrayList<R> getRuns();

    /**
     * Grabs the best run for a bot (shorted number of blocks traveled to successful goal)
     * @return run
     */
    R getBestRun();

    /**
     * Grabs the starting block coordinates for a bot.
     * @return coordinates
     */
    ArrayList<Integer> getStartingCords();

    /**
     * Grabs the current x or row position for a bot.
     * @return row position
     */
    int getCurrentRow();

    /**
     * Grabs the current y or column position for a bot.
     * @return column position.
     */
    int getCurrentColumn();

    /**
     * Moves a bot in it's map
     * @param blockRow  new block position.
     * @param blockColumn new block position.
     */
    void move(int blockRow, int blockColumn);


    ArrayList<Integer> getRunVisit(int tick);

    /**
     * Grabs the final tick of this a bot run
     * @return final tick
     */
    int getFinalTick();

    /**
     * Determines if the bot has reached the goal.
     * @return true if goal reached
     */
    boolean goalReached();


    ArrayList<Integer> getGoal();
}
