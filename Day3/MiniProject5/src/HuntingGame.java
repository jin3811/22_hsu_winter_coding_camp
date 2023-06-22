import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;

public class HuntingGame extends JFrame {
    private int row; // 지도의 행 개수
    private int col; // 지도의 열 개수
    private int xCount; // 사냥감 수
    private int oCount; // 장애물 수
    private String innerBoard[][]; // 문자열로 된 내부 지도 정보.
    private JLabel board[][]; // innerBoard를 참조하여 JLabel로 변환한다.
    private Random r = new Random(); // 장애물과 사냥감 랜덤 배치를 위한 객체
    private Font font = new Font("나눔 고딕", Font.BOLD, 16);

    public HuntingGame(int row, int col, int xCount, int oCount) {
        super("Hunting Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(row, col));
        // 맵의 최소크기는 480x270이다.
        setSize(48 * row < 480 ? 480 : 48 * row,
                27 * col < 270 ? 270 : 27 * col);
        Container c = getContentPane();
        
        // 멤버 변수 초기화
        this.row = row;
        this.col = col;
        this.xCount = xCount;
        this.oCount = oCount;

        // 안내 메세지
        System.out.println("맵 생성중...");

        // 문자열로 표현한 화면을 초기화한다.
        // 여러번 초기화되는 상황을 방지하기 위해 생성자에서 초기화한다.
        innerBoard = new String[row][col];
        for(int i = 0; i < innerBoard.length; i++) {
            for (int j = 0; j < innerBoard[i].length; j++) {
                // 공백의 경우, 내부적으로 "-"로 표기한다.
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
                // 찾은 위치에 장애물을 배치함
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
                // 찾은 위치에 사냥감을 배치함.
                innerBoard[y][x] = "X";
            }
            // 배치가 완료되면 지도의 유효성을 체크한다.
        } while(!Validation.checkMapValidation(innerBoard));
    }

    // 배치가 완료된 문자열 지도를 통해 JLabel을 설정한다.
    private void setLabel() {
        for(int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // 공백의 경우, 내부적으로 "-"로 표기하므로 JLabel에서 공백으로 출력한다.
                if (innerBoard[i][j].equals("-")) {
                    board[i][j].setText(" ");
                }
                else {
                    board[i][j].setText(innerBoard[i][j]);
                }
            }
        }
    }
    
    // 로봇의 움직임을 제어하는 키보드 이벤트 클래스
    private class MoveEvent extends KeyAdapter {
        private int curX, curY; // 로봇의 현재 위치

        public MoveEvent() {
            super();
            // 초기 위치는 좌측 최하단이므로, 해당 위치를 맨 처음에 저장한다.
            this.curX = 0;
            this.curY = col - 1;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            int newX = curX; // 이동할 X좌표
            int newY = curY; // 이동할 Y좌표
            int keyCode = e.getKeyCode(); // 눌린 키를 불러온다.

            switch(keyCode) {
                case KeyEvent.VK_W: // W키, 위로 움직인다.
                    newY = curY - 1;
                    // 지도륾 벗어난 위치라면 움직이지 않는다.
                    if (newY < 0) return;
                    break;
                case KeyEvent.VK_A: // A키, 왼쪽으로 움직인다.
                    newX = curX - 1;
                    // 지도륾 벗어난 위치라면 움직이지 않는다.
                    if (newX < 0) return;
                    break;

                case KeyEvent.VK_S: // S키, 아래로 움직인다.
                    newY = curY + 1;
                    // 지도륾 벗어난 위치라면 움직이지 않는다.
                    if (newY > innerBoard.length) return;
                    break;

                case KeyEvent.VK_D: // D키, 오른쪽으로 움직인다.
                    newX = curX + 1;
                    // 지도륾 벗어난 위치라면 움직이지 않는다.
                    if (newX >= innerBoard[0].length) return;
                    break;
            }

            // 움직이고자하는 위치가 장애물이 있는 곳이라면 움직이지 않는다.
            if(innerBoard[newY][newX].equals("O")) return;

            // 움직이고자하는 위치가 사냥감이 있는 곳이라면 사냥감의 수를 줄인다.
            if(innerBoard[newY][newX].equals("X")) {
                xCount -= 1;
            }

            // 이동한다.
            innerBoard[newY][newX] = "R";
            innerBoard[curY][curX] = "-";

            // 사냥감을 모두 사냥하기 전에 도착지점에 도착하고 다른 곳으로 움직이는 경우
            // "-"가 아닌 "E"로 표시한다
            if (curY == 0 && curX == innerBoard[0].length - 1) {
                innerBoard[curY][curX] = "E";
            }

            // 만약 도착지점에 도달한 경우
            // 사냥감의 수에 따라 분기를 나눈다.
            if(newY == 0 && newX == innerBoard[0].length - 1) {
                // 모든 사냥감을 사냥한 경우 게임을 끝낸다
                if (xCount == 0) {
                    System.out.println("축하합니다!!!!!");
                    System.exit(0);
                }

                // 사냥감이 남은 경우는 안내메세지를 표시한다.
                else {
                    System.out.println("아직 사냥감이 남아있습니다.");
                }
            }

            // 현재 위치를 갱신하고
            curX = newX;
            curY = newY;

            // JLabel을 갱신한다.
            setLabel();
        }
    }
}
