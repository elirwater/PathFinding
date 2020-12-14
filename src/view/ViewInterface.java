package view;

/**
 * The interface for representing the view of a path-finding model.
 */
public interface ViewInterface {

    /**
     * Runs the animation of a given path-finding model.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    void animate() throws InterruptedException;

    /**
     * Animates a specific subset (usually a certain motion) of the provided path-finding model.
     *
     * @throws InterruptedException if the thread is interrupted
     */
    void render() throws InterruptedException;
}
