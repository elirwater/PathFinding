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
        int blockXSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / m.getGridSize().getX());
        int blockYSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / m.getGridSize().getY());


        int blockSize = Math.min(blockXSize, blockYSize);
        int gridWidth = blockSize * m.getGridSize().getX();
        int gridLength = blockSize * m.getGridSize().getY();





        return new Dimension(gridWidth + 30, gridLength + 30);
    }
}
