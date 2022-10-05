
import java.io.*;
import java.util.*;

public class Main {

    static class Shark {
        int y, x, size, cnt;

        public Shark(int yy, int xx, int s, int c) {
            y = yy;
            x = xx;
            size = s;
            cnt = c;
        }
    }

    static class Pair {
        int y, x, cnt;

        public Pair(int yy, int xx, int c) {
            y = yy;
            x = xx;
            cnt = c;
        }
    }

    static int n;
    static int[] dy = { 0, 0, 1, -1 };
    static int[] dx = { 1, -1, 0, 0 };
    static int[][] board;
    static boolean[][] vis;
    static Shark shark;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        board = new int[n][n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] == 9) {
                    board[i][j] = 0;
                    shark = new Shark(i, j, 2, 0);
                }
            }
        }

        System.out.println(simul());
    }

    static int simul() {

        int val = 0;
        int t = 0;
        while (true) {
            val = search();
            if (val == -1) {
                break;
            }
            t += val;
        }
        return t;
    }

    static int search() {

        vis = new boolean[n][n];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(shark.y, shark.x, 0));
        vis[shark.y][shark.x] = true;
        boolean flag = false;
        Pair moved = null;

        while (!q.isEmpty()) {
            int qsize = q.size();
            if (flag) {

                
                board[moved.y][moved.x] = 0;
                shark.y = moved.y;
                shark.x = moved.x;
                shark.cnt += 1;
                if (shark.cnt == shark.size) {
                    shark.size += 1;
                    shark.cnt = 0;
                }
                return moved.cnt;
            }

            while (qsize-- > 0) {
                Pair pos = q.poll();

                for (int i = 0; i < 4; i++) {
                    int y = dy[i] + pos.y;
                    int x = dx[i] + pos.x;

                    if (y < 0 || x < 0 || y >= n || x >= n || vis[y][x])
                        continue;
                    if (board[y][x] > shark.size)
                        continue;

                    q.add(new Pair(y, x, pos.cnt+1));
                    vis[y][x] = true;

                    if (board[y][x] != 0 && board[y][x] < shark.size) {
                        if (moved == null) {
                            moved = new Pair(y, x, pos.cnt+1);
                        } else {
                            if (y < moved.y || (y == moved.y && x < moved.x)) {
                                moved.y = y;
                                moved.x = x;
                            }
                        }
                        flag = true;
                    }

                }

            }
        }
        return -1;
    }

    static void showBoard(){
        System.out.println("\n");
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}