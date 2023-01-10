import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class Prob10 extends JFrame {
    private JTextField input = new JTextField(10);
    private JButton btn = new JButton("check");
    private JLabel result = new JLabel("faweio");
    private Random r = new Random();
    private int bound[] = {0, 99};
    private int target;
    public Prob10() {
        super("0~99 Up % Down game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(null);
        Container c = getContentPane();

        input.setLocation(40, 20);
        input.setSize(100, 20);
        input.addActionListener(new MyEvent());
        c.add(input);

        btn.setLocation(input.getX() + input.getWidth() + 20, input.getHeight());
        btn.setSize(100, 20);
        btn.addActionListener(new MyEvent());
        c.add(btn);

        result.setSize(100, 20);
        result.setLocation(100, 150);
        c.add(result);

        setVisible(true);

        target = r.nextInt(100);
    }

    class MyEvent implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int user = Integer.parseInt(input.getText());

            if (user < target) {
                bound[0] = user;
                result.setText("더 높게 " + bound[0] + "~" + bound[1]);
            }
            else if (user > target) {
                bound[1] = user;
                result.setText("더 낮게 " + bound[0] + "~" + bound[1]);
            }
            else if (user < 0 || user > 100) {
                result.setText(user + ": 0~99 범위를 벗어났습니다.");
            }
            else {
                result.setText(target + " 맞췄습니다!");
            }
            input.setText("");
        }
    }

    public static void main(String[] args) {
        new Prob10();
    }
}