package Frame;

public class Frame {
    private int first;
    private int second;
    private int third;

    private boolean strike;
    private boolean spare;

    public Frame(int first, int second, int third) {
        this.first = first;
        this.second = second;
        this.third = third;

        if (first == 10) strike = true;
        else if (first + second == 10) spare = true;
    }

    public int getFirst() { return first; }

    public int getSecond() { return second; }

    public boolean isStrike() { return strike; }

    public boolean isSpare() { return spare; }
}