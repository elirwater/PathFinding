package model.runs;

import model.Pair;
import model.blocks.BasicBlocks;
import model.bots.BotInterface;
import model.maps.MapInterface;

import java.util.Comparator;
import java.util.PriorityQueue;

/**
 * Class for creating an instance of a dijkstra run.
 */
public class DijkstraRun extends AbstractRuns {


    public DijkstraRun(MapInterface m, BotInterface b) {
        super(m, b);
    }

    @Override
    public void generateRun() {

        PriorityQueue<BasicBlocks> prioQ = new PriorityQueue<>(new BasicBlockComparator());

        this.startBlock.setDistanceFromGoal(0);
        prioQ.add(this.startBlock);


        while(!prioQ.isEmpty()) {
            BasicBlocks curNode = prioQ.poll();
            curNode.setVisited(true);


            for (BasicBlocks b : this.m.getUnvisitedNeighbors(curNode.getRowPos(), curNode.getColumnPos())) {

                int minD = Math.min(b.getDistanceFromGoal(), curNode.getDistanceFromGoal() + 1);

                if (minD != b.getDistanceFromGoal()) {
                    b.setDistanceFromGoal(minD);
                    b.setParent(curNode);

                    if (prioQ.contains(b)) {
                        b.setDistanceFromGoal(minD);
                    }
                }

                if (!prioQ.contains(b)) {
                    prioQ.add(b);
                }
            }

        }

    }


    /**
     * Class comparator for ranking the priority of Basic Blocks by their block distance from the goal.
     */
    static class BasicBlockComparator implements Comparator<BasicBlocks> {

        //want to return 1 if closer to goal....
        @Override
        public int compare(BasicBlocks b1, BasicBlocks b2) {
            if (b1.getDistanceFromGoal() < b2.getDistanceFromGoal()) {
                return 1;
            }
            if (b1.getDistanceFromGoal() == b2.getDistanceFromGoal()) {
                return 0;
            }
            else  {
                return -1;
            }
        }
    }

}
