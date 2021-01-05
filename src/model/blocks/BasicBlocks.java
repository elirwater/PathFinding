package model.blocks;

/**
 * Class for representing a basic block, which holds all of the relevant map grid info.
 */
public class BasicBlocks {

    private boolean blocked = true;
    private boolean goal = false;
    private boolean bot = false;
    private boolean path = false;
    private boolean visited = false;
    private BasicBlocks parent;
    private int rowPos;
    private int columnPos;
    private int distanceFromGoal = (int) Double.POSITIVE_INFINITY;

    public BasicBlocks(int rowPos, int columnPos) {
        this.rowPos = rowPos;
        this.columnPos = columnPos;
    }

    /**
     * Clears all of the data from this block that could affect a pathfinding bot
     * when navigating to a given goal.
     */
    public void clearCache() {
        this.distanceFromGoal = (int) Double.POSITIVE_INFINITY;
        this.visited = false;
        this.parent = null;
    }


    /**
     * Enum for describing a set of states for a basic block
     */
    public enum BlockState {
        goal,
        bot,
        path,
    }


    /**
     * Sets the state of this basic block.
     * @param state which state is to be set (path, bot, or goal)
     * @param val the value of that state (true or false)
     */
    public void setState(BlockState state, boolean val) {

        switch (state) {
            case goal:
                if (val) {
                    this.goal = true;
                    this.bot = false;
                    this.path = false;
                } else {
                    this.goal = false;
                }
                break;
            case bot:
                if (val) {
                    this.bot = true;
                    this.goal = false;
                    this.path = false;
                } else {
                    this.bot = false;
                }
                break;
            case path:
                if (val) {
                    this.path = true;
                    this.bot = false;
                    this.goal = false;
                } else {
                    this.path = false;
                }
                break;
            default:
                throw new IllegalArgumentException("Incorrect BlockState Given");
        }
    }


    // Block State Setters
    public void setDistanceFromGoal(int distanceFromGoal) {
        this.distanceFromGoal = distanceFromGoal;
    }
    public void setVisited(boolean state) { this.visited = state; }
    public void setParent(BasicBlocks parent) {
        this.parent = parent;
    }
    public void unblock() {
        this.blocked = false;
    }


    //Block State Getters
    public boolean isPath() {
        return this.path;
    }
    public boolean isGoal() {
        return this.goal;
    }
    public boolean isBot() {
        return this.bot;
    }
    public int getColumnPos() {
        return columnPos;
    }
    public int getRowPos() {
        return rowPos;
    }
    public BasicBlocks getParent() {
        return parent;
    }
    public boolean getBlocked() {
        return this.blocked;
    }
    public boolean getVisited() {
        return this.visited;
    }
    public int getDistanceFromGoal() {
        return distanceFromGoal;
    }



}
