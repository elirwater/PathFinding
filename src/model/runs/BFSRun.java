package model.runs;

import model.blocks.BasicBlocks;
import model.bots.BotInterface;
import model.maps.MapInterface;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Class for creating an instance of a breadth-first search run.
 */
public class BFSRun extends AbstractRuns {



    public BFSRun(MapInterface m, BotInterface b) {
        super(m, b);
    }


    @Override
    public void generateRun() {

        this.startBlock.setVisited(true);
        Queue<BasicBlocks> queue = new LinkedList<>();
        queue.add(startBlock);

        while (!queue.isEmpty()) {

            BasicBlocks currNode = queue.poll();

            if (currNode.isGoal()) {
                break;
            }

            int x = currNode.getRowPos();
            int y = currNode.getColumnPos();

            ArrayList<BasicBlocks> neighbors = this.m.getUnvisitedNeighbors(x, y);

            for (int i = 0; i < neighbors.size(); i++) {
                neighbors.get(i).setVisited(true);
                neighbors.get(i).setParent(currNode);
                queue.offer(neighbors.get(i));
            }
        }

    }

}
