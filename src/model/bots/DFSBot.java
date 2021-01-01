package model.bots;

import model.maps.MapInterface;
import model.runs.AbstractRuns;
import model.runs.DFSRun;

public class DFSBot extends AbstractBot {

    public DFSBot(MapInterface m) {
        super(m);
    }

    @Override
    public void generateRun() {
        DFSRun r = new DFSRun(m, this);
        r.clearBlockCache();
        r.generateRun();
        this.visitedOrder.add(r.getRun());
    }

}
