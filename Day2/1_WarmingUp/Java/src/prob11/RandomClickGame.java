package prob11;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Random;

public class RandomClickGame extends JFrame {
    private JLabel tenLabel[] = new JLabel[10];
    private int correctClick = 0;
    private Random r = new Random();

    private Font font = new Font("나눔 고딕", Font.BOLD, 20);
    public RandomClickGame() {
        super("Ten 레이블 게임");
        setSize(450, 450);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        for (int i = 0; i < tenLabel.length; i++) {
            tenLabel[i] = new JLabel(Integer.toString(i + 1));
            tenLabel[i].setSize(40, 40);
            tenLabel[i].addMouseListener(new LabelClick(tenLabel[i]));
            getContentPane().add(tenLabel[i]);
        }
        setLabelPos();
        setVisible(true);
    }

    public void setLabelPos() {
        for (JLabel label : tenLabel) {
            int newX = r.nextInt(this.getWidth() - label.getWidth());
            int newY = r.nextInt(this.getHeight() - label.getHeight());
            label.setLocation(newX, newY);
            label.setVisible(true);
        }
    }

    class LabelClick extends MouseAdapter {
        private JLabel label;

        public LabelClick(JLabel label) {
            super();
            this.label = label;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            label.setVisible(false);
            correctClick += 1;
            System.out.println("CorrectClick: " + correctClick);
            if (correctClick == 10) {
                setLabelPos();
                correctClick = 0;
            }
        }
    }
}
