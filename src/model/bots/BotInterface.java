package model.bots;

import model.Pair;

/**
 * Interface for representing a path-finding bot in a path-finding program.
 *
 */
public interface BotInterface {


    /**
     * Generates a starting point fort a bot that is within the map-grid AND is not blocked (a wall).
     */
    void generateRandomValidStartingPoint();

    /**
     * Generates a run for a bot.
     */
    void generateRun();

    /**
     * Grabs the starting cords for a given bot's run.
     * @return starting cords (position on the map-grid of a basic block)
     * @throws IllegalStateException if the starting cords have not yet been instantiated or something fails.
     */
    Pair getStartingCords() throws IllegalStateException;

    /**
     * Grabs the current row of the bot on the map-grid.
     * @return current row
     * @throws IllegalStateException if the current row pos has not yet been instantiated or something fails.
     */
    int getCurrentRow() throws IllegalStateException;

    /**
     * Grabs the current column of the bot on the map-grid.
     * @return current column
     * @throws IllegalStateException if the current column cords has not yet been instantiated or something fails.
     */
    int getCurrentColumn() throws IllegalStateException;

    /**
     * Moves the bot to the position specified by the method's arguments on the map-grid.
     * @param blockRow  row position to move
     * @param blockColumn   column position to move
     */
    void move(int blockRow, int blockColumn);

    /**
     * Grabs the position of a bot at a certain run during a certain move in that run for visualization purposes.
     * @param run   which run
     * @param tick  which move in that run
     * @return  pair of the position parameterized by the method's arguments on the map-grid
     * @throws IllegalArgumentException if the specified run or tick is out of bounds of the array list.
     */
    Pair getRunCurrentBotPos(int run, int tick) throws IllegalArgumentException;

    /**
     * Grabs the final tick of a certain run to be used for visualization purposes.
     * @param run   which run to grab the final tick from
     * @return  final tick
     */
    int getFinalTick(int run);

    /**
     * Checks if the bot has reached the current bot-goal.
     * @return true if goal has been reached
     * @throws IllegalStateException if the goal has not yet been instantiated.
     */
    boolean goalReached() throws IllegalStateException;

    /**
     * Set's the goal of a bot on the map-grid.
     * @param x row position of goal
     * @param y column position of goal
     */
    void setGoal(int x, int y);

    /**
     * Grabs the goal-cords of a bot.
     * @return goal-cords
     * @throws IllegalStateException if the goal has not yet been instantiated or something fails.
     */
    Pair getGoal() throws IllegalStateException;

    /**
     * Generates a goal-block for a bot.
     */
    void generateGoal();
}
