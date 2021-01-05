package controller;

import model.BasicPathFindingModel;
import view.BasicBotView;


/**
 * Class for representing for controlling several different path-finding bots.
 */
public class BasicBotController implements Features {

    BasicPathFindingModel m;
    BasicBotView v;



    public BasicBotController(BasicPathFindingModel m) {
        this.m = m;
        this.v = new BasicBotView(m, 30);
    }

    @Override
    public void go() throws InterruptedException {
        v.addFeatures(this);
        v.animate();
    }

    @Override
    public void move() {

    }

    @Override
    public void restart() {

    }

    @Override
    public void generateNewStartingPos() {

    }

    @Override
    public void autoNavigateToLocation() {
        v.autoNavigateToLocation();
    }

    @Override
    public void adjustTickSpeed() {
        v.adjustTickSpeed();
    }
}
