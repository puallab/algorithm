import java.util.*;
import java.io.*;
class Solution {
    static int answer = 0;
    static boolean[] vis;
    public int solution(int k, int[][] dungeons) {
        vis = new boolean[dungeons.length];
        dfs(0, k, dungeons);
        return answer;
    }
    
    static void dfs(int picked, int k, int[][] dun){
        
        for(int i =0; i<dun.length; i++){
            if(vis[i]) continue;
            if(dun[i][0] > k) continue;
            vis[i] = true;
            dfs(picked+1, k-dun[i][1] ,dun);
            vis[i] = false;
        }
        answer = Math.max(answer,picked);
    }

    //다른 분 코드 vis 배열 쓰지않고 처리하는 방법.
    static void dfs(int picked ,int k, int[][] dg){
        for(int[] d : dg){
            int a = d[0], b = d[1];
            if(a <= k){
                d[0] = 9999;
                dfs(picked+1,k-b, dg);
                d[0] = a;
            }
        }
        answer = Math.max(answer, pikced);
    }
    
}