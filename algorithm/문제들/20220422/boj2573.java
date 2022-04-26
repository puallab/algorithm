import java.io.*;
import java.util.*;

public class Main {

	static int n,m;
    static int[][] board;
    static boolean[][] vis;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }
    }

	public static void main(String[] args) throws Exception {

        input();
        pro();
	}

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringtTokenizer st = new StringtTokenizer();
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][m];
        vis = new int[n][m];

        for(int i =0; i<n; i++){
            st = new StringtTokenizer(br.readLine());
            for(int j =0; j<m; j+){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
    }

    static void pro(){

        // 1. 주위 핡기
        bfs(0,0);
        //2. 2개인지 판별.

        for(int i=0; i<n; i++){
            for(int j =0; j<m; j++){
                

                // 2. 2개인지 판별.
                if(board[i][j] == 0 && !vis[i][j] ){

                    // 들어가  
                }
            }
        }
    }

    static void bfs(int y, int x){
        Queue<Pair> q = new LinkedList<>():
        q.add(new Pair(y,x));
        vis[y][x] = true;

        while(!q.IsEmpty()){
            Pair now = q.poll();
            for(int i =0; i<4; i++){
                int yy = now.y + dy[i];
                int xx = now.x + dx[i];
                if( !isValid(yy,xx) || vis[yy][xx] ) continue;

                


            }

        }
    }

    static boolean isValid(int y, int x){
        return (y >=0 && x>=0 && y < n && x < m);
    }
}