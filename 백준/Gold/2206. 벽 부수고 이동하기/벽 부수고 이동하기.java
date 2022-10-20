import java.util.*;
import java.io.*;

public class Main{
    static class Tuple{
        int y, x;
        boolean flag;

        public Tuple(int yy, int xx ,boolean f){
            y =yy;
            x =xx;
            flag = f;
        }
    }
    static int n, m;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] board;
    static int[][][] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for(int i =0; i<n; i++){
            String s= br.readLine();
            for(int j =0; j<m; j++){
                board[i][j] = (int)(s.charAt(j)-'0');
            }
        }
        if(n == 1 && m == 1) System.out.println(1);
        else System.out.println(bfs());

    }

    static int bfs(){
        vis = new int[2][n][m];
        vis[0][0][0] = 1;
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0, 0, false));
        
        while(!q.isEmpty()){
            Tuple now = q.poll();
            for(int i =0; i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];
                boolean flag = now.flag;

                if(y < 0 || x< 0|| y>=n || x>=m ) continue;
                if(flag){
                    if(vis[1][y][x] != 0) continue;
                    if(board[y][x] == 1) continue;
                    vis[1][y][x] = vis[1][now.y][now.x] + 1;
                    q.add(new Tuple(y, x, true));

                }else{
                    if(vis[0][y][x] != 0) continue;
                    if(board[y][x] == 1){
                        q.add(new Tuple(y, x, true));
                        vis[1][y][x] = vis[0][now.y][now.x] + 1;
                    }else{
                        vis[0][y][x] = vis[0][now.y][now.x] + 1;
                        q.add(new Tuple(y, x, false));
                    }
                }


                if(y == n-1 && x == m-1){
                    return Math.max(vis[0][y][x], vis[1][y][x]);
                }

            }
        }
        return -1;
    }   

}