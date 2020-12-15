package model.bots;

import model.maps.MapInterface;
import model.runs.BFSRun;
import model.runs.DFSRun;

public class BFSBot extends AbstractBot {

    public BFSBot(MapInterface m) {
        super(m);
    }


    @Override
    public void generateRun() {
        BFSRun r = new BFSRun(m, this);
        r.generateRun();
        this.visitedOrder = r.getRun();
    }
}
