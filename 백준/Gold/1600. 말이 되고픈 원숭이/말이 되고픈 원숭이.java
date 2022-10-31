import java.util.*;
import java.io.*;

public class Main{

    static class Tuple{
        int y, x, cnt;
        public Tuple(int yy, int xx ,int cc){
            y =yy;
            x =xx;
            cnt = cc;
        }
    }

    static int k, w, h;
    static int[] dmy = {0, 0, 1, -1};
    static int[] dmx = {1, -1, 0, 0};
    static int[] dhy = {-1, -2, -2, -1, 1, 2, 2, 1};
    static int[] dhx = {-2, -1, 1, 2, 2, 1, -1, -2};
    static int[][] board;
    static int[][][] vis;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        k = Integer.parseInt(br.readLine());
        
        st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new int[h][w];
        for(int i= 0; i<h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<w; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        System.out.println(bfs());
    }
    
    static boolean isValid(int y, int x){
        return (y>=0 && x >=0 && y <h && x <w);
    }

    static boolean isArrived(int y, int x){
        return (y == h-1 && x == w-1);
    }

    static int bfs(){
        if(isArrived(0, 0)) return 0;
        
        vis = new int[k+1][h][w];
        vis[0][0][0] = 0;
        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(0, 0, 0));

        while(!q.isEmpty()){
            Tuple now = q.poll();

            for(int i =0; i<4; i++){
                int y = now.y + dmy[i];
                int x = now.x + dmx[i];
                if(!isValid(y, x) || vis[now.cnt][y][x] != 0 || board[y][x] != 0) continue;
                q.add(new Tuple(y, x, now.cnt));
                vis[now.cnt][y][x] = vis[now.cnt][now.y][now.x] + 1;
                if(isArrived(y, x)){
                    return vis[now.cnt][y][x];
                }
            }

            if(now.cnt < k){
                // 말 이동 가능
                for(int i =0; i< dhx.length; i++){
                    int y = now.y + dhy[i];
                    int x = now.x + dhx[i];
                    if(!isValid(y, x) || vis[now.cnt+1][y][x] != 0 || board[y][x] !=0) continue;
                    q.add(new Tuple(y, x, now.cnt+1));
                    vis[now.cnt+1][y][x] = vis[now.cnt][now.y][now.x] + 1;
                    if(isArrived(y, x)){
                        return vis[now.cnt+1][y][x];
                    }
                }

            }
        }
        return -1;
    }
}