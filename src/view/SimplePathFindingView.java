package view;

import model.ModelInterface;
import model.blocks.BasicBlocks;
import model.bots.BotInterface;
import model.maps.MapInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.Map;

/**
 * Abstract class for abstracting all of the common view functionality.
 */
abstract class SimplePathFindingView extends JPanel implements ViewInterface {

    protected final ModelInterface m;
    protected final BotInterface bot;
    protected FramePanel f;
    protected final int tps;
    protected int lastTick;
    protected int currentTick = 0;

    public SimplePathFindingView (ModelInterface m, int tps) {
        this.m = m;
        this.lastTick = 0;
        this.tps = tps;
        this.bot = (BotInterface) m.getBot();
        this.f = new FramePanel((MapInterface) this.m.getMap());
    }


    @Override
    public void animate() throws InterruptedException {
        BotInterface b = (BotInterface) this.m.getBot();
        this.lastTick = b.getFinalTick() + 1;
        f.setLayout(new BorderLayout());

        f.add(this, BorderLayout.CENTER);
        f.pack();
        f.setVisible(true);

        this.render();
    }


    @Override
    public void render() throws InterruptedException {
        while (this.currentTick <= this.lastTick) {
            Thread.sleep(1000 / this.tps);
            this.repaint();
            this.currentTick ++;
        }
    }


    @Override
    public Dimension getPreferredSize() {
        MapInterface map = (MapInterface) m.getMap();
        int dimensions = (int) (map.getBlockDimensions() + Math.sqrt(map.getGridSize()));
        return new Dimension(dimensions, dimensions);
    }


    @Override
    protected void paintComponent(Graphics g) {


        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

            MapInterface map = (MapInterface) this.m.getMap();
            ArrayList<ArrayList<BasicBlocks>> toDraw = map.getMapGrid();
            for (ArrayList<BasicBlocks> ba : toDraw) {
                for (int x = 0; x < ba.size(); x++) {
                    BasicBlocks b = ba.get(x);
                    if (b.getBlocked()) {
                        g.setColor(Color.gray);
                        g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                    }
                    if (b.isBot()) {
                        g.setColor(Color.blue);
                        g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                    }
                    if (b.isGoal()) {
                        g.setColor(Color.green);
                        g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                    }
                    if (b.isPath()) {
                        g.setColor(Color.MAGENTA);
                        g.fillRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                    }
                    g.setColor(Color.gray);
                    g.drawRect(b.getX(), b.getY(), b.getWidth(), b.getHeight());
                }
            }
        g2d.dispose();
    }


}
