package view;

import model.ModelInterface;
import model.blocks.BasicBlocks;
import model.maps.MapInterface;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class BasicMapView extends JPanel implements ViewInterface {

    private final ModelInterface model;
    private final int tps;
    private boolean finalDisplay = false;
    private final MapInterface map;
    private int alpha = 0;



    public BasicMapView(ModelInterface model, int tps) {
        this.model = model;
        this.tps = tps;
        this.map = (MapInterface) model.getMap();

    }

    public void animate() throws InterruptedException {

        FramePanel frame = new FramePanel((MapInterface) this.model.getMap());
        frame.add(this);
        frame.pack();
        frame.setVisible(true);
        this.render();
    }


    public void render() throws InterruptedException {

        while (alpha < map.getVisitedOrder().size()) {
            this.repaint();
            Thread.sleep(5);
        }
        this.finalDisplay = true;
        this.repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {


        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D) g.create();


        int blockXSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getWidth() / map.getGridSize().getX());
        int blockYSize = (int) (Toolkit.getDefaultToolkit().getScreenSize().getHeight() / map.getGridSize().getY());

        int blockSize = Math.min(blockXSize, blockYSize);

        if (!this.finalDisplay) {
            for (int x = 0; x < alpha; x++) {
                BasicBlocks b = map.getVisitedOrder().get(x);


                if (x + 1 == alpha) {
                    g.setColor(Color.blue);
                    g.fillRect(blockSize * b.getColumnPos(), blockSize * b.getRowPos(), blockSize, blockSize);
                }
                g.drawRect(blockSize * b.getColumnPos(), blockSize * b.getRowPos(), blockSize, blockSize);

            }
            alpha++;
        } else {
            for (ArrayList<BasicBlocks> ba : map.getMapGrid()) {
                for (int x = 0; x < ba.size(); x++) {
                    BasicBlocks b = ba.get(x);
                    if (b.getBlocked()) {
                        g.fillRect(blockSize * b.getColumnPos(), blockSize * b.getRowPos(), blockSize, blockSize);
                    }
                    g.drawRect(blockSize * b.getColumnPos(), blockSize * b.getRowPos(), blockSize, blockSize);
                }
            }

        }

        g2d.dispose();


    }
}
