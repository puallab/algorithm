import java.io.*;
import java.util.*;

public class Main{
    static int n, m;
    static String[] board;
    static int[][] dist;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }

        public Pair(){
            this(0, 0);
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new String[n];
        dist = new int[n][m];
        for(int i =0; i<n; i++){
            board[i] =br.readLine();
        }
       
        bfs();
    }

    static void bfs(){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0));
        dist[0][0] =1;
        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i =0; i<4; i++){
                int y = now.y+ dy[i];
                int x = now.x+ dx[i];
                if(y == n-1 && x == m-1){
                    System.out.println(dist[now.y][now.x]+1);
                    return;
                }
                if(y < 0 || n <= y || x<0 || m <=x || board[y].charAt(x) =='0' ||dist[y][x] != 0 ){
                    continue;
                }

                dist[y][x] = dist[now.y][now.x] +1;
                q.add(new Pair(y, x));
            }
        }
    }


   

}

