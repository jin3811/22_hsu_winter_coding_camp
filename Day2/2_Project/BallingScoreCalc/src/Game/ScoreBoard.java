package Game;

import ScoreFrame.*;

import java.util.Scanner;

public class ScoreBoard {
    // 10프레임의 정보를 저장할 배열
    private Frame scores[] = new Frame[10];
    
    // 각 프레임의 점수
    private int frameScore[] = new int[10];

    public ScoreBoard () {
        setScores(); // 프레임 정보를 입력받는다.
        setFrameScore(); // 점수를 계산한다.
    }

    public void setScores() {
        int f, s, t = 0; // 첫번째, 두번째, 세번째(10Frame) 점수

        Scanner scan = new Scanner(System.in);
        // 1 ~ 9번째 Frame, 10번째 프레임은 따로 입력받는다.
        for (int i = 0 ; i < scores.length - 1 ; i++) {
            System.out.println((i + 1) + "번째 Frame");

            // 해당 프레임의 첫번째 점수를 입력받는다.
            System.out.print(("첫번째 점수>> "));
            f = scan.nextInt();

            // 스트라이크의 경우, 해당 프레임의 두번째 기회가 없다.
            if (f == 10) {
                scores[i] = new Frame(f, 0);
                continue;
            }

            // 두번째 점수를 입력받는다.
            System.out.print(("두번째 점수>> "));
            s = scan.nextInt();

            // 입력받은 정보를 통해 Frame을 할당받는다.
            scores[i] = new Frame(f, s);
        }
        
        // 10번째 프레임 입력
        System.out.println("10번째 Frame");
        
        // 10번째 프레임의 첫번째 점수
        System.out.print(("첫번째 점수>> "));
        f = scan.nextInt();
        
        // 두번째 점수
        System.out.print(("두번째 점수>> "));
        s = scan.nextInt();

        // 10프레임 첫번째가 스트라이크거나, 두번째가 스페어라면 세번째 점수가 존재한다.
        if (f == 10 || f + s == 10) {
            System.out.print(("세번째 점수>> "));
            t = scan.nextInt();
        }

        // 입력을 받지 않으면 t(세번째 점수)는 초기값인 0으로 들어간다.
        scores[9] = new LastFrame(f, s, t);
    }

    public void setFrameScore() {
        int temp = 0;
        // 1 ~ 9프레임의 점수를 계산한다.
        // 10프레임의 경우, 스트라이크, 스페어의 추가 점수가 없음을 유의한다.
        for (int i = 0; i < scores.length; i++) {
            temp = 0;

            // 해당 프레임의 점수를 더한다.
            temp += scores[i].getScore();

            // 10프레임이라면 추가점수를 계산하지 않도록 한다.
            if (i == scores.length - 1) {
                frameScore[i] = temp;
                break;
            }

            // 스페어인 경우, 다음 프레임의 첫번째 점수까지 더한다.
            if (scores[i].isSpare()) {
                temp += scores[i + 1].getFirst();
            }
            // 스트라이크인 경우, 다음 프레임의 두 점수를 더한다.
            else if (scores[i].isStrike()) {
                temp += scores[i + 1].getFirst();

                // 다음 프레임도 스트라이크였을 경우
                if (scores[i + 1].isStrike()) {
                    // 9번째 프레임 전이라면, 다다음 프레임의 첫 공을 가져와야한다.
                    if (i < 8) {
                        temp += scores[i + 2].getFirst();
                    }
                    // 9번째 프레임이라면 다음 프레임이 스트라이크여도 다음 공이 존재한다.
                    else {
                        temp += scores[i + 1].getSecond();
                    }
                }
            }
            // 해당 프레임에서의 점수를 저장한다.
            frameScore[i] = temp;
        }

        // 이후 누적합을 통해 볼링 점수판을 만든다.
        for (int i = 1; i < frameScore.length; i++) {
            frameScore[i] += frameScore[i - 1];
        }
    }

    // 최종 점수는 frameScore 배열의 맨 마지막에 저장되어있다.
    public int calcTotalScore() {
        return frameScore[frameScore.length - 1];
    }

    // 볼링판을 출력한다.
    public void showScore() {
        String frame;
        // 몇번째 프레임인지 출력한다.
        System.out.print("   |");
        for (int i = 1; i <= scores.length; i++) {
            if (i < scores.length) System.out.print("   " + i + "   |");
            else System.out.print("    " + i + "     |");
        }
        System.out.println("");

        // 프레임의 정보를 출력한다.
        System.out.print("   |");
        for (int i = 0; i < scores.length; i++) {
            // 1 ~ 9번째 프레임
            if (i < scores.length - 1) {
                // 스트라이크라면
                if (scores[i].isStrike()) {
                    // 오른쪽에 X출력
//                    System.out.print("   | x |");
                    frame = "   | x |";
                }
                // 스페어라면
                else if (scores[i].isSpare()) {
                    // 첫번째 점수를 출력하고, 이후 스페어 표시
                    
                    // 거터인 경우 - 로 표시한다.
                    if (scores[i].getFirst() == 0) {
                        frame = " - | / |";
                    }
                    else {
                        frame = String.format(" %d | / |", scores[i].getFirst());
                    }
                }
                
                // 무엇도 아니라면
               else {
                   frame = " ";
                   
                   // 첫번째가 거터인 경우
                   if (scores[i].getFirst() == 0) {
                        frame = frame.concat("- |");
                   }
                   else {
                       frame = frame.concat(String.format("%d |", scores[i].getFirst()));
                   }

                    // 두번째가 거터인 경우
                    if (scores[i].getSecond() == 0) {
                        frame = frame.concat(" - |");
                    }
                    else {
                        frame = frame.concat(String.format(" %d |", scores[i].getSecond()));
                    }

//                   frame = String.format(" %d | %d |", scores[i].getFirst(), scores[i].getSecond());
                }
            }
            // 맨 마지막 프레임
            else {
                // 맨 마지막 프레임을 다운캐스팅한다.
                LastFrame last = (LastFrame)scores[i];
                
                // 첫번째 시도가 스트라이크인 경우.
                if (last.isStrike()) {
                    frame = " x | ";
                    if (last.getSecond() == 10) frame = frame.concat("x | ");
                    else if (last.getSecond() == 0) frame = frame.concat("- | ");
                    else frame = frame.concat(last.getSecond() + " | ");

                    if (last.getThird() == 10) frame = frame.concat("x | ");
                    else if (last.getThird() == 0) frame = frame.concat("- | ");
                    else frame = frame.concat(last.getThird() + " | ");
                }
                
                // 두번째까지 시도했을때 스페어인 경우
                else if (last.isSpare()) {
                    if (last.getFirst() == 0) {
                        frame = " - | / | ";
                    }
                    else {
                        frame = String.format(" %d | / | ", last.getFirst());
                    }
                    if (last.getThird() == 10) frame = frame.concat("x | ");
                    else if (last.getThird() == 0) frame = frame.concat("- | ");
                    else frame = frame.concat(last.getThird() + " | ");
                }
                
                // 아무것도 아닌 경우
                else {
                    frame = " ";

                    // 첫번째가 거터인 경우
                    if (scores[i].getFirst() == 0) {
                        frame = frame.concat("- |");
                    }
                    else {
                        frame = frame.concat(String.format("%d |", scores[i].getFirst()));
                    }

                    // 두번째가 거터인 경우
                    if (scores[i].getSecond() == 0) {
                        frame = frame.concat(" - |");
                    }
                    else {
                        frame = frame.concat(String.format(" %d |", scores[i].getSecond()));
                    }
                }
            }
            System.out.print(frame);
        }
        System.out.println(); // 개행

        // 프레임별 점수를 출력한다.
        System.out.print("   |");
        for (int i = 0 ; i < frameScore.length; i++) {
            if (i < frameScore.length - 1) System.out.print(String.format(" %3d   |", frameScore[i]));
            else System.out.print(String.format("  %3d      |", frameScore[i]));
        }
        System.out.println();
    }
}

/*
 * 볼링 점수
 * 1프레임당 최대 30점
 * 스트라이크는 다음 프레임의 1, 2번째 점수까지 합산 (즉, 9프레임에서 스트라이크를 치면 10프레임 3타는 점수에 포함하지 않는 것)
 * 맨 마지막 프레임에서는 스트라이크, 스페어 추가점수 X (이 전 프레임에는 당연히 영향을 준다.)
 * 맨 마지막 프레임은 첫번쨰 스트라이크 or 두번째 스페어인 경우만 3번째 기회가 생긴다.
 *
*/