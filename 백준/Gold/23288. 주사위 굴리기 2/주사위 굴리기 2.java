import java.util.*;
import java.io.*;
import java.text.ParsePosition;

public class Main {

    static class Pair {
        int y, x;

        public Pair(int yy, int xx) {
            y = yy;
            x = xx;
        }
    }

    static class Dice {
        int y, x, d;
        int point;
        int[] arr = new int[] { 4, 1, 3, 6, 2, 5 };

        public Dice(int yy, int xx, int dd, int p){
            y = yy;
            x = xx;
            d = dd;
            point = p;
        }

        public void forword() {
            int yy = y + dy[d];
            int xx = x + dx[d];
            if (yy < 0 || xx < 0 || yy >= n || xx >= m) {
                d = (d + 2) % 4;
                yy = y + dy[d];
                xx = x + dx[d];
            }
            
            int temp = arr[3];
            switch (d) {
                case 0:
                    arr[3] = arr[2];
                    arr[2] = arr[1];
                    arr[1] = arr[0];
                    arr[0] = temp;
                    break;
                case 1:
                    arr[3] = arr[5];
                    arr[5] = arr[1];
                    arr[1] = arr[4];
                    arr[4] = temp;
                    break;
                case 2:
                    arr[3] = arr[0];
                    arr[0] = arr[1];
                    arr[1] = arr[2];
                    arr[2] = temp;
                    break;
                case 3:
                    arr[3] = arr[4];
                    arr[4] = arr[1];
                    arr[1] = arr[5];
                    arr[5] = temp;
                    break;

            }

            y= yy;
            x= xx;
        }

    }

    static int n, m, k;
    static int[] dy = { 0, 1, 0, -1 };
    static int[] dx = { 1, 0, -1, 0 };
    static int[][] board, points;
    static Dice dice = new Dice(0, 0, 0, 0);

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        points = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simul();
        
    }

    static void simul() {

        // 0. 판의 값 미리 구해놓기
        makePoints();
        while(k-- > 0){
            dice.forword();
            getPoint();
        }
        System.out.println(dice.point);

    }

    static void makePoints() {

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (points[i][j] > 0)
                    continue;
                bfs(board[i][j], i, j);
            }
        }

    }

    static void bfs(int val, int y, int x) {

        Queue<Pair> q = new LinkedList<>();
        Queue<Pair> t = new LinkedList<>();
        int cnt = 1;
        q.add(new Pair(y, x));
        t.add(new Pair(y, x));
        points[y][x] = val;

        while (!q.isEmpty()) {
            Pair now = q.poll();
            for (int i = 0; i < 4; i++) {
                int yy = dy[i] + now.y;
                int xx = dx[i] + now.x;
                if (yy < 0 || xx < 0 || yy >= n || xx >= m || points[yy][xx] != 0 || board[yy][xx] != val)
                    continue;
                cnt += 1;
                points[yy][xx] = val;
                q.add(new Pair(yy, xx));
                t.add(new Pair(yy, xx));
            }
        }

        while (!t.isEmpty()) {
            Pair now = t.poll();
            points[now.y][now.x] = cnt * val;
        }
    }

    static void getPoint(){
        dice.point += points[dice.y][dice.x];
        int valBoard = board[dice.y][dice.x];

        if(dice.arr[3] > valBoard ){
            dice.d = (dice.d+1)%4;
        }else if(dice.arr[3] < valBoard){
            dice.d = (dice.d+3)%4;
        }
    }
}