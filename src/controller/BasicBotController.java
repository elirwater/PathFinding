package controller;

import model.BasicPathFindingModel;
import view.BasicBotView;
import view.BasicMapView;


//specific controller for specific model

public class BasicBotController implements ControllerInterface {

    BasicPathFindingModel m;
    BasicBotView v;



    public BasicBotController(BasicPathFindingModel m) { /////////////////NOT HOW YOU SHOULD HANDLE THIS.........
        this.m = m;
        this.v = new BasicBotView(m, 120);
    }

    @Override
    public void go() throws InterruptedException {
        v.animate();
    }

}
