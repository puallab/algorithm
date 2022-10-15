import java.util.*;
import java.io.*;

public class Main {

    static class Runner {
        int y, x, dir, idx;
        boolean alive = true;

        public Runner(int yy, int xx, int d, int i) {
            y = yy;
            x = xx;
            dir = d;
            idx = i;
        }
    }

    static class Police {
        int y, x, dir, point;
        boolean rotate = true;

        public Police(int yy, int xx, int d, int p, boolean r) {
            y = yy;
            x = xx;
            dir = d;
            point = p;
            rotate = r;
        }

    }

    static int n, m, h, k;
    static int[] dy = { -1, 0, 1, 0 };
    static int[] dx = { 0, 1, 0, -1 };

    static Police police;
    static Runner[] runners;
    static ArrayList<Runner>[][] board;
    static boolean[][] isTree, isCurve;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        police = new Police(n / 2, n / 2, 0, 0, true);
        isTree = new boolean[n][n];
        isCurve = new boolean[n][n];
        runners = new Runner[m];
        board = new ArrayList[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                board[i][j] = new ArrayList<>();
            }
        }

        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int d = Integer.parseInt(st.nextToken());
            runners[i] = new Runner(y, x, d, i);
            board[y][x].add(runners[i]);
        }

        for (int i = 0; i < h; i++) {
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            isTree[y][x] = true;
        }

        makeMap();
        simul();
    }

    static void makeMap() {
        int y = 0, x = 0, d = 2;
        boolean[][] vis = new boolean[n][n];
        vis[0][0] = true;
        isCurve[0][0] = true;
        while (true) {
            if (y == n / 2 && x == n / 2) {
                isCurve[y][x] = true;
                break;
            }
            int yy = y + dy[d];
            int xx = x + dx[d];

            if (yy < 0 || xx < 0 || yy >= n || xx >= n || vis[yy][xx]) {
                isCurve[y][x] = true;
                d = (d + 3) % 4;
                yy = y + dy[d];
                xx = x + dx[d];
            }

            y = yy;
            x = xx;
            vis[y][x] = true;
        }

        //showBoard(isCurve, n, n);
    }

    static void simul() {
        int t = 0;
        while (t != k) {
            t++;
            //showRunners();
            runnerMove();
            //showRunners();
            policeMove(t);
        }
        System.out.println(police.point);
    }

    static void runnerMove() {
        for (int i = 0; i < m; i++) {
            if (runners[i].alive == false)
                continue;
            if (getDistance(police.y, police.x, runners[i].y, runners[i].x) > 3)
                continue;
            Runner runner = runners[i];
            int y = runner.y + dy[runner.dir];
            int x = runner.x + dx[runner.dir];
            if (y < 0 || x < 0 || y >= n || x >= n) {
                // 격자밖
                runner.dir = (runner.dir + 2) % 4;
                y = runner.y + dy[runner.dir];
                x = runner.x + dx[runner.dir];
                if (getDistance(police.y, police.x, y, x) != 0) {
                    // 술래 없으면 이동
                    board[y][x].add(runner);

                    board[runner.y][runner.x].remove(runner);
                    runner.y = y;
                    runner.x = x;
                }

            } else {
                // 격자안
                if (getDistance(police.y, police.x, y, x) != 0 ) {
                    // 술래가 없거나, 나무가 있으면 움직임
                    board[y][x].add(runner);
                    board[runner.y][runner.x].remove(runner);
                    runner.y = y;
                    runner.x = x;
                }

            }

        }
    }

    static int getDistance(int y1, int x1, int y2, int x2) {

        return Math.abs(y1 - y2) + Math.abs(x1 - x2);

    }

    static void policeMove(int t) {
        // 일단 무조건 움직여야함.
        police.y += dy[police.dir];
        police.x += dx[police.dir];

        if (isCurve[police.y][police.x]) {
            if (police.rotate) {
                police.dir = (police.dir + 1) % 4;
            } else {
                police.dir = (police.dir + 3) % 4;
            }
        }

        if (police.y == 0 && police.x == 0) {
            police.rotate = false;
            police.dir = 2;
        }

        if (police.y == n / 2 && police.x == n / 2) {
            police.rotate = true;
            police.dir = 0;
        }

        // d 방향으로 탐색 나 포함.
        int y = police.y;
        int x = police.x;
        int catchCnt = 0;
        for (int i = 0; i < 3; i++) {
            if (y < 0 || x < 0 || y >= n || x >= n)
                break;
            if (!isTree[y][x]) {
                // 술래가 있으면
                if (board[y][x].size() > 0) {
                    // 술래 삭제.
                    for (Runner runner : board[y][x]) {
                        runner.alive = false;
                        catchCnt++;
                    }
                    board[y][x] = new ArrayList<>();
                }
            }

            y += dy[police.dir];
            x += dx[police.dir];
        }

        police.point += catchCnt * t;

    }

    static void showBoard(boolean[][] bb, int r, int c) {
        System.out.println("\n");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(bb[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void showRunners() {
        System.out.println("\n");
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j].size() + " ");
            }
            System.out.println();
        }
    }
}