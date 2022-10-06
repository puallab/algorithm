import java.util.*;
import java.io.*;

public class Main{
    static class Pair{
        int y, x;
        public Pair(int yy ,int xx){
            y = yy;
            x = xx;
        }
    }
    static int n, m, ans = Integer.MAX_VALUE, zeroCnt =0;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    static int[][] board;

    static boolean[] selected;
    static boolean[][] vis;
    static List<Pair> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 2){
                    list.add(new Pair(i, j));
                }
                else if(board[i][j] == 0){
                    zeroCnt += 1;
                }
            }
        }

        selected = new boolean[list.size()];

        dfs(0, 0);
        if(ans == Integer.MAX_VALUE){
            ans = -1;
        }

        System.out.println(ans);
    }

    static void dfs(int idx, int picked){
        if(picked == m){
            ans = Math.min(ans, timeToSpread());
            return;
        }

        for(int i = idx; i< selected.length; i++){
            selected[i] = true;
            dfs(i+1, picked+1);
            selected[i] = false;
        }
    }

    static int timeToSpread(){
        int zCnt = 0, depth = 0;
        Queue<Pair> q = new LinkedList<>();
        vis = new boolean[n][n];
        for(int i =0; i<selected.length; i++){
            if(selected[i]){
                q.add(list.get(i));
                vis[list.get(i).y][list.get(i).x] = true;
            }
        }

        while(!q.isEmpty()){
            if(zeroCnt == zCnt){
                return depth;
            }
            int qsize = q.size();
            depth += 1;
            while(qsize -- > 0){
                Pair now = q.poll();
                for(int i =0; i<4; i++){
                    int y = now.y + dy[i];
                    int x = now.x + dx[i];
                    if(y < 0 || x < 0 || y >=n || x>=n || vis[y][x] || board[y][x] == 1) continue;
                    if(board[y][x] == 0){
                        zCnt++;
                    }
                    q.add(new Pair(y, x));
                    vis[y][x] = true;
                }
            }

            
        }

        return Integer.MAX_VALUE;
    }
}