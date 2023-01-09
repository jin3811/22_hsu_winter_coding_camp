package Prob3;

import javax.swing.*;
import java.awt.*;

public class CoffieVendingMachine extends JFrame {
    private JLabel title = new JLabel("Welcome, Hot Coffie!!");
    private Font font = new Font("Malgun Gothic", Font.PLAIN, 18);

    public CoffieVendingMachine() {
        super("커파 자판기");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(null);
        Container c = getContentPane();

        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.green);
        title.setFont(font);
        title.setLocation(0, 0);
        title.setSize(this.getWidth(), 25);
        c.add(title);


        setVisible(true);
    }

    public static void main(String[] args) {
        new CoffieVendingMachine();
    }
}

class Gauze extends JLabel {
    private int barSize;

    public Gauze(int size) {

    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.MAGENTA);
        int height = (int)(((double)(this.getHeight())) / maxBarSize * barSize);
        if (height == 0) return;
        g.fillRect(0, 0, this.getWidth(), height);
    }
}