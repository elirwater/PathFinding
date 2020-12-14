package model.runs;

import model.blocks.BasicBlocks;
import model.bots.BotInterface;
import model.maps.MapInterface;

import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;

public class BasicRun implements RunInterface {

    private final ArrayList<ArrayList<Integer>> run;
    private int currRow;
    private int currColumn;
    private final MapInterface m;
    private final int blocksAhead;
    private final BotInterface b;

    private final Stack<BasicBlocks> stack;




    private int currentDirection;


    public BasicRun(int blocksAhead, MapInterface m, BotInterface b) {
        this.stack = new Stack<>();
        this.run = new ArrayList<>();
        this.currRow = b.getCurrentRow();
        this.currColumn = b.getCurrentColumn();
        this.m = m;
        this.blocksAhead =  blocksAhead;
        this.b = b;
    }


    //should have a caveat on not going back the same direction it came

    @Override
    public void generateRun() {   ///OBVIOUSLY NOT THE WAY U ARE GONNA DO THIS!


        this.m.getMapGrid().get(this.currRow).get(this.currColumn).setVisited();

        this.stack.push( this.m.getMapGrid().get(this.currRow).get(this.currColumn));

        while (!this.stack.empty()) {

            BasicBlocks curNode = this.stack.pop();

            if (curNode.isGoal()) {
                break;
            }

            int x = curNode.getRowPos();
            int y = curNode.getColumnPos();

            ArrayList<BasicBlocks> neighbors = this.m.getUnvisitedNeighbors(x, y);


            for (int i = 0; i < neighbors.size(); i ++) {
                neighbors.get(i).setVisited();
                neighbors.get(i).setParent(curNode);
                this.stack.push(neighbors.get(i));

            }

        }

        //ANother terrible way to do this

        ArrayList<Integer> goalCords = this.b.getGoal();
        BasicBlocks b = this.m.getMapGrid().get(goalCords.get(0)).get(goalCords.get(1));

        ArrayList<BasicBlocks> fromGoal = new ArrayList<>();

        BasicBlocks startingBlock = this.m.getMapGrid().get(this.currRow).get(this.currColumn);
        //SHould add an equality method to Blocks,....lot to change!

        fromGoal.add(startingBlock);
        while (startingBlock.getColumnPos() != b.getColumnPos() || startingBlock.getRowPos() != b.getRowPos()) {
            fromGoal.add(b);
            b = b.getParent();

        }

        ///ALSO VERY IMPORTANT: map should know block positions, blocks shouldn't know their position....
        //AND map should know the neighbors, NOT THE BLOCKS!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        //re-design using gitKraken



        for (int x = fromGoal.size() - 1; x > 0; x--) {
            ArrayList<Integer> test = new ArrayList<>();
            test.add(fromGoal.get(x).getRowPos());
            test.add(fromGoal.get(x).getColumnPos());
            this.run.add(test);
        }




    }



    private void generateRandDirection() {

        int d = 10;
        Random r = new Random();
        boolean checkPrevious = true;

        while (checkPrevious == true) {
            d = r.nextInt(4);
            if (d != this.currentDirection) {
                checkPrevious = false;
            }
        }

        this.currentDirection = d;

    }


    //Returns true if target block is blocked
    private boolean checkBlocked(int x, int y) {
        return m.getMapGrid().get(x).get(y).getBlocked();
    }


    private void goForward() {

        switch (this.currentDirection) {

            //North
            case 0:
                if (!this.checkBlocked(this.currRow - 1, this.currColumn)) {
                    this.currRow --;
                } else {
                    this.generateRandDirection();
                }
                break;
            //South
            case 1:
                if (!this.checkBlocked(this.currRow + 1, this.currColumn)) {
                    this.currRow ++;
                } else {
                    this.generateRandDirection();
                }

                break;
            //West
            case 2:
                if (!this.checkBlocked(this.currRow, this.currColumn - 1)) {
                    this.currColumn --;
                } else {
                    this.generateRandDirection();
                }
                break;
            //East
            case 3:
                if (!this.checkBlocked(this.currRow, this.currColumn + 1)) {
                    this.currColumn ++;
                } else {
                    this.generateRandDirection();
                }
                break;
        }
    }






    @Override
    public ArrayList<ArrayList<Integer>> getRun() {
        return this.run;
    }
}
