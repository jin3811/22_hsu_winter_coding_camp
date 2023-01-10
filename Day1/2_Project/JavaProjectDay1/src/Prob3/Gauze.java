package Prob3;

import javax.swing.*;
import java.awt.*;

class Gauze extends JLabel {
    private int barSize;
    private int maxBarSize;

    public Gauze(int maxBarSize) {
        setOpaque(true);
        this.maxBarSize = maxBarSize;
        this.barSize = maxBarSize;
        setSize(60, 200);
        setBackground(Color.BLACK);
    }

    public int getLeft() { return barSize; }

    public void consume(int n) {
        barSize -= n;
        this.getParent().repaint();
    }

    public void fillFull() {
        barSize = maxBarSize;
        this.getParent().repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.BLUE);
        int height = (int)(((double)(this.getHeight())) / maxBarSize * barSize);
        if (height == 0) return;
//        g.fillRect(0, 0, this.getWidth(), height);
        g.fillRect(0, this.getHeight() - height, this.getWidth(), this.getHeight());
    }
}