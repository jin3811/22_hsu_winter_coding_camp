import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        // 가로, 세로, 장애물 개수, 사냥감 개수
        int r, c, x, o;
        Scanner scan = new Scanner(System.in);

        System.out.print("지도의 행 개수 >> ");
        r = scan.nextInt();
        System.out.print("지도의 열 개수 >> ");
        c = scan.nextInt();
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

        System.out.println("맵이 생성되었습니다.");

        new HuntingGame(r, c, x, o);
    }
}
