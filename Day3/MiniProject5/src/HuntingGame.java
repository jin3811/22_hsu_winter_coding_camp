import javax.swing.*;
import java.awt.*;
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

    public HuntingGame(int row, int col, int xCount, int oCount) {
        super("Hunting Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(row, col));
        setSize(48 * row, 27 * col);
        Container c = getContentPane();
        
        // 멤버 변수 초기화
        this.row = row;
        this.col = col;
        this.xCount = xCount;
        this.oCount = oCount;

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

        setRandomMatrix();
        setLabel();

        setVisible(true);
        c.setFocusable(true);
        c.requestFocus();

        System.out.println(Validation.checkMapValidation(innerBoard));
    }

    // R, E, x, o를 조건에 맞게 배치한다.
    private void setRandomMatrix() {
        int x, y;

        // 로봇과 도착 위치를 배치한다.
        innerBoard[0][col - 1] = "E"; // 도착 위치는 우측 최상단
        innerBoard[row - 1][0] = "R"; // 시작 위치는 좌측 최하단

        // 장애물 배치
        for (int i = 0 ; i < oCount; i++) {
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
        for (int i = 0 ; i < xCount; i++) {
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
}
