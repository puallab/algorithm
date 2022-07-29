import java.io.*;
import java.util.*;

import javax.management.Descriptor;


public class Main{
    static int[][] board = new int[10][10];
    static boolean[][] vis = new boolean[10][10];
    static int ans = 101;
    static int[] cnt = new int[]{5, 5, 5, 5, 5};
    static boolean flag = false;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;        

        for(int i =0; i<10; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<10; j++){
                int k = Integer.parseInt(st.nextToken());
                board[i][j] = k;
                
            }
        }

        dfs(0, 0);

        if(ans == 101) ans = -1;
        System.out.println(ans);
    }

    static void dfs(int depth, int idx){
        
        if(idx == 100){
            ans = Math.min(ans, depth);
            return;
        }

        if (ans < depth) {
            return;
        }

        int y = idx/10;
        int x = idx%10;
        if(board[y][x] == 0 || vis[y][x]) {
            dfs(depth, idx+1);
        }
        else{
            for(int i =0; i < 5; i++){
              
                if(cnt[i] > 0 && isValid(i, idx)){
                    cover(i, idx, true);
                    cnt[i] -= 1;

                    dfs(depth+1, idx+1);

                    cover(i, idx, false);
                    cnt[i] += 1;
                }
            }
        }
        
        
    }

    static boolean isValid(int k, int idx){
        int y = idx/10; 
        int x = idx%10;
        if( y+k >= 10 || x+k >= 10) return false;

        for(int i =y; i<= y+k; i++){
            for(int j =x; j <= x+k; j++){
                if(board[i][j] == 0 ) return false;
            }
        }
        

        return true;
        
    }

    static void cover(int k, int idx, boolean val){
        int y = idx/10; 
        int x = idx%10;

        for(int i =y; i<= y+k; i++){
            for(int j =x; j <= x+k; j++){
                vis[i][j] = val;
            }
        }

    }

}

