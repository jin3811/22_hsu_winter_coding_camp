package Prob3;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class CoffieVendingMachine extends JFrame {
    private JLabel title = new JLabel("Welcome, Hot Coffie!!");
    private Font font = new Font("Malgun Gothic", Font.PLAIN, 18);

    private final int MAXINGREDIENT = 10;

    private Gauze cupGauze = new Gauze(10);
    private Gauze coffieGauze = new Gauze(10);
    private Gauze waterGauze = new Gauze(10);
    private Gauze sugarGauze = new Gauze(10);
    private Gauze creamGauze = new Gauze(10);

    private JLabel cup = new JLabel("cup");
    private JLabel coffie = new JLabel("coffie");
    private JLabel water = new JLabel("water");
    private JLabel sugar = new JLabel("sugar");
    private JLabel cream = new JLabel("cream");
    private JLabel coffieImage = new JLabel(" ");

    private JButton blackCoffie = new JButton("Black Coffie");
    private JButton sugarCoffie = new JButton("Sugar Coffie");
    private JButton dabangCoffie = new JButton("Dabang Coffie");
    private JButton resetBtn = new JButton("reset");
    private Gauze gauzes[] = { cupGauze, coffieGauze, waterGauze, sugarGauze, creamGauze };
    private JLabel gauzeName[] = { cup, coffie, water, sugar, cream };
    private JButton menu[] = { blackCoffie, sugarCoffie, dabangCoffie, resetBtn };

    public CoffieVendingMachine() {
        super("커피 자판기");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Container c = getContentPane();
        setLayout(null);

        // 화면 상단 제목
        title.setHorizontalAlignment(JLabel.CENTER);
        title.setOpaque(true);
        title.setBackground(Color.green);
        title.setFont(font);
        title.setLocation(0, 0);
        title.setSize(this.getWidth(), 25);
        c.add(title);

        for (int i = 0; i < gauzes.length; i++) {
            gauzes[i].setLocation(80 * (i + 1) + gauzes[i].getWidth() * i, 40);
            c.add(gauzes[i]);
        }

        for (int i = 0; i < gauzeName.length; i++) {
            gauzeName[i].setFont(font);
            gauzeName[i].setSize(50, 30);
            gauzeName[i].setLocation(gauzes[i].getX(), 240);
            c.add(gauzeName[i]);
        }

        coffieImage.setSize(200, 30);
        coffieImage.setFont(font);
//        coffieImage.setLocation((c.getWidth() - coffieImage.getWidth()) / 2, (c.getHeight() - coffieImage.getHeight()) / 2);
        coffieImage.setLocation(300, 285);
        c.add(coffieImage);

        blackCoffie.addMouseListener(new MakeCoffie(0, 0));
        sugarCoffie.addMouseListener(new MakeCoffie(1,0));
        dabangCoffie.addMouseListener(new MakeCoffie(1,1));
        resetBtn.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (Gauze gauze : gauzes) {
                    gauze.fillFull();
                }
            }
        });

        for (int i = 0; i < menu.length; i++) {
            menu[i].setSize(180, 30);
            menu[i].setLocation(13 * (i + 1) + menu[i].getWidth() * i, /*c.getHeight() - menu[i].getHeight()*/ 530);
            c.add(menu[i]);
        }

        setVisible(true);
    }

    private class MakeCoffie extends MouseAdapter {
        // 소모할 재료양을 배열에 저장한다. 순서대로 컵, 커피, 물, 설탕, 크림.
//        private enum ingredientIdx { CUP, COFFIE, WATER, SUGAR, CREAM };
        private int ingredient[] = {1, 1, 1, 0, 0};

        public MakeCoffie(int sugar, int cream) {
            ingredient[3] = sugar;
            ingredient[4] = cream;
        }

        public boolean make() {
            // 만들기 전에 재료가 충분한지 먼저 체크한다.
            for (int i = 0; i < gauzes.length; i++) {
                if (gauzes[i].getLeft() < ingredient[i]) {
                    return false; // 재료가 부족하기 때문에 만들지 않고, false를 return
                }
            }

            // 재료가 모두 충분한 경우에 해당 코드에 도달할 수 있다.
            for (int i = 0; i < gauzes.length; i++) {
                gauzes[i].consume(ingredient[i]); // 재료를 소모하여 만든다.
            }
            return true;
        }

        @Override
        public void mousePressed(MouseEvent e) {
            if (make()) {
                coffieImage.setText("대충 커피 사진");
                JOptionPane.showMessageDialog(getContentPane(), "뜨거워요. 즐거운 하루...", "커피 완성", JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                JOptionPane.showMessageDialog(getContentPane(), "부족한 재료가 있습니다. 채워주세요", "커피 제작 불가", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

}