package model.bots;

import model.blocks.BasicBlocks;
import model.maps.MapInterface;
import model.runs.BasicRun;
import model.runs.RunInterface;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public class BasicBot implements BotInterface<BasicRun> {

    private int rowBlock;
    private int columnBlock;
    private ArrayList<Integer> startingCords;
    private final MapInterface m;
    private ArrayList<Integer> goal;
    private final HashSet<BasicBlocks> visited;

    private ArrayList<ArrayList<Integer>> visitedOrder;

    private final ArrayList<BasicRun> runs;



    public BasicBot(MapInterface m) {

        this.m = m;
        this.startingCords = new ArrayList<>();
        this.visitedOrder = new ArrayList<>();
        this.visited = new HashSet<>();
        this.runs = new ArrayList<>();

        this.generateRandomValidStartingPoint();
        this.generateGoal();
        this.rowBlock = startingCords.get(0);
        this.columnBlock = startingCords.get(1);
        this.m.getMapGrid().get(rowBlock).get(columnBlock).setBot();

    }


    /**
     * Generates a random pair of integers.
     * @return pair of integers.
     */
    private ArrayList<Integer> generateRandomPair() {
        ArrayList<Integer> out = new ArrayList<>();


        Random r = new Random();
        int rowBound = m.getMapGrid().size();
        int colBound = m.getMapGrid().get(0).size();

        int tempR = r.nextInt(rowBound);
        int tempC = r.nextInt(colBound);

        while (m.getMapGrid().get(tempR).get(tempC).getBlocked()) {
            tempR = r.nextInt(rowBound);
            tempC = r.nextInt(colBound);
        }
        out.add(tempR);
        out.add(tempC);

        return out;
    }

    /**
     * Generates a random goal block for this bot.
     */
    public void generateGoal() {
        this.goal = generateRandomPair();
        int x = this.goal.get(0);
        int y = this.goal.get(1);
        this.m.getMapGrid().get(x).get(y).setGoal();
    }


    @Override
    public void generateRandomValidStartingPoint() {

        this.startingCords = generateRandomPair();
        int x = this.startingCords.get(0);
        int y = this.startingCords.get(1);

        this.m.getMapGrid().get(x).get(y).setBot();
        this.visited.add(this.m.getMapGrid().get(x).get(y));
    }



    @Override
    public void move(int blockRow, int blockColumn) {

        //Adds the blocks to the visited hashset
        for (BasicBlocks b: this.visited) {
            b.setPath();
        }

        //removing the last block as the bot
        int x = this.rowBlock;
        int y = this.columnBlock;
        //checking to see if the block that it is attempting to move towards is blocked
        try {
            //Just roots the user in place if they try to move somewhere they shouldn't
            if (!m.getMapGrid().get(blockRow).get(blockColumn).getBlocked()) {
                this.rowBlock = blockRow;
                this.columnBlock = blockColumn;
                this.m.getMapGrid().get(blockRow).get(blockColumn).setBot();
                this.visited.add(this.m.getMapGrid().get(blockRow).get(blockColumn));
            } else {
                this.m.getMapGrid().get(x).get(y).setBot();
            }
            //Looking outside of the map
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Cannot move there");
        }
    }

    @Override
    public ArrayList<Integer> getRunVisit(int tick) {
        return this.visitedOrder.get(tick);
    }

    @Override
    public int getFinalTick() {
        return this.visitedOrder.size() - 1;
    }

    @Override
    public boolean goalReached() {
        return this.goal.get(0) == this.rowBlock && this.goal.get(1) == this.columnBlock;
    }


    @Override
    public int getCurrentRow() {
        return this.rowBlock;
    }

    @Override
    public int getCurrentColumn() {
        return this.columnBlock;
    }

    @Override
    public ArrayList<Integer> getStartingCords() {
        return this.startingCords;
    }

    @Override
    public void generateRun() { /////////////////NOT FINAL !!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
        BasicRun r = new BasicRun(2, this.m, this);
        r.generateRun();
        this.runs.add(r);

        this.visitedOrder = r.getRun();
    }

    @Override
    public ArrayList<BasicRun> getRuns() {
        return this.runs;
    }

    @Override
    public BasicRun getBestRun() {
        return null;
    }


    public ArrayList<Integer> getGoal() {
        return goal;
    }
}
