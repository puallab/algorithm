import java.io.*;
import java.util.*;

public class Main {

    static int n, ans, bCnt;
    static int[] dirs = new int[5];
    static int[] dy = { 0, 0, 1, -1 };
    static int[] dx = { 1, -1, 0, 0 };
    static int[][] board;
    static int[][] temp;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] != 0) {
                    bCnt++;
                }
            }
        }

        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int idx) {
        if (idx == 5) {
            boardInit();
            ans = Math.max(go(), ans);
            return;
        }

        for (int i = 0; i < 4; i++) {
            
            dirs[idx] = i;
            dfs(idx + 1);
            dirs[idx] = -1;
        }
    }

    static void boardInit() {
        temp = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                temp[i][j] = board[i][j];
            }
        }
    }

    static int go() {
        int nCnt = bCnt;
        //int[] dirs = new int[]{1, 3, 0, 0, 0};
        for (int i = 0; i < 5; i++) {
            if (nCnt == 1)
                break;
            nCnt -= move(dirs[i]);
        }

        return findBiggestValue();
    }

    static int move(int dir) {
        boolean[][] vis = new boolean[n][n];
        int mergeCnt = 0;
        switch (dir) {
            case 0:
                for (int y = 0; y < n; y++) {
                    for (int x = n - 2; x >= 0; x--) {
                        if(temp[y][x] == 0) continue;
                        int xPoint = x;
                        int val = temp[y][x];
                        temp[y][x] = 0;
                        while (true) {
                            xPoint += dx[dir];
                            if (xPoint == n || vis[y][xPoint] || (temp[y][xPoint] !=0 && temp[y][xPoint] != val)) {
                                temp[y][xPoint - dx[dir]] = val;
                                break;
                            }
                            if (temp[y][xPoint] == val) {
                                vis[y][xPoint] = true;
                                temp[y][xPoint] = 2 * val;
                                mergeCnt++;
                                break;
                            }
                        }
                    }
                }
                break;
            case 1:
                for (int y = 0; y < n; y++) {
                    for (int x = 0; x < n; x++) {
                        int xPoint = x;
                        if(temp[y][x] == 0 ) continue;
                        int val = temp[y][x];
                        temp[y][x] = 0;
                        while (true) {
                            xPoint += dx[dir];
                            if (xPoint == -1 || vis[y][xPoint] || (temp[y][xPoint] !=0 && temp[y][xPoint] != val)) {
                                
                                temp[y][xPoint - dx[dir]] = val;
                                break;
                            }
                            if (temp[y][xPoint] == val) {
                                vis[y][xPoint] = true;
                                temp[y][xPoint] = 2 * val;
                                mergeCnt++;
                                break;
                            }
                        }
                    }
                }
                break;
            case 2:
                for (int x = 0; x < n; x++) {
                    for (int y = n - 1; y >= 0; y--) {
                        if(temp[y][x] == 0) continue;
                        int yPoint = y;
                        int val = temp[y][x];
                        temp[y][x] = 0;
                        while (true) {
                            yPoint += dy[dir];
                            if (yPoint == n || vis[yPoint][x] || (temp[yPoint][x] != 0 && temp[yPoint][x] != val)) {
                                
                                temp[yPoint - dy[dir]][x] = val;
                                break;
                            }
                            if (temp[yPoint][x] == val) {
                                vis[yPoint][x] = true;
                                temp[yPoint][x] = 2 * val;
                                mergeCnt++;
                                break;
                            }
                        }
                    }
                }
                break;
            case 3:
                for (int x = 0; x < n; x++) {
                    for (int y = 0; y < n; y++) {
                        if(temp[y][x] == 0) continue;
                        int yPoint = y;
                        int val = temp[y][x];
                        temp[y][x] = 0;
                        while (true) {
                            yPoint += dy[dir];
                            if (yPoint == -1 || vis[yPoint][x] || (temp[yPoint][x] != 0 && temp[yPoint][x] != val)) {
                                
                                temp[yPoint - dy[dir]][x] = val;
                                break;
                            }
                            if (temp[yPoint][x] == val) {
                                vis[yPoint][x] = true;
                                temp[yPoint][x] = 2 * val;
                                mergeCnt++;
                                break;
                            }
                        }
                    }
                }
                break;

        }
        return mergeCnt;
    }

    static int findBiggestValue() {
        int biggiestVal = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                biggiestVal = Math.max(biggiestVal, temp[i][j]);
            }
        }
        return biggiestVal;
    }

}
