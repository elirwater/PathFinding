package controller;

import model.BasicPathFindingModel;
import view.BasicBotView;


//specific controller for specific model

public class BasicBotController implements Features {

    BasicPathFindingModel m;
    BasicBotView v;



    public BasicBotController(BasicPathFindingModel m) { /////////////////NOT HOW YOU SHOULD HANDLE THIS.........
        this.m = m;
        this.v = new BasicBotView(m, 180);
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
}
