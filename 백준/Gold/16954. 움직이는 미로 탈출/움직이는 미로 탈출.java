import java.util.*;
import java.io.*;

public class Main{

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y = yy;
            x = xx;
        }
    }

    static int[] dy = {0, 0, -1, -1, -1, 0, 1, 1, 1};
    static int[] dx = {0, -1, -1, 0, 1, 1, 1, 0, -1};

    static String[] board = new String[8]; 
    static boolean[][] vis = new boolean[8][8];
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i =0; i<8; i++){
            board[i] = br.readLine();
        }

        System.out.println(bfs());
        
    }

    static int bfs(){

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(7,0));
        vis[7][0] = true;

        while(!q.isEmpty()){

            Pair now = q.poll();
            for(int i =0; i<dy.length; i++){

                int y = dy[i] + now.y;
                int x = dx[i] + now.x;

                // 한칸이동
                if(!isValid(y, x) || board[y].charAt(x) =='#') continue; 

                
                //벽 내려오기전에 끝인지?
                if(y == 0){
                    return 1;
                }

                //벽 내려옴 ( 내가 한 칸 위로 올라가는 것과 동일)
                y -= 1;

                if(!isValid(y, x) || board[y].charAt(x) =='#' || vis[y][x]) continue;

                if(y == 0){
                    return 1;
                }

                q.add(new Pair(y, x));
                vis[y][x] = true;
            }

        }

        return 0;
    }

    static boolean isValid(int y, int x){
        return (y >=0 && x>= 0 && y<8 && x < 8);
    }
}