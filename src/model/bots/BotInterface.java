package model.bots;

import model.Pair;
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
     * Grabs the starting block coordinates for a bot.
     * @return coordinates
     */
    Pair getStartingCords();

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


    Pair getRunCurrentBotPos(int run, int tick);

    /**
     * Grabs the final tick of this a bot run
     * @return final tick
     */
    int getFinalTick(int run);

    /**
     * Determines if the bot has reached the goal.
     * @return true if goal reached
     */
    boolean goalReached();

    /**
     * Sets the goal.
     */
    void setGoal(int x, int y);


    Pair getGoal();
}
