import java.util.*;
import java.io.*;

public class Main {

    static class CCTV {
        int y, x, type;

        public CCTV(int yy, int xx, int tt) {
            y = yy;
            x = xx;
            type = tt;
        }
    }

    static int n, m, ans = Integer.MAX_VALUE;
    static int[] dirs;
    static int[] dy = {0, 1, 0, -1};
    static int[] dx = {-1, 0, 1, 0};
    static int[][] board, copyBoard;
    static List<CCTV> cList = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < m; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                if (board[i][j] % 6 != 0) {
                    cList.add(new CCTV(i, j, board[i][j]));
                }
            }
        }

        dirs = new int[cList.size()];
        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int idx) {
        if (idx == cList.size()) {
            init();
            onCCTV();
            ans = Math.min(countBlindSpot(), ans);
            return;
        }

        for (int i = 0; i < 4; i++) {
            dirs[idx] = i;
            dfs(idx + 1);
            dirs[idx] = -1;
        }
    }

    static void init(){
        copyBoard = new int[n][m];
        for(int i =0; i<n; i++){
            System.arraycopy(board[i], 0, copyBoard[i], 0, m );
        }
        
    }

    static void onCCTV() {
        for (int idx = 0; idx < cList.size(); idx++) {
            CCTV cctv = cList.get(idx);
            int dir = dirs[idx];
            switch (cctv.type) {
                case 1:
                    watch(dir, cctv);
                    break;
                case 2:
                    watch(dir, cctv);
                    watch((dir+2)%4, cctv);
                    break;
                case 3:
                    watch(dir, cctv);
                    watch((dir+1)%4, cctv);
                    break;
                case 4:
                    watch(dir, cctv);
                    watch((dir+1)%4, cctv);
                    watch((dir+2)%4, cctv);
                    break;
                case 5:
                    watch(0, cctv);
                    watch(1, cctv);
                    watch(2, cctv);
                    watch(3, cctv);
                    break;
            }
        }
    }

    static void watch(int dir, CCTV cctv){
        int y = cctv.y;
        int x = cctv.x;
        while(true){
            y += dy[dir];
            x += dx[dir];

            if(y<0 || x < 0 || y>=n || x>=m ||  board[y][x] == 6) break;
            copyBoard[y][x] = -1;
        }
    }


    static int countBlindSpot(){
        int val = 0;
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(copyBoard[i][j] == 0) 
                    val++;
            }
        }
        return val;
    }

}