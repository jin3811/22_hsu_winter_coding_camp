package ScoreFrame;

public class Frame {
    // 각각 첫번째, 두번째 점수
    private int first;
    private int second;

    // 해당 프레임이 스트라이크인지 볼인지 저장한다.
    private boolean strike;
    private boolean spare;
    
    public Frame(int first, int second) {
        this.first = first;
        this.second = second;
    
        // 첫 시도가 10점이면 스트라이크
        if (first == 10) strike = true;
        
        // 두번의 시도 합쳐서 10점이면 스트라이크
        else if (first + second == 10) spare = true;
    }

    // getter
    public int getFirst() { return first; }

    public int getSecond() { return second; }

    public int getScore() { return first + second; }

    public boolean isStrike() { return strike; }

    public boolean isSpare() { return spare; }
}