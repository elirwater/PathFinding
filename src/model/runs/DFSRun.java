package model.runs;

import model.Pair;
import model.blocks.BasicBlocks;
import model.bots.BotInterface;
import model.maps.MapInterface;

import java.util.ArrayList;
import java.util.Stack;

public class DFSRun implements RunInterface {

    private final ArrayList<Pair> run;
    private final MapInterface m;
    private final BotInterface b;
    private BasicBlocks startBlock;


    public DFSRun(MapInterface m, BotInterface b) {
        this.run = new ArrayList<>();
        this.m = m;
        this.b = b;
        this.startBlock = this.m.getBlock(b.getCurrentRow(), b.getCurrentColumn());
    }



    @Override
    public void generateRun() {

        Stack<BasicBlocks> stack = new Stack<>();
        this.startBlock.setVisited();
        stack.push(this.startBlock);

        while (!stack.empty()) {

            BasicBlocks curNode = stack.pop();

            if (curNode.isGoal()) {
                break;
            }

            int x = curNode.getRowPos();
            int y = curNode.getColumnPos();

            ArrayList<BasicBlocks> neighbors = this.m.getUnvisitedNeighbors(x, y);

            for (int i = 0; i < neighbors.size(); i ++) {
                neighbors.get(i).setVisited();
                neighbors.get(i).setParent(curNode);
                stack.push(neighbors.get(i));

            }
        }

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
    public ArrayList<Pair> getRun() {
        return this.run;
    }
}
