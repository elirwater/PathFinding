package model.maps;

import model.Pair;
import model.blocks.BasicBlocks;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Concrete class implementation for a depth first search map.
 */
public class DepthFirstSearchMap implements MapInterface {

    private final Pair gridSize;
    private final ArrayList<ArrayList<BasicBlocks>> mapGrid;
    private final ArrayList<BasicBlocks> visitedOrder;


    /**
     * Constructor for instantiating a depth first search map for a path-finding bot
     *
     * @throws  IllegalArgumentException if the grid-size is likely to cause a stack over-flow exception
     */
    public DepthFirstSearchMap(Pair gridSize) {
        if (gridSize.getX() > 300 || gridSize.getY() > 300) {
            throw new IllegalArgumentException("Selected grid size will likely cause a stack-overflow exception");
        }

        this.gridSize = gridSize;
        this.mapGrid = new ArrayList<>();
        this.visitedOrder = new ArrayList<>();
        this.generateMapGrid();
        this.generateMaze();
    }

    @Override
    public void generateMapGrid() {
        int rowCount = this.gridSize.getY();
        int columnCount = this.gridSize.getX();

        for (int z = 0; z < rowCount; z++) {
            ArrayList<BasicBlocks> rowList = new ArrayList<>();

            for (int t = 0; t < columnCount; t++) {

                BasicBlocks b = new BasicBlocks(z, t);
                rowList.add(b);
            }
            this.mapGrid.add(rowList);
        }
    }


    @Override
    public void generateMaze() {
        Random rand = new Random();

        //grabbing a random row cell value
        int r = rand.nextInt(this.gridSize.getY());
        //Making sure random number is odd
        while (r % 2 == 0) {
            r = rand.nextInt(this.gridSize.getY());
        }

        //grabbing a random column value
        int c = rand.nextInt( this.gridSize.getX());
        //Making sure random number is odd
        while (c % 2 == 0) {
            c = rand.nextInt( this.gridSize.getX());
        }

        this.mapGrid.get(r).get(c).unblock();

        this.mazeRecursion(r, c);

    }




    /**
     * Controls the depth first search recursion for generating the maze
     *
     * @param r index value of the selected block in the grid
     * @param c index value of the selected block in the grid
     */
    private void mazeRecursion(int r, int c) {

        //generate 4 random directions
        ArrayList<Integer> fourRandDirections = generateFourRandomDirections();

        //Looping through each direction
        for (int i = 0; i < fourRandDirections.size(); i++) {

            switch (fourRandDirections.get(i)) {
                //Direction is north
                case 1:
                    //Determining if two cells north is out of the bounds of the maze
                    if (r - 2 <= 0)
                        continue;
                    if (this.mapGrid.get(r - 2).get(c).getBlocked()) {
                        this.visitedOrder.add(this.mapGrid.get(r - 2).get(c));
                        this.visitedOrder.add(this.mapGrid.get(r - 1).get(c));
                        this.mapGrid.get(r - 2).get(c).unblock();
                        this.mapGrid.get(r - 1).get(c).unblock();
                        mazeRecursion(r - 2, c);
                    }
                    break;
                case 2:
                    if (c + 2 >=  this.gridSize.getX() - 1)
                        continue;
                    if (this.mapGrid.get(r).get(c + 2).getBlocked()) {
                        this.visitedOrder.add(this.mapGrid.get(r).get(c + 2));
                        this.visitedOrder.add(this.mapGrid.get(r).get(c + 1));
                        this.mapGrid.get(r).get(c + 2).unblock();
                        this.mapGrid.get(r).get(c + 1).unblock();
                        mazeRecursion(r, c + 2);
                    }
                    break;

                case 3:
                    if (r + 2 >=  this.gridSize.getY() - 1)
                        continue;
                    if (this.mapGrid.get(r + 2).get(c).getBlocked()) {
                        this.visitedOrder.add(this.mapGrid.get(r + 2).get(c));
                        this.visitedOrder.add(this.mapGrid.get(r + 1).get(c));
                        this.mapGrid.get(r + 2).get(c).unblock();
                        this.mapGrid.get(r + 1).get(c).unblock();
                        mazeRecursion(r + 2, c);

                    }
                    break;

                case 4:
                    if (c - 2 <= 0)
                        continue;
                    if (this.mapGrid.get(r).get(c - 2).getBlocked()) {
                        this.visitedOrder.add(this.mapGrid.get(r).get(c - 2));
                        this.visitedOrder.add(this.mapGrid.get(r).get(c - 1));
                        this.mapGrid.get(r).get(c - 2).unblock();
                        this.mapGrid.get(r).get(c - 1).unblock();
                        mazeRecursion(r, c - 2);
                    }
                    break;
            }
        }
    }

    /**
     * Randomly picks a direction for the search to take.
     *
     * @return 4 random directions as integers
     */
    private ArrayList<Integer> generateFourRandomDirections() {
        ArrayList<Integer> randoms = new ArrayList<>();
        for (int i = 0; i < 4; i++)
            randoms.add(i + 1);
        Collections.shuffle(randoms);
        return randoms;
    }

    @Override
    public BasicBlocks getBlock(int x, int y) throws IllegalArgumentException {
        try {
            return this.mapGrid.get(x).get(y);
        } catch (Exception e) {
            throw new IllegalArgumentException("The specified basic block doesn't exist in this map-grid");
        }
    }

    @Override
    public ArrayList<ArrayList<BasicBlocks>> getMapGrid() {
        return this.mapGrid;
    }

    @Override
    public ArrayList<BasicBlocks> getVisitedOrder() {
        return visitedOrder;
    }


    @Override
    public ArrayList<BasicBlocks> getUnvisitedNeighbors(int x, int y) {   //terrible way to implement this

        ArrayList<BasicBlocks> out = new ArrayList<>();


        if (!this.mapGrid.get(x + 1).get(y).getVisited() && !this.mapGrid.get(x + 1).get(y).getBlocked()) {
            out.add(this.mapGrid.get(x + 1).get(y));
        }
        if (!this.mapGrid.get(x - 1).get(y).getVisited() && !this.mapGrid.get(x - 1).get(y).getBlocked()) {
            out.add(this.mapGrid.get(x - 1).get(y));
        }
        if (!this.mapGrid.get(x).get(y + 1).getVisited() && !this.mapGrid.get(x).get(y + 1).getBlocked()) {
            out.add(this.mapGrid.get(x).get(y + 1));
        }
        if (!this.mapGrid.get(x).get(y - 1).getVisited() && !this.mapGrid.get(x).get(y - 1).getBlocked()) {
            out.add(this.mapGrid.get(x).get(y - 1));
        }

        return out;
    }

    @Override
    public Pair getGridSize() {
        return this.gridSize;
    }
}
