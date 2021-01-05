package model.runs;

import model.Pair;
import model.blocks.BasicBlocks;
import model.bots.BotInterface;
import model.maps.MapInterface;

import java.util.ArrayList;

/**
 * Abstract class for abstracting repeated functionality of various runs.
 */
public abstract class AbstractRuns implements RunInterface {

    protected final ArrayList<Pair> run;
    protected final MapInterface m;
    protected final BotInterface b;
    protected BasicBlocks startBlock;


    AbstractRuns(MapInterface m, BotInterface b) {
        this.run = new ArrayList<>();
        this.m = m;
        this.b = b;
        this.startBlock = this.m.getBlock(b.getCurrentRow(), b.getCurrentColumn());
    }

    @Override
    public void generateRun() {

    }

    @Override
    public void generatePathFromGoal() {
        Pair goalCords = this.b.getGoal();
        BasicBlocks b = this.m.getBlock(goalCords.getX(), goalCords.getY());

        ArrayList<BasicBlocks> fromGoal = new ArrayList<>();

        fromGoal.add(this.startBlock);
        while (this.startBlock.getColumnPos() != b.getColumnPos() || this.startBlock.getRowPos() != b.getRowPos()) {
            fromGoal.add(b);
            b = b.getParent();
        }

        for (int x = fromGoal.size() - 1; x > 0; x--) {
            this.run.add(new Pair(fromGoal.get(x).getRowPos(), (fromGoal.get(x).getColumnPos())));
        }
    }


    @Override
    public void clearBlockCache() {
        for (ArrayList<BasicBlocks> br: this.m.getMapGrid()) {
            for (BasicBlocks b: br) {
                b.clearCache();
            }
        }
    }

    @Override
    public ArrayList<Pair> getRun() {
        this.generatePathFromGoal();
        return this.run;
    }
}
