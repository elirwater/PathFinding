package model.bots;

import model.maps.MapInterface;
import model.runs.DFSRun;

/**
 * Class for instantiating a depth-first search bot.
 */
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
