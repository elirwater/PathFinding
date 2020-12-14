package view;

import model.ModelInterface;
import model.blocks.BasicBlocks;
import model.bots.BotInterface;

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
            ArrayList<Integer> runMove = bot.getRunVisit(currentTick);
            bot.move(runMove.get(0), runMove.get(1));
        }
        catch (Exception e) {

        }



    }

}
