package controller;

import model.BasicPathFindingModel;
import view.BasicMapView;

/**
 * Class for controlling the map-creation.
 */
public class BasicPathFindingModelController implements ControllerInterface {

    BasicPathFindingModel m;
    BasicMapView v;


    public BasicPathFindingModelController(BasicPathFindingModel m) {
        this.m = m;
        this.v = new BasicMapView(m, 10);
    }

    @Override
    public void go() throws InterruptedException {
        v.animate();

    }

}
