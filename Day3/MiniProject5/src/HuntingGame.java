import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class HuntingGame extends JFrame {
    private int row;
    private int col;
    private int xCount;
    private int oCount;
    private String innerBoard[][];
    private JLabel board[][];
    private Random r = new Random();
    private Font font = new Font("나눔 고딕", Font.BOLD, 16);

    private int curX, curY;

    public HuntingGame(int row, int col, int xCount, int oCount) {
        super("Hunting Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(row, col));
        // 맵의 최소크기는 320x180이다.
        setSize(48 * row < 480 ? 480 : 48 * row,
                27 * col < 270 ? 270 : 27 * col);
        Container c = getContentPane();
        
        // 멤버 변수 초기화
        this.row = row;
        this.col = col;
        this.xCount = xCount;
        this.oCount = oCount;
        this.curX = 0;
        this.curY = col - 1;

        System.out.println("맵 생성중...");

        // 문자열로 표현한 화면을 초기화한다.
        // 여러번 초기화되는 상황을 방지하기 위해 생성자에서 초기화한다.
        innerBoard = new String[row][col];
        for(int i = 0; i < innerBoard.length; i++) {
            for (int j = 0; j < innerBoard[i].length; j++) {
                innerBoard[i][j] = "-";
            }
        }

        // 실제 화면에 포현할 JLbael을 초기화한다.
        // 이 역시 여러번 초기화되는 상황을 방지하기 위해 생성자에서 초기화한다.
        board = new JLabel[row][col];
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new JLabel("123");
                board[i][j].setHorizontalAlignment(JLabel.CENTER);
                board[i][j].setFont(font);
                c.add(board[i][j]);
            }
        }

        // 먼저 문자열로 지도를 생성하고, JLabel로 만든다.
        // 이때, 지도는 항상 유효하다(모든 사냥감을 사냥하고 도착 지점에 도착할수 있다).
        setRandomMatrix();
        setLabel();
        c.addKeyListener(new MoveEvent());

        setVisible(true);
        c.setFocusable(true);
        c.requestFocus();

        System.out.println("맵이 생성되었습니다.");
    }

    // R, E, x, o를 조건에 맞게 배치한다.
    private void setRandomMatrix() {
        int x, y;

        // 로봇과 도착 위치를 배치한다.
        innerBoard[0][col - 1] = "E"; // 도착 위치는 우측 최상단
        innerBoard[row - 1][0] = "R"; // 시작 위치는 좌측 최하단

        do { // 유효한 맵일때까지 반복한다.
            // 장애물 배치
            for (int i = 0; i < oCount; i++) {
                while (true) {
                    // 랜덤한 위치를 발생시킨다.
                    x = r.nextInt(col);
                    y = r.nextInt(row);

                    // 해당 위치의 유효성 검증, 빈 칸이어야만 배치한다.
                    if (innerBoard[y][x].equals("-")) break;
                    else continue;
                }
                innerBoard[y][x] = "O";
            }

            // 사냥감 배치
            for (int i = 0; i < xCount; i++) {
                while (true) {
                    // 랜덤한 위치를 발생시킨다.
                    x = r.nextInt(col);
                    y = r.nextInt(row);

                    // 해당 위치의 유효성 검증, 빈 칸이어야만 배치한다.
                    if (innerBoard[y][x].equals("-")) break;
                    else continue;
                }
                innerBoard[y][x] = "X";
            }
        } while(!Validation.checkMapValidation(innerBoard));

        // 검증
        innerBoard = innerBoard;
        for(int i = 0; i < innerBoard.length; i++) {
            for (int j = 0; j < innerBoard[i].length; j++) {
                System.out.print(innerBoard[i][j] + " ");
            }
            System.out.println();
        }
    }

    private void setLabel() {
        Container c = getContentPane();
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (innerBoard[i][j].equals("-"))
                    board[i][j].setText("---");
                else
                    board[i][j].setText(innerBoard[i][j]);
            }
        }
    }

    private class MoveEvent extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int newX = curX;
            int newY = curY;
            int keyCode = e.getKeyCode();

            switch(keyCode) {
                case KeyEvent.VK_W:
                    newY = curY - 1;
                    if (newY < 0) return;
                    break;
                case KeyEvent.VK_A:
                    newX = curX - 1;
                    if (newX < 0) return;
                    break;

                case KeyEvent.VK_S:
                    newY = curY + 1;
                    if (newY > innerBoard.length) return;
                    break;

                case KeyEvent.VK_D:
                    newX = curX +1;
                    if (newX >= innerBoard[0].length) return;
                    break;
            }

            if(innerBoard[newY][newX].equals("O")) return;
            if(innerBoard[newY][newX].equals("X")) {
                System.out.println("사냥!");
                xCount -= 1;
            }
            innerBoard[newY][newX] = "R";
            innerBoard[curY][curX] = "-";
            if (curY == 0 && curX == innerBoard[0].length - 1) {
                innerBoard[curY][curX] = "E";
            }

            if(newY == 0 && newX == innerBoard[0].length - 1) {
                if (xCount == 0) {
                    System.out.println("축하합니다!!!!!");
                    System.exit(0);
                }
                else {
                    System.out.println("아직 사냥감이 남아있습니다.");
                }
            }

            curX = newX;
            curY = newY;
            setLabel();
        }
    }
}
