package model.bots;

import model.maps.MapInterface;
import model.runs.DFSRun;
import model.runs.DijkstraRun;

public class DijkstraBot extends AbstractBot {

    public DijkstraBot(MapInterface m) {
        super(m);
    }

    @Override
    public void generateRun() {
        DijkstraRun r = new DijkstraRun(m, this);
        r.clearBlockCache();
        r.generateRun();
        this.visitedOrder.add(r.getRun());
    }
}
