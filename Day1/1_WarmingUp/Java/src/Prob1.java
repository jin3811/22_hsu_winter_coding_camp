public class Prob1 {
    private int rdata[];
    private final int SIZE = 10;
    public Prob1() {
        rdata = new int[SIZE];
        for (int i = 0; i < SIZE; i++) {
            rdata[i] = (int)(Math.random() * 101 + 100);
        }
    }

    public void showData() {
        for (int elem : rdata) {
            System.out.print(elem + " ");
        }
        System.out.println();
    }

    public double getAvg() {
        double avg = 0;
        for (int elem : rdata) {
            avg += elem / (double)SIZE;
        }

        return avg;
    }

    public static void main(String[] args) {
        Prob1 p = new Prob1();
        double result;
        p.showData();
        result = p.getAvg();
        System.out.println(result);
    }
}
