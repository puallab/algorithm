import java.io.*;
import java.util.*;

public class Main{
    static int n, m, ans, cnt = -3;
    static int[][] board;
    static List<Pair> list = new ArrayList<>();
    static boolean[][] vis;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static class Pair{
        private int y, x;

        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }

        public Pair(){
            this(-1, -1);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0){
                    list.add(new Pair(i, j));
                    cnt++;
                }
            }
        }

       
        dfs(0, 0);

        System.out.println(ans);
    }

    static void dfs(int picked, int idx){
        if(picked == 3){
            ans = Math.max(ans, getAns());
            return;
        }

        for(int i =idx; i<list.size(); i++){
            Pair now = list.get(i);
            board[now.y][now.x] = 1;
            dfs(picked+1, i+1);
            board[now.y][now.x] = 0;
        }
    }

    static int getAns(){
        vis = new boolean[n][m];
        int ret =0;
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(vis[i][j] || board[i][j] != 2 ) continue;
                ret += bfs(i,j);
            }
        }
        return cnt-ret;
    }

    static int bfs(int y, int x){
        vis[y][x] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        int ret= 1;
        
        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i =0; i<4; i++){
                int yy = now.y + dy[i];
                int xx = now.x + dx[i];
                if(yy <0 || yy>=n || xx <0 || xx>=m || vis[yy][xx] || board[yy][xx] == 1 ) continue;
                vis[yy][xx]  = true;
                q.add(new Pair(yy, xx));
                ret++;
            }
        }

        return ret;
    }

    
}

