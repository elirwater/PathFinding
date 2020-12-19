package model;


import model.blocks.BasicBlocks;
import model.bots.BotInterface;
import model.maps.MapInterface;
import model.types.BotTypes;
import model.types.MapTypes;

import java.util.ArrayList;

//idea for interface parameter usage: a basic path finding model should be able to use multiple bot types and map types
public class BasicPathFindingModel implements ModelInterface<BotInterface, MapInterface> {

    private final Pair gridSize;
    private MapInterface m;
    private BotInterface b;

    private String mapType;
    private String botType;



    public BasicPathFindingModel(Pair gridSize, String mapType, String botType) {
        this.gridSize = gridSize;
        this.mapType = mapType;
        this.botType = botType;
        this.generateMap();
        this.generateBot();
    }


    @Override
    public void generateMap() {
        this.m = new MapTypes().choose(mapType, gridSize);
    }

    @Override
    public void generateBot() {
        this.b = new BotTypes().choose(botType, this.m);
    }


    @Override
    public BotInterface getBot() {
        return b;
    }

    @Override
    public MapInterface getMap() {
        return m;
    }

    @Override
    public void generateBotRuns(int numRuns) {

    }

    @Override
    public BasicBlocks getRunVisit(int tick) {
        return null;
    }


    @Override
    public int getFinalTick() {
        return this.b.getFinalTick(0);
    }
}
