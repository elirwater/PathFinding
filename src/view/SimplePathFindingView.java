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
        this.lastTick = b.getFinalTick(0) + 1;
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
        return null;
    }


    @Override
    protected void paintComponent(Graphics g) {

        MapInterface map = (MapInterface) this.m.getMap();
        int blockXSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / map.getGridSize().getX());
        int blockYSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / map.getGridSize().getY());

        int blockSize = Math.min(blockXSize, blockYSize);


        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();


            ArrayList<ArrayList<BasicBlocks>> toDraw = map.getMapGrid();
        int i = 0;
            for (ArrayList<BasicBlocks> ba : toDraw) {
                for (int x = 0; x < ba.size(); x++) {
                    BasicBlocks b = ba.get(x);
                    if (b.getBlocked()) {
                        g.setColor(Color.gray);
                        g.fillRect(blockSize * x, blockSize * i, blockSize, blockSize);
                    }
                    if (b.isBot()) {
                        g.setColor(Color.blue);
                        g.fillRect(blockSize * x, blockSize * i, blockSize, blockSize);
                    }
                    if (b.isGoal()) {
                        g.setColor(Color.red);
                        g.fillRect(blockSize * x, blockSize * i, blockSize, blockSize);
                    }
                    if (b.isPath()) {
                        g.setColor(Color.MAGENTA);
                        g.fillRect(blockSize * x, blockSize * i, blockSize, blockSize);
                    }
                    g.setColor(Color.gray);
                    g.drawRect(blockSize * x, blockSize * i, blockSize, blockSize);
                }
                i ++;
            }
        g2d.dispose();
    }


}
