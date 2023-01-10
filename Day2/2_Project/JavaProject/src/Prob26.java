interface AddInterface {
    public int add(int x, int y);
    public int add(int n);
}

class MyAdder implements AddInterface {
    @Override
    public int add(int x, int y) {
        return x + y;
    }

    @Override
    public int add(int n) {
        return n * (n + 1) / 2;
    }
}

public class Prob26 {
    public static void main(String[] args) {
        MyAdder adder = new MyAdder();
        System.out.println(adder.add(5, 10));
        System.out.println(adder.add(10));
    }
}
