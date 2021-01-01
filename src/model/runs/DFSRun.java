package model.runs;

import model.Pair;
import model.blocks.BasicBlocks;
import model.bots.BotInterface;
import model.maps.MapInterface;

import java.util.ArrayList;
import java.util.Stack;

public class DFSRun extends AbstractRuns {



    public DFSRun(MapInterface m, BotInterface b) {
        super(m, b);
    }



    @Override
    public void generateRun() {

        Stack<BasicBlocks> stack = new Stack<>();
        this.startBlock.setVisited(true);
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
                neighbors.get(i).setVisited(true);
                neighbors.get(i).setParent(curNode);
                stack.push(neighbors.get(i));

            }
        }
    }
}
