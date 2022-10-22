
import java.util.*;
import java.io.*;
import java.nio.Buffer;

public class Main{
    static class Tuple{
        int y, x, cnt;
        boolean isfire;
        public Tuple(int yy, int xx, int c, boolean t){
            y =yy;
            x =xx;
            cnt = c;
            isfire = t;
        }
    }
    static int n, m;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1 , 0, 0};
    static char[][] board;
    static boolean[][] vis;
    static Queue<Tuple> q = new LinkedList<>();
    static Tuple jh;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new char[n][m];
        vis = new boolean[n][m];
        for(int i =0; i<n; i++){
            String s = br.readLine();
            for(int j=0; j<m; j++){
                board[i][j] = s.charAt(j);
                if(board[i][j] == 'J'){
                    board[i][j] = '.';
                    jh = new Tuple(i, j, 0, false);
                }else if( board[i][j] == 'F'){
                    q.add(new Tuple(i, j, 0, true));
                    //vis[i][j] = true;
                }
            }
        }

        int ans = bfs();
        if(ans == Integer.MAX_VALUE){
            System.out.println("IMPOSSIBLE");
        }else{
            System.out.println(ans+1);
        }

    }

    static int bfs(){

        q.add(jh);
        vis[jh.y][jh.x] = true;
        if(isExit(jh.y, jh.x)){
            return 0;
        }

        while(!q.isEmpty()){

            Tuple now = q.poll();
            for(int i =0; i<4; i++){
                int y = now.y + dy[i];
                int x= now.x + dx[i];
                if(y <0 || y>= n || x<0|| x>=m || board[y][x] =='#' || board[y][x] == 'F' ) continue;

                if(now.isfire){
                    // 다 태워 
                    board[y][x] = 'F';
                    q.add(new Tuple(y, x, now.cnt +1, true));
                }else{
                    if(vis[y][x] ) continue;
                    if(isExit(y, x)){
                        return now.cnt+1;
                    }
                    vis[y][x] = true;
                    q.add(new Tuple(y, x, now.cnt+1, false));
                    
                }
            }
        }
        return Integer.MAX_VALUE;
    }

    static boolean isExit(int y, int x){
        return (y == 0 || y == n-1 || x == 0 || x== m-1);
    }
}