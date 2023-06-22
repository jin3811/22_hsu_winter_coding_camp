import java.util.Scanner;

public class Prob20 {
    public static void main(String[] args) {
        int money;
        int bill[] = {50000, 10000, 1000, 500, 100, 10, 1};
        String billName[] = {"오만원", "만원", "천원", "500원", "100원", "10원", "1원"};

        Scanner scan = new Scanner(System.in);
        System.out.print("돈의 액수를 입력하세요>>");
        money = scan.nextInt();

        for (int i = 0; i < bill.length; i++) {
            System.out.print(billName[i] + " " + money / bill[i] + "개");
            money %= bill[i];

            if (i < bill.length - 1) System.out.print(", ");
        }
    }
}
