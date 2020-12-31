package model.bots;

import model.Pair;
import model.blocks.BasicBlocks;
import model.blocks.BasicBlocks.BlockState;
import model.maps.MapInterface;
import model.runs.DFSRun;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;

public abstract class AbstractBot implements BotInterface {

    private int rowBlock;
    private int columnBlock;
    private Pair startingCords;
    protected final MapInterface m;
    private Pair goal;
    private final HashSet<BasicBlocks> visited;

    protected ArrayList<ArrayList<Pair>> visitedOrder;




    public AbstractBot(MapInterface m) {

        this.m = m;
        this.visitedOrder = new ArrayList<>();
        this.visited = new HashSet<>();


        this.generateRandomValidStartingPoint();
        this.generateGoal();
        this.rowBlock = startingCords.getX();
        this.columnBlock = startingCords.getY();

        this.m.getBlock(rowBlock, columnBlock).setState(BlockState.bot, true);

    }


    /**
     * Generates a random pair of integers.
     * @return pair of integers.
     */
    private Pair generateRandomPair() {

        Random r = new Random();
        int rowBound = m.getMapGrid().size();
        int colBound = m.getMapGrid().get(0).size();

        int tempR = r.nextInt(rowBound);
        int tempC = r.nextInt(colBound);

        while (this.m.getBlock(tempR, tempC).getBlocked()) {
            tempR = r.nextInt(rowBound);
            tempC = r.nextInt(colBound);
        }

        return new Pair(tempR, tempC);
    }

    /**
     * Generates a random goal block for this bot.
     */
    public void generateGoal() {
        this.goal = generateRandomPair();
        int x = this.goal.getX();
        int y = this.goal.getY();
        this.m.getBlock(x, y).setState(BlockState.goal, true);
    }


    @Override
    public void generateRandomValidStartingPoint() {
        this.startingCords = generateRandomPair();
        int x = this.startingCords.getX();
        int y = this.startingCords.getY();
        this.m.getBlock(x, y).setState(BlockState.bot, true);
        this.visited.add(this.m.getBlock(x, y));
    }



    @Override
    public void move(int blockRow, int blockColumn) {

        //Adds the blocks to the visited hashset
        for (BasicBlocks b: this.visited) {
            if (!b.isGoal()) {
                b.setState(BlockState.path, true);
            }
        }

        //removing the last block as the bot
        int x = this.rowBlock;
        int y = this.columnBlock;
        //checking to see if the block that it is attempting to move towards is blocked
        try {
            //Just roots the user in place if they try to move somewhere they shouldn't
            if (!m.getBlock(blockRow, blockColumn).getBlocked()) {
                this.rowBlock = blockRow;
                this.columnBlock = blockColumn;
                this.m.getBlock(blockRow, blockColumn).setState(BlockState.bot, true);
                this.visited.add(this.m.getBlock(blockRow, blockColumn));
            } else {
                this.m.getBlock(x, y).setState(BlockState.bot, true);
            }
            //Looking outside of the map
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Cannot move there");
        }
    }

    @Override
    public Pair getRunCurrentBotPos(int run, int tick) {
        return this.visitedOrder.get(run).get(tick);
    }

    @Override
    public int getFinalTick(int run) {
        return this.visitedOrder.get(run).size();
    }

    @Override
    public boolean goalReached() {
        return this.goal.getX() == this.rowBlock && this.goal.getY() == this.columnBlock;
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
    public Pair getStartingCords() {
        return this.startingCords;
    }

    @Override
    public void generateRun() {
    }

    public Pair getGoal() {
        return goal;
    }


    @Override
    public void setGoal(int x, int y) {
        this.m.getBlock(x, y).setState(BlockState.goal, true);
        this.m.getBlock(this.goal.getX(), this.goal.getY()).setState(BlockState.goal, false);
        this.goal = new Pair(x, y);
    }
}
