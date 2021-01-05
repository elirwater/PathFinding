package model.bots;

import model.Pair;
import model.blocks.BasicBlocks;
import model.blocks.BasicBlocks.BlockState;
import model.maps.MapInterface;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Random;


/**
 * Abstract class for abstracting the repeated functionality shared between bot classes.
 */
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
     * Checks to see if the requested basic block (through its array list pos) is not blocked,
     * AND is within the map-grid.
     * @param x Outer array list pos to be selected from
     * @param y Inner array list pos to be selected from
     * @return true if not blocked AND within map-grid
     * @throws IllegalArgumentException if the requested block doesn't exist within the grid
     */
    private boolean checkBounds(int x, int y) throws IllegalArgumentException {
        try {
            return !m.getBlock(x, y).getBlocked() && x < m.getGridSize().getY() && y < m.getGridSize().getX();
        } catch (Exception e) {
            throw new IllegalArgumentException("Must select a block that is within the map-grid");
        }
    }


    /**
     * Generates a random pair that when translated to a basic-block in the map grid is unblocked and within the grid.
     * @return valid pair
     */
    private Pair generateRandomPair() {

        Random r = new Random();
        int rowBound = m.getMapGrid().size();
        int colBound = m.getMapGrid().get(0).size();

        int tempR = r.nextInt(rowBound);
        int tempC = r.nextInt(colBound);

        while (!checkBounds(tempR, tempC)) {
            tempR = r.nextInt(rowBound);
            tempC = r.nextInt(colBound);
        }

        return new Pair(tempR, tempC);
    }


    /**
     * Generates a goal for a path-finding bot.
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

        if (checkBounds(blockRow, blockColumn)) {
            for (BasicBlocks b: this.visited) {
                if (!b.isGoal()) {
                    b.setState(BlockState.path, true);
                }
            }

            this.rowBlock = blockRow;
            this.columnBlock = blockColumn;
            this.m.getBlock(blockRow, blockColumn).setState(BlockState.bot, true);
            this.visited.add(this.m.getBlock(blockRow, blockColumn));
        }
    }

    @Override
    public Pair getRunCurrentBotPos(int run, int tick) {
        try {
            return this.visitedOrder.get(run).get(tick);
        } catch (Exception e) {
            throw new IllegalArgumentException("Provided run or tick is not within bounds");
        }
    }

    @Override
    public int getFinalTick(int run) {
        return this.visitedOrder.get(run).size();
    }

    @Override
    public boolean goalReached() {
        try {
            return this.goal.getX() == this.rowBlock && this.goal.getY() == this.columnBlock;
        } catch (Exception e) {
            throw new IllegalStateException("Goal has not been instantiated");
        }
    }


    @Override
    public int getCurrentRow() {
        try {
            return this.rowBlock;
        } catch (Exception e) {
            throw new IllegalStateException("Something failed when retrieving row position");
        }
    }

    @Override
    public int getCurrentColumn() {
        try {
            return this.columnBlock;
        } catch (Exception e) {
            throw new IllegalStateException("Something failed when retrieving column position");
        }
    }

    @Override
    public Pair getStartingCords() {
        try {
            return this.startingCords;
        } catch (Exception e) {
            throw new IllegalStateException("Something failed when retrieving starting cords");
        }
    }

    @Override
    public void generateRun() {
    }

    @Override
    public Pair getGoal() {
        try {
            return goal;
        } catch (Exception e) {
            throw new IllegalStateException("Something failed when retrieving goal");
        }
    }

    @Override
    public void setGoal(int x, int y) {
        if (checkBounds(x, y)) {
            this.m.getBlock(x, y).setState(BlockState.goal, true);
            this.m.getBlock(this.goal.getX(), this.goal.getY()).setState(BlockState.goal, false);
            this.goal = new Pair(x, y);
        }
    }
}
