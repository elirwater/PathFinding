package model.blocks;

public class BasicBlocks {

    private boolean blocked;
    private boolean goal = false;
    private boolean bot = false;
    private boolean path = false;
    private boolean visited = false;
    private BasicBlocks parent;
    private int rowPos;
    private int columnPos;
    private int distanceFromGoal = (int) Double.POSITIVE_INFINITY;

    public BasicBlocks(boolean blocked, int rowPos, int columnPos) {

        this.blocked = blocked;
        this.rowPos = rowPos;
        this.columnPos = columnPos;
    }

    public int getDistanceFromGoal() {
        return distanceFromGoal;
    }

    public void setDistanceFromGoal(int distanceFromGoal) {
        this.distanceFromGoal = distanceFromGoal;
    }

    public int getColumnPos() {
        return columnPos;
    }

    public int getRowPos() {
        return rowPos;
    }

    public void setParent(BasicBlocks parent) {
        this.parent = parent;
    }

    public BasicBlocks getParent() {
        return parent;
    }


    public boolean getBlocked() {
        return this.blocked;
    }



    public void unblock() {
        this.blocked = false;
    }

    public boolean isGoal() {
        return this.goal;
    }

    public boolean isBot() {
        return this.bot;
    }



    public void setVisited() {
        this.visited = true;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public void setGoal() {
        if (this.goal) {
            this.goal = false;
        } else {
            this.goal = true;
            this.path = false;
            this.bot = false;
        }
    }

    public void setBot() {
        this.bot = true;
        this.path = false;
        this.goal = false;

    }

    public void setPath() {
        this.path = true;
        this.goal = false;
        this.bot = false;
    }

    public boolean isPath() {
        return this.path;
    }
}
