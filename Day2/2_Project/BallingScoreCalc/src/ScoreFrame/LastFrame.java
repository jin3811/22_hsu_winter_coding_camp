package ScoreFrame;

public class LastFrame extends Frame {
    private int third;

    public LastFrame(int first, int second, int third) {
        super(first, second);
        this.third = third;
    }

    public LastFrame() {
        super();
        this.third = -1;
    }

    @Override
    public int getScore() {
        return super.getScore() + third;
    }

    public int getThird() { return third; }
}
