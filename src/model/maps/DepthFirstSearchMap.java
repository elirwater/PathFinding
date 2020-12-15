package model.maps;

import model.blocks.BasicBlocks;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

/**
 * Concrete class implementation for a depth first search map.
 */
public class DepthFirstSearchMap implements MapInterface {

    private final int blockSize;
    private final int numBlocks;
    private final ArrayList<ArrayList<BasicBlocks>> mapGrid;
    private final ArrayList<BasicBlocks> visitedOrder;


    /**
     * Constructor for instantiating a depth first search map for a path-finding bot
     *
     * @param blockSize height/width of the square block
     * @param numBlocks number of blocks in a grid, must be a perfect square
     * @throws  IllegalArgumentException if the grid isn't an odd perfect square, or incorrect block instantiation
     */
    public DepthFirstSearchMap(int blockSize, int numBlocks) {

        if (numBlocks % Math.sqrt(numBlocks) != 0 || (Math.sqrt(numBlocks) % 2) == 0) {
            throw new IllegalArgumentException("Grid Size must be a perfect square, with squares being odd");
        }

        this.blockSize = blockSize;
        this.numBlocks = numBlocks;
        this.mapGrid = new ArrayList<>();
        this.visitedOrder = new ArrayList<>();
        this.generateMapGrid();
        this.generateMaze();
    }

    @Override
    public void generateMapGrid() {
        int rowCount = (int) Math.sqrt(numBlocks);
        int blockHeight = blockSize / rowCount;
        int blockWidth = blockSize / rowCount;

        for (int z = 0; z < rowCount; z++) {
            int numInRowCount = (int) Math.sqrt(numBlocks);
            ArrayList<BasicBlocks> rowList = new ArrayList<>();

            for (int t = 0; t < numInRowCount; t++) {

                //position calculates from center of block
                int blockXPosition = ((t + 1) * blockWidth) - (blockWidth / 2);
                int blockYPosition = ((z + 1) * blockHeight) - (blockHeight / 2);


                BasicBlocks b = new BasicBlocks(blockHeight, blockWidth, blockXPosition, blockYPosition, true, z, t);
                rowList.add(b);
            }
            this.mapGrid.add(rowList);
        }
    }


    @Override
    public void generateMaze() {
        Random rand = new Random();
        int mazeDimensions = (int) Math.sqrt(this.numBlocks);

        //grabbing a random row cell value
        int r = rand.nextInt(mazeDimensions);
        //Making sure random number is odd
        while (r % 2 == 0) {
            r = rand.nextInt(mazeDimensions);
        }

        //grabbing a random column value
        int c = rand.nextInt(mazeDimensions);
        //Making sure random number is odd
        while (c % 2 == 0) {
            c = rand.nextInt(mazeDimensions);
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
                    if (c + 2 >=  (int) (Math.sqrt(this.numBlocks) - 1))
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
                    if (r + 2 >=  (int) Math.sqrt(this.numBlocks) - 1)
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
    public BasicBlocks getBlock(int x, int y) {
        return this.mapGrid.get(x).get(y);
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
    public int getBlockDimensions() {
        return this.blockSize;
    }

    @Override
    public int getGridSize() {
        return this.numBlocks;
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

}
