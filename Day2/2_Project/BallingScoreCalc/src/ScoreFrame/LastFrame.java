package ScoreFrame;

// 프레임을 상속받아 세번째 시도를 추가한다.
public class LastFrame extends Frame {
    private int third;

    public LastFrame(int first, int second, int third) {
        super(first, second); // 슈퍼 클래스의 생성자를 실행하고
        this.third = third; // 세번째 점수를 저장한다.
    }

    // 슈퍼 클래스의 getScore에 세번째 점수까지 더하여 return한다.
    @Override
    public int getScore() { return super.getScore() + third; }

    // getter
    public int getThird() { return third; }
}
