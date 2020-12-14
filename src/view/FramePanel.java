package view;

import model.bots.BotInterface;
import model.maps.MapInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Class for abstracting the common functionality of the JFrame.
 */
public class FramePanel extends JFrame {

    MapInterface m;

    FramePanel(MapInterface m) {
        this.m = m;
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    }



    @Override
    public Dimension getPreferredSize() {
        int dimensions = (int) (m.getBlockDimensions() + Math.sqrt(m.getGridSize()));
        return new Dimension(dimensions, dimensions);
    }
}
