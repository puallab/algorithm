import java.util.*;
import java.io.*;

public class Main{
    static int n,m, ans = Integer.MAX_VALUE;
    static int[][] board;
    static List<Integer> houses = new ArrayList<>();
    static List<Integer> chicks = new ArrayList<>();
    static boolean[] vis;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[n][n];

        for(int i =0; i<n; i++){
            st=  new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1){
                    houses.add(i*n +j);
                }else if(board[i][j] == 2){
                    chicks.add(i*n+ j);
                }
            }
        }
        vis = new boolean[chicks.size()];
        dfs(0, 0);
        System.out.println(ans);
    }

    static void dfs(int idx, int picked){
        if(picked == m){
            ans = Math.min(getchickenLen(), ans);
            return;
        }

        for(int i = idx ; i< chicks.size(); i++){ 
            vis[i] = true;
            dfs(i+1, picked+1);
            vis[i] = false;
        }
    }

    static int getchickenLen(){
        int val = 0;
        for(int i : houses){
            int hy = i/n;
            int hx = i%n;
            int nval = n*n+1;
            for(int j =0; j<chicks.size(); j++){
                if(vis[j] == false) continue;
                int sy = chicks.get(j)/n;
                int sx = chicks.get(j)%n;

                nval = Math.min( calc(hy, hx, sy, sx), nval );
            }
            val += nval;
        }
        return val;
    }

    static int calc(int y1, int x1, int y2, int x2){
        return Math.abs(y1-y2) + Math.abs(x1-x2);
    }
}