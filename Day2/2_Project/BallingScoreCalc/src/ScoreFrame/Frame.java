package ScoreFrame;

public class Frame {
    private int first;
    private int second;

    private boolean strike;
    private boolean spare;

    public Frame() {
        this.first = -1;
        this.second = -1;
        strike = false;
        spare = false;
    }

    public Frame(int first, int second) {
        this.first = first;
        this.second = second;

        if (first == 10) strike = true;
        else if (first + second == 10) spare = true;
    }

    public int getFirst() { return first; }

    public int getSecond() { return second; }

    public int getScore() { return first + second; }

    public boolean isStrike() { return strike; }

    public boolean isSpare() { return spare; }
}