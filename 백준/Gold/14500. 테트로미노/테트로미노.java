import java.util.*;
import java.io.*;

public class Main{
    static int n, m;
    static int ans;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][][] shape5 = { {{0, 0}, {0, 1}, {0, -1}, {1, 0} },
                                {{0, 0}, {0, 1}, {0, -1}, {-1, 0} },
                                {{0, 0}, {-1, 0}, {1, 0}, {0, 1}},
                                {{0, 0}, {-1, 0}, {1, 0}, {0, -1}} };
    static int[][] board;
    static boolean[][] vis;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        vis = new boolean[n][m];

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i = 0; i<n; i++){
            for(int j=0; j<m; j++){
                vis[i][j] = true;
                dfs(1, board[i][j], i, j);
                vis[i][j] = false;

                tranException(i, j);
            }
        }
        System.out.println(ans);
    }

    static void dfs(int idx, int sum, int y, int x){
        if(idx == 4){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i =0; i<4; i++){
            int ny = dy[i] + y;
            int nx = dx[i] + x;
            if(ny<0 || nx <0 || ny >= n || nx >=m || vis[ny][nx]) continue;
            vis[ny][nx] = true;
            dfs(idx+1, sum + board[ny][nx], ny, nx);
            vis[ny][nx] = false;
        }
    }

    static void tranException( int y, int x){
        for(int i =0; i<4; i++){
            int sum = 0;
            for(int j=0; j<4; j++){
                int ny = shape5[i][j][0] + y;
                int nx = shape5[i][j][1] + x;
                if(ny < 0 || nx <0 || ny >= n || nx >=m) {
                    sum = 0;
                    break;
                }
                sum += board[ny][nx];
            }
            ans = Math.max(ans, sum);
        }
    }
}