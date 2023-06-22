// 여러 검증을 위한 클래스. static 메서드와 멤버변수로만 이루어져
// 객체를 생성하지 않고, 불러다 쓸 수 있도록 한다.
public class Validation {
    // x, y좌표 변경값을 미리 저장한다.
    private static int dx[] = {1, 0, -1, 0};
    private static int dy[] = {0, -1, 0, 1};
    
    // 지도를 복사하여 저장할 배열
    private static String copyGraph[][];

    /*
     * 지도의 유효성 검증에서 확인해야할 리스트는 다음과 같다.
     * 1. 시작 위치(R)에서 도착 위치(E)로 가는 경로가 존재하는가?
     * 2. 사냥감(X)은 모두 사냥 가능한가?, 즉 R에서 사냥감으로 가는 경로가 존재하는가?
     * 모든 조건을 만족해야 유효한 지도이다.
    */
    public static boolean checkMapValidation(String graph[][]) {
        copyGraph = new String[graph.length][graph[0].length];

        // 지도를 복사해서 사용한다.
        makeCopyGraph(graph);

        // R -> E 경로가 있는지 조사.
        boolean result = dfs(graph[0].length - 1, 0);

        // R에서 E로 가는 경로가 없다면 유효성X
        if (!result) return false;

        // 사냥감을 모두 사냥할 수 있는지 검사
        for (int y = 0; y < graph.length; y++) {
            for (int x = 0; x < graph[y].length; x++) {
                if (graph[y][x].equals("X")) {
                    // 지도를 복사해서 사용한다.
                    makeCopyGraph(graph);

                    // 시작 위치에서 사냥감으로 가는 경로가 있는지 조사한다.
                    result = dfs(x, y);

                    // 사냥할수 없는 사냥감이 존재한다면 유효성X
                    if (!result) return false;
                }
            }
        }
        // 위 테스트들을 통과했다면 유효한 지도이다.
        return true;
    }

    // 지도를 깊은 복사한다.
    private static void makeCopyGraph(String g[][]) {
        for (int i = 0; i < g.length; i++) {
            copyGraph[i] = g[i].clone();
        }
    }

    /*
     * (stX, stY)에서 R로 가는 경로가 있는지 dfs를 통해 조사함.
     * 지도는 무방향 그래프이기 때문에 R에서 (stX, stY)로 가는 경로가 있다는 것은 역의 경우도 당연히 성립한다.
     */
    private static boolean dfs(int stX, int stY) {
        // R에 도착했다면 경로가 존재함. true
        if (stX == 0 && stY == copyGraph.length - 1) return true;

        // 다음 좌표
        int newX, newY;
        
        // 결과
        boolean result = false;
        
        // 방문처리
        copyGraph[stY][stX] = "V";

        for (int i = 0; i < dx.length; i++) {
            // 새로운 좌표를 구한다.
            newX = stX + dx[i];
            newY = stY + dy[i];

            // 이동하고자 하는 좌표가 갈 수 있는 좌표인지 검사한다.
            if (newX < 0 || newX >= copyGraph[0].length) continue;
            if (newY < 0 || newY >= copyGraph.length) continue;

            // 해당 위치가 장애물이 있거나, 방문한 좌표인 경우가 아닌 경우에 재귀한다.
            if (!copyGraph[newY][newX].equals("O") &&
                !copyGraph[newY][newX].equals("V")   ) {
                copyGraph[newY][newX] = "V";
                result = result || dfs(newX, newY);
            }

            // result가 true가 되었다면 이는 경로가 존재한다는 것이다. 더 조사하지 말고 끝낸다.
            if (result) return true;
        }
        // 경로 없음
        return false;
    }
}
