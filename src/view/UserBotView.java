package view;

import controller.Features;
import model.ModelInterface;
import model.bots.BotInterface;
import model.maps.MapInterface;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;
import java.util.Map;


/**
 * Class for visualizing the UserBot for a path-finding model.
 */
public class UserBotView extends SimplePathFindingView {


    private JButton reset = new JButton("reset");
    private JButton newStart = new JButton("New Starter Position");


    public UserBotView(ModelInterface m) {
        super(m, 20);
    }


    @Override
    public void animate() throws InterruptedException {
        this.setLayout(new BorderLayout());

        JPanel p = new JPanel();
        p.add(this.reset);
        p.add(this.newStart);
        p.setLayout(new GridLayout(1, 2));
        p.setVisible(true);


        f.add(p, BorderLayout.NORTH);
        f.add(this, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);
        this.render();
    }

    @Override
    public void render() throws InterruptedException {
        this.setFocusable(true);
        BotInterface bot = (BotInterface) this.m.getBot();
        while (!bot.goalReached()) {
            Thread.sleep(1000 / this.tps);
            this.repaint();
        }
        if (bot.goalReached()) {
            JOptionPane.showMessageDialog(new JPanel(), "Goal Reached");
        }
    }

    /**
     * Adds the given features defined by the features interface to this view window.
     *
     * @param features features to be added.
     */
    public void addFeatures(Features features) {
        features.move();
        features.restart();
        features.generateNewStartingPos();
        features.autoNavigateToLocation();
    }


    /**
     * Adds all of the movement features to this view, with key listeners.
     */
    public void move() {
        Map<Character, Runnable> typedKeys = new HashMap<>();
        typedKeys.put('w', () -> {
            this.bot.move(this.bot.getCurrentRow() - 1, this.bot.getCurrentColumn());
        });

        typedKeys.put('a', () -> {
            this.bot.move(this.bot.getCurrentRow(), this.bot.getCurrentColumn() - 1);
        });

        typedKeys.put('s', () -> {
            this.bot.move(this.bot.getCurrentRow() + 1, this.bot.getCurrentColumn());
        });

        typedKeys.put('d', () -> {
            this.bot.move(this.bot.getCurrentRow(), this.bot.getCurrentColumn() + 1);
        });

        InteractiveKeyListener l = new InteractiveKeyListener();
        l.setKeyTypedMap(typedKeys);
        f.addKeyListener(l);
        f.setFocusable(true);
    }

    /**
     * Adds the restart feature to this view, with an action listener.
     */
    public void restart() {

        this.reset.addActionListener(actionEvent -> {
            int x = (int) bot.getStartingCords().getX();
            int y = (int) bot.getStartingCords().getY();
            bot.move(x, y);
            reset.setFocusable(false);
        });
    }


    /**
     * Adds the generate a new default starting position to this view, with an action listener.
     */
    public void generateNewStartingPos() {

        this.newStart.addActionListener(actionEvent -> {
            this.bot.generateRandomValidStartingPoint();
            int x = (int) bot.getStartingCords().getX();
            int y = (int) bot.getStartingCords().getY();
            bot.move(x, y);
            newStart.setFocusable(false);
        });
    }

}
