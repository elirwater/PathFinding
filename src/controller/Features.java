package controller;

import java.awt.event.KeyListener;

/**
 * Interface for representing different features for an PathFindingViewInterface
 */
public interface Features extends ControllerInterface {

    /**
     * Moves the user-controlled path-finding bot using key listeners and a key map
     * that correspond to different direction
     */
    void move();

    /**
     * Resets the view and model to initial state (bot goes back to original position).
     */
    void restart();

    /**
     * Generates new random starting position
     */
    void generateNewStartingPos();
}