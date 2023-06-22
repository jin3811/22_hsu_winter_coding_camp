import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // 가로, 세로, 장애물 개수, 사냥감 개수
        int r, c, x, o;
        Scanner scan = new Scanner(System.in);

        // 지도의 정보를 입력받는다.
        System.out.print("지도의 행 개수 >> ");
        r = scan.nextInt();
        System.out.print("지도의 열 개수 >> ");
        c = scan.nextInt();

        // 장애물과 사냥감의 경우, 음수, 또는 빈칸의 개수보다 많은 입력을 할 경우 다시 입력받도록 한다.
        System.out.print("사냥감 개수 (사냥감은 " + (r * c - 2) + "개를 초과할 수 없습니다.) >> ");
        while (true) {
            x = scan.nextInt();
            if (x > r * c - 2 || x < 0) {
                System.out.println("잘못된 입력입니다");
            } else break;
        }

        System.out.print("장애물 개수 (장애물은 " + (r * c - 2 - x) + "개를 초과할 수 없습니다.) >> ");
        while (true) {
            o = scan.nextInt();
            if (o > r * c - 2 - x || o < 0) {
                System.out.println("잘못된 입력입니다");
            } else break;
        }
        
        // 정보를 전달하여 게임을 시작함
        new HuntingGame(r, c, x, o);
    }
}
