import java.io.*;
import java.util.*;

public class Main{

    static class Pair{
        int y, x;

        public Pair(int yy, int xx){
            y = yy;
            x = xx;
        }
    }

    static int n, m, t;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] board;
    static int[][] vis;
    static boolean flag= false;
    

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        board = new int[n][m+1];
        vis = new int[n][m];

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i =0; i<t; i++){
            st=  new StringTokenizer(br.readLine());
            int x = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int k = Integer.parseInt(st.nextToken());

            simul(x, d, k);

        }

        System.out.println(getAns());
    }

    static void simul(int x, int d, int k){
        flag = false;
        
        for(int i =0; i<n; i++){
            if( (i+1)%x == 0 ){
                for(int j =0; j<k; j++){
                    rotate(i, d);
                }
            }
        }
        
        vis = new int[n][m];
        for(int i =0; i<n; i++){
            for(int j=0; j<m; j++){
                if(board[i][j] == 0 || vis[i][j] != 0) continue;
                findAdjacent(i, j, board[i][j]);
            }
        }

        

        if(flag){
            deletValue();
        }else{
            changeValue();
        }
        
    }

    static void rotate(int idx, int dir){
        if(dir == 0){
            for(int i =m; i>=0; i--){
                int k = (i+m)%(m+1);
                board[idx][i] = board[idx][k];
            }
        }else{
            for(int i =0; i<=m; i++){
                int k = (i+m)%(m+1);
                board[idx][k] = board[idx][i];
            }
        }
    }

    static void findAdjacent(int y, int x, int val){

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        vis[y][x] = -1;

        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i =0; i<4; i++){
                int yy = dy[i] + now.y;
                int xx = dx[i] + now.x;

                if(yy <0  || yy>=n ) continue;
                else if( xx == -1 ){
                    xx = m-1;
                }else if ( xx == m ){
                    xx = 0;
                }
                if(vis[yy][xx] != 0 || val != board[yy][xx]) continue;

                vis[yy][xx] = 1;
                vis[y][x] = 1;

                q.add(new Pair(yy, xx));
            }
        
        
        }


        if(vis[y][x] == 1) flag= true;
    }

    static void deletValue(){
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(vis[i][j] == 1){
                    board[i][j] = 0;
                }
            }
        }
    }

    static void changeValue(){
        //평균 구하기
        double cnt = 0;
        double sum =0;
        double avg =0;
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(board[i][j] == 0) continue;
                cnt += 1;
                sum += board[i][j];
            }
        }

        avg = sum/cnt;

        for(int i =0; i<n; i++){
            for(int j =0 ;j<m; j++){
                if(board[i][j] == 0) continue;
                if((double)board[i][j] > avg){
                    board[i][j] -= 1;
                }
                else if((double)board[i][j] < avg) {
                    board[i][j] +=1;
                }
            }
        }
    }

    static int getAns(){
        int val = 0;
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                val += board[i][j];
            }
        }
        return val;
    }

}