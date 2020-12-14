package controller;

import model.BasicPathFindingModel;
import view.BasicMapView;


//specific controller for specific model

public class BasicPathFindingModelController implements ControllerInterface {

    BasicPathFindingModel m;
    BasicMapView v;



    public BasicPathFindingModelController(BasicPathFindingModel m) { /////////////////NOT HOW YOU SHOULD HANDLE THIS.........
        this.m = m;
        this.v = new BasicMapView(m, 10);
    }

    @Override
    public void go() throws InterruptedException {
        v.animate();

    }

}
