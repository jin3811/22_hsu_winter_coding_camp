package Prob3;

import javax.swing.*;
import java.awt.*;

public class CoffieVendingMachine extends JFrame {
    private JLabel title = new JLabel("Welcome, Hot Coffie!!");
    private Font font = new Font("Malgun Gothic", Font.PLAIN, 18);
    private Gauze cupGauze = new Gauze(100);
    private Gauze coffieGauze = new Gauze(100);
    private Gauze waterGauze = new Gauze(100);
    private Gauze sugarGauze = new Gauze(100);
    private Gauze creamGauze = new Gauze(100);
    private JLabel cup = new JLabel("cup");
    private JLabel coffie = new JLabel("coffie");
    private JLabel water = new JLabel("water");
    private JLabel sugar = new JLabel("sugar");
    private JLabel cream = new JLabel("cream");

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

        cupGauze.setLocation(100, 30);
        c.add(cupGauze);

        cup.setFont(font);
        cup.setSize(50, 20);
        cup.setLocation(cupGauze.getX(), 230);
        c.add(cup);

        setVisible(true);
    }

    public static void main(String[] args) {
        new CoffieVendingMachine();
    }
}

class Gauze extends JLabel {
    private int barSize = 0;
    private int maxBarSize;

    public Gauze(int maxBarSize) {
        setOpaque(true);
        this.maxBarSize = maxBarSize;
        this.barSize = maxBarSize;
        setSize(50, 200);
        setBackground(Color.BLUE);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(Color.MAGENTA);
        int height = (int)(((double)(this.getHeight())) / maxBarSize * barSize);
        if (height == 0) return;
        g.fillRect(0, 0, this.getWidth(), height);
    }

    public void consume(int n) {
        barSize -= n;
        this.getParent().repaint();
    }

    public void fillFull() {
        barSize = maxBarSize;
        this.getParent().repaint();
    }
}