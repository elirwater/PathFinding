package controller;

/**
 * Interface for controlling a path-finding bot
 */
public interface ControllerInterface {

    /**
     * Instantiates both the selected path-finding bot and the bot's selected visual representation.
     * @throws InterruptedException
     */
    void go() throws InterruptedException;

}
