package view;

import model.ModelInterface;
import model.Pair;

import javax.swing.*;
import java.util.ArrayList;

public class BasicBotView extends SimplePathFindingView {

    public BasicBotView(ModelInterface m, int tps) {
        super(m, tps);
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
    /////ALSO NEED A WAY TO GRAB THE LENGTH OF A SPECIIFC RUN......

    public void move() {


        try {
            Pair runMove = bot.getRunCurrentBotPos(currentTick);
            bot.move(runMove.getX(), runMove.getY());
        }
        catch (Exception e) {

        }



    }

}
