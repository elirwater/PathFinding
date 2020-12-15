package model.runs;

import model.Pair;
import model.blocks.BasicBlocks;
import model.bots.BotInterface;
import model.maps.MapInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class BFSRun implements RunInterface {

    private final ArrayList<Pair> run;
    private final MapInterface m;
    private final BotInterface b;
    private BasicBlocks startBlock;


    public BFSRun(MapInterface m, BotInterface b) {
        this.run = new ArrayList<>();
        this.m = m;
        this.b = b;
        this.startBlock = this.m.getBlock(b.getCurrentRow(), b.getCurrentColumn());
    }


    @Override
    public void generateRun() {

        this.startBlock.setVisited();
        Queue<BasicBlocks> queue = new LinkedList<>();
        queue.add(startBlock);



    }

    @Override
    public ArrayList<Pair> getRun() {
        return this.run;
    }
}
