package view;

import controller.Features;
import model.ModelInterface;
import model.Pair;
import model.bots.BotInterface;
import model.maps.MapInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class BasicBotView extends SimplePathFindingView {
    private int numRuns = 0;

    public BasicBotView(ModelInterface m, int tps) {
        super(m, tps);
        f.setFocusable(true);
        bot.generateRun();

        ///NOT THE WAY TO DO THIS.

    }

    @Override
    public void render() throws InterruptedException {
        while (this.currentTick <= this.lastTick) {
            if (bot.goalReached()) {
                JOptionPane.showMessageDialog(new JPanel(), "Goal Reached");
                break;
            }
            Thread.sleep(1000 / this.tps);
            this.move();
            this.repaint();
            this.currentTick ++;
        }


    }

    /**
     * Adds the given features defined by the features interface to this view window.
     *
     * @param features features to be added.
     */
    public void addFeatures(Features features) {
        features.autoNavigateToLocation();
    }



    public void autoNavigateToLocation() {

        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent mouseEvent) {
                int x = mouseEvent.getX();
                int y = mouseEvent.getY();

                MapInterface map = (MapInterface) m.getMap();


                int blockXSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth()
                    / map.getGridSize().getX());
                int blockYSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight()
                    / map.getGridSize().getY());


                int blockSize = Math.min(blockXSize, blockYSize);

                int rowBlock = y / blockSize;
                int columnBlock = x / blockSize;

                bot.setGoal(rowBlock, columnBlock);



                bot.generateRun();
                numRuns ++;
                currentTick = 0;
                lastTick = lastTick + bot.getFinalTick(numRuns);
            }

            @Override
            public void mousePressed(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseReleased(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseEntered(MouseEvent mouseEvent) {

            }

            @Override
            public void mouseExited(MouseEvent mouseEvent) {

            }
        });
    }
    /////ALSO NEED A WAY TO GRAB THE LENGTH OF A SPECIIFC RUN......

    public void move() {


        try {
            Pair runMove = bot.getRunCurrentBotPos(this.numRuns, currentTick);
            bot.move(runMove.getX(), runMove.getY());
        }
        catch (Exception e) {

        }



    }

}
