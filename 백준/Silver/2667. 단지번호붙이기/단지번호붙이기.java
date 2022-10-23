import java.io.*;
import java.util.*;

import javax.swing.colorchooser.ColorSelectionModel;

public class Main{
    static int n, m, ans;
    static String[] board;
    static boolean[][] vis;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static List<Integer> list = new ArrayList<>();

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
        n = Integer.parseInt(br.readLine());
        board = new String[n];
        vis = new boolean[n][n];
        for(int i =0; i<n ;i++){
            board[i]  = br.readLine();
        }
        
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(vis[i][j] || board[i].charAt(j) == '0') continue;
                ans++;
                list.add(bfs(i,j));
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        sb.append(ans +"\n");
        for(int k : list){
            sb.append(k + "\n");
        }

        System.out.println(sb.toString());
    }

    static int bfs(int y, int x){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        vis[y][x] = true;
        int ret = 1;

        while(!q.isEmpty()){
            Pair k = q.poll();
            for(int i =0; i<4; i++){
                int yy = dy[i] + k.y;
                int xx = dx[i] + k.x;
                if(isValid(yy,xx)){
                    q.add(new Pair(yy, xx));
                    vis[yy][xx] = true;
                    ret++;
                }
            }
        }
        return ret;
    }

    static boolean isValid(int y, int x){
        return ( y>=0 && y <n && x>= 0 && x<n && vis[y][x] == false && board[y].charAt(x) =='1') ;
    }
}

