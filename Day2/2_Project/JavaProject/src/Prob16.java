import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Prob16 extends JFrame {
    private JLabel startLabel = new JLabel("start: ");
    private JTextField start = new JTextField(20);

    private JLabel endLabel = new JLabel("end: ");
    private JTextField end = new JTextField(20);

    private JButton btn = new JButton("calc!");
    private int resultValue = -1;
    private JLabel result = new JLabel(Integer.toString(resultValue));

    private Calc calcThread;

    public Prob16() {
        super("스레드 연습 16번");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(300, 300);
        setLayout(null);
        Container c = getContentPane();

        startLabel.setSize(80, 20);
        startLabel.setLocation(10, 10);
        c.add(startLabel);

        start.setSize(100, 20);
        start.setLocation(50, 10);
        c.add(start);

        endLabel.setSize(80, 20);
        endLabel.setLocation(10, 50);
        c.add(endLabel);

        end.setSize(100, 20);
        end.setLocation(50, 50);
        c.add(end);

        calcThread = new Calc();
        calcThread.start();

        btn.setSize(100, 40);
        btn.setLocation(160, 20);
        btn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int l = Integer.parseInt(start.getText());
                int r = Integer.parseInt(end.getText());

                calcThread.setBound(l, r);
                calcThread.start();
                notify();
                try {
                    calcThread.join();
                }
                catch (InterruptedException eeee) {
                    result.setText(Integer.toString(resultValue));
                    return;
                }
                result.setText(Integer.toString(resultValue));
            }
        });

        c.add(btn);

        result.setSize(100, 20);
        result.setLocation(50, 150);
        c.add(result);

        setVisible(true);
    }

    public synchronized void setResult(int l, int r) {
        int sum = 0;
        for (int i = l; i <= r; i++) sum += i;
        result.setText(Integer.toString(sum));
        notify();
    }

    private class Calc extends Thread {
        private int l, r;

        // [l, r] 설정
        public void setBound(int l, int r) {
            this.l = l;
            this.r = r;
            setResult(l, r);
        }

        @Override
        public void run() {
    //            synchronized (this) {
    //                setResult(l, r);
    //            }
    //            notify();
            while (true) {
                try {
                    wait();
                }
                catch (InterruptedException ee) {
                    return;
                }

                int sum = 0;
                for (int i = l; i <= r; i++) sum += i;
//                result.setText(Integer.toString(sum));
                resultValue = sum;
                notify();
            }
        }
    }

    public static void main(String[] args) {
        new Prob16();
    }
}