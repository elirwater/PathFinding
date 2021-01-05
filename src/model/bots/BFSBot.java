package model.bots;

import model.maps.MapInterface;
import model.runs.BFSRun;

/**
 * Class for instantiating a breadth-first search bot.
 */
public class BFSBot extends AbstractBot {

    public BFSBot(MapInterface m) {
        super(m);
    }


    @Override
    public void generateRun() {
        BFSRun r = new BFSRun(m, this);
        r.clearBlockCache();
        r.generateRun();
        this.visitedOrder.add(r.getRun());
    }
}
