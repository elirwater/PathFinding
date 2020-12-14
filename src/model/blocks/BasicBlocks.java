package model.blocks;

import java.util.ArrayList;

public class BasicBlocks {

    private final int height, width;
    private final int x, y;
    private boolean blocked;
    private boolean goal = false;
    private boolean bot = false;
    private boolean path = false;
    private boolean visited = false;
    private BasicBlocks parent;
    private int rowPos;
    private int columnPos;

    public BasicBlocks(int height, int width, int x, int y, boolean blocked, int rowPos, int columnPos) {

        if (height <= 0 || width <= 0) {
            throw new IllegalArgumentException("Dimensions must be greater than 0");
        }

        this.height = height;
        this.blocked = blocked;
        this.rowPos = rowPos;
        this.columnPos = columnPos;
        this.width = width;
        this.x = x;
        this.y = y;
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

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }

    public boolean getBlocked() {
        return this.blocked;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
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

    //enforcing that it should only have 1 of 3 states


    public void setVisited() {
        this.visited = true;
    }

    public boolean getVisited() {
        return this.visited;
    }

    public void setGoal() {
        this.goal = true;
        this.path = false;
        this.bot = false;
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
