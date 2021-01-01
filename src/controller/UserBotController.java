package controller;

import model.BasicPathFindingModel;
import view.UserBotView;

public class UserBotController implements Features {


    BasicPathFindingModel m;
    UserBotView v;



    public UserBotController(BasicPathFindingModel m) {
        this.m = m;
        this.v = new UserBotView(this.m);
    }

    @Override
    public void go() throws InterruptedException {
        v.addFeatures(this);
        v.animate();
    }

    @Override
    public void move() {
        this.v.move();


    }

    @Override
    public void restart() {
        this.v.restart();

    }

    @Override
    public void generateNewStartingPos() {
        this.v.generateNewStartingPos();
    }

    @Override
    public void autoNavigateToLocation() {

    }

    @Override
    public void adjustTickSpeed() {

    }

}
