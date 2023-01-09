import java.util.Scanner;

public class Prob2 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("배열의 크기를 입력하시오.");
        int index = scan.nextInt();
        int[] arr = new int[index];

        System.out.println("배열의 입력값을 띄어쓰기로 구분하여 입력");
        for (int i = 0; i < index; i++) {
            arr[i] = scan.nextInt();
        }

        System.out.println("결과 : " + singleNumber(arr));
    }

    public static int singleNumber(int[] arr) {
        int result = arr[0];
        boolean isFind = false;

        for (int i = 0 ; i < arr.length; i++) {
            int target = arr[i];
            isFind = false;
            for (int j = 0; j < arr.length; j++) {
                if (i == j) continue;
                if (target == arr[j]) {
                    isFind = true;
                    break;
                }
            }
            if (!isFind) result = target;
        }

        return result;
    }
}
