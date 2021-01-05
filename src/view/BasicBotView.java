package view;

import controller.Features;
import model.ModelInterface;
import model.Pair;
import model.bots.BotInterface;
import model.maps.MapInterface;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.Hashtable;

public class BasicBotView extends SimplePathFindingView {
    private int numRuns = 0;

    public BasicBotView(ModelInterface m, int tps) {
        super(m, tps);
        f.setFocusable(true);
        bot.generateRun();

    }


    @Override
    public void render() throws InterruptedException {
        while (this.currentTick <= this.lastTick) {
            if (bot.goalReached()) {
                JOptionPane.showMessageDialog(new JPanel(), "Goal Reached");
                bot.generateGoal();
                generateNewRun();
            }
            if (this.tps != 0) {
                Thread.sleep(1000 / this.tps);
                this.move();
                this.repaint();
                this.currentTick++;
            } else {
                this.repaint();
            }
        }



    }

    public void generateNewRun() {
        bot.generateRun();
        numRuns ++;
        currentTick = 0;
        lastTick = lastTick + bot.getFinalTick(numRuns);
    }


    /**
     * Adds the given features defined by the features interface to this view window.
     *
     * @param features features to be added.
     */
    public void addFeatures(Features features) {
        features.autoNavigateToLocation();
        features.adjustTickSpeed();
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
                generateNewRun();
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


    public void adjustTickSpeed() {
        slider.setMinimum(0);
        slider.setMaximum(300);

        slider.setMajorTickSpacing(150);
        slider.setPaintTicks(true);



        Hashtable labelTable = new Hashtable();
        labelTable.put(0, new JLabel("Pause"));
        labelTable.put(150, new JLabel("Medium"));
        labelTable.put(300, new JLabel("Speedy"));

        slider.setLabelTable( labelTable );
        slider.setPaintLabels(true);

        slider.addChangeListener(changeEvent -> {
            JSlider source = (JSlider)changeEvent.getSource();
            this.tps = source.getValue();
        });


    }




    public void move() {
        try {
            Pair runMove = bot.getRunCurrentBotPos(this.numRuns, currentTick);
            bot.move(runMove.getX(), runMove.getY());
        }
        catch (Exception e) {

        }
    }

}
