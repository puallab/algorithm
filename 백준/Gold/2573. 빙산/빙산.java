import java.util.*;
import java.io.*;
public class Main{

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }
    }

    static int n, m, cnt;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] board;
    static boolean[][] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                cnt += board[i][j];
            }
        }

        System.out.println(simul());

    }

    static int simul(){
        int year = 0;
        while(cnt != 0){
            year += 1;
            meltDown();
            int k = check();
            if(k >= 2) break;
        }
        if(cnt == 0) return 0;
        return year;
    }

    static void meltDown(){
        vis = new boolean[n][m];
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(board[i][j] != 0 || vis[i][j]) continue;
                bfs(i, j);
            }   
        }
    }


    static void bfs(int y, int x){
        vis[y][x] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));

        while(!q.isEmpty()){

            Pair now = q.poll();
            for(int i =0; i<4; i++){
                y= now.y + dy[i];
                x = now.x +dx[i];
                if(y <0 || x<0|| y>=n || x>=m || vis[y][x]) continue;
                
                if(board[y][x] > 0){
                    board[y][x] -= 1;
                    cnt -=1;
                    if(board[y][x] == 0) vis[y][x] = true;
                }else{
                    vis[y][x] = true;
                    q.add(new Pair(y, x));
                }
                
            }

        }
    }

    static int check(){
        int pieces  =0;
        vis = new boolean[n][m];
        for(int i=0; i<n; i++){
            for(int j =0; j<m; j++){
                if(vis[i][j] || board[i][j] == 0) continue;
                findOne(i, j);
                pieces += 1;
            }
        }
        return pieces;
    }

    static void findOne(int y, int x){
        vis[y][x] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));

        while(!q.isEmpty()){

            Pair now = q.poll();
            for(int i =0; i<4; i++){
                y= now.y + dy[i];
                x = now.x +dx[i];
                if(y <0 || x<0|| y>=n || x>=m || vis[y][x] || board[y][x] ==0) continue;
                vis[y][x] = true;
                q.add(new Pair(y, x));
            }

        }
    }

}