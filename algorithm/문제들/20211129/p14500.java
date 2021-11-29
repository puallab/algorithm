import java.util.*;
import java.io.*;
import java.nio.Buffer;

/**
 *  45분 소요.. 큰 배열 할당할 땐 꼭 재할당이 필요한지 확인.
 */
public class Main {
    
    static int n, m;
    static int ans = 0;
    static int[] dy = {0,0,1,-1};
    static int[] dx = {1,-1,0,0};
    static boolean[][] vis;
    static int[][] board = new int[501][501];
    static int[][][] map5 = {
        { {0,0},{0,-1}, {0,1}, {1,0} },
        { {0,0}, {0,-1}, {-1,0}, {1,0} },
        { {0,0}, {0,-1}, {-1,0}, {0,1} },
        { {0,0}, {-1,0}, {0,1}, {1,0} }
    };

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st=  new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j= 0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        vis = new boolean[n][m];
    }

    
    public static void dfs(int nn, int sum, int y, int x){
        if(nn == 4){
            
            ans = Math.max(sum, ans);
            return;
        }

        for(int i =0; i<4; i++){
            int cy = y+ dy[i];
            int cx = x+ dx[i];

            if(cy<0 || cy>=n || cx<0 || cx>= m) continue;
            if(vis[cy][cx] == true) continue;
            vis[cy][cx] = true;
            dfs(nn+1, sum+board[cy][cx], cy, cx);
            vis[cy][cx] = false;
        }

        return;
    }


    public static void calcModel4(int y, int x){
        for(int i =0; i<4; i++){
            int k =0, cnt = 0;;
            for(int j =0; j<4; j++){
                int cy =  map5[i][j][0] + y;
                int cx = map5[i][j][1]+ x;
                if(cy< 0 || cy>= n || cx< 0 || cx>=m) continue;
                k += board[cy][cx];
                cnt++;
            }
            if(cnt == 4) ans = Math.max(ans,k);
        }
    }

    public static void pro(){
       
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                vis[i][j] = true;
                dfs(1, board[i][j], i, j);
                vis[i][j] = false;

                //2번쟤 보드 놓기.
                calcModel4(i, j);
            }
        }

        System.out.println(ans);
    }

    
    
}

