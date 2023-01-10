import Game.ScoreBoard;

public class App {
    public static void main(String[] args) {
        ScoreBoard sc = new ScoreBoard();
        sc.showScore();
        System.out.println("합계: " + sc.calcTotalScore());
    }
}