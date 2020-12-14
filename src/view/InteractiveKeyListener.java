package view;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Map;

/**
 * Class for implementing the keyListener for a view which needs it.
 */
public class InteractiveKeyListener implements KeyListener {

    private Map<Character, Runnable> keyTypedMap;



    public void setKeyTypedMap(Map<Character, Runnable> map) {
        keyTypedMap = map;}



    @Override
    public void keyTyped(KeyEvent e) {
        if (keyTypedMap.containsKey(e.getKeyChar())) {
            keyTypedMap.get(e.getKeyChar()).run();
        }

    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
