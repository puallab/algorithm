import java.io.*;
import java.util.*;

public class boj18430 {

    static int n, m;
    static int map[][];
    static boolean vis[][];
    static int ans = 0;
    static int[][][] shape = { 
            { { 0, 1 }, { -1, 0 } },    // 우 상
            { { 0, 1 }, { 1, 0 } },     // 우 하
            { { 0, -1 }, { -1, 0 } },   // 좌 상
            { { 0, -1 }, { 1, 0 } }     // 좌 하
            };

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    static void input() throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        map = new int[n][m];
        vis = new boolean[n][m];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro() {
        if (n < 2 || m < 2 || n * m < 4)
            ans = 0;
        else
            dfs(0, 0);

        System.out.println(ans);
    }

    static void dfs(int idx, int sum) {

        if (idx == m * n) {
            ans = Math.max(sum, ans);
            return;
        }

        int i = idx / m;
        int j = idx % m;
        
        if (!vis[i][j]){
            for (int k = 0; k < 4; k++) {

                int ry = shape[k][0][0] + i;
                int rx = shape[k][0][1] + j;

                int ly = shape[k][1][0] + i;
                int lx = shape[k][1][1] + j;

                if (!isValid(ry, rx) || !isValid(ly, lx))
                    continue;
                if (vis[ry][rx] || vis[ly][lx])
                    continue;

                vis[i][j] = vis[ry][rx] = vis[ly][lx] = true;
                dfs(idx + 1, sum + map[ry][rx] + map[ly][lx] + map[i][j]*2);
                vis[i][j] = vis[ry][rx] = vis[ly][lx] = false;
            }
            
        }
        dfs(idx + 1, sum);

    }

    static boolean isValid(int y, int x) {
        return (y >= 0 && x >= 0 && y < n && x < m);
    }
}
