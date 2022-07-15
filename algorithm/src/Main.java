import java.io.*;
import java.util.*;

import javax.swing.border.Border;

public class Main{
    static int n, ans;
    static int[] w, d;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        w = new int[n];
        d = new int[n];

        StringTokenizer st;
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            d[i] = Integer.parseInt(st.nextToken());
            w[i] = Integer.parseInt(st.nextToken());
        }
        
        dfs(0);
        
        System.out.println(ans);
    }

    static void dfs(int idx){
        if(idx == n){
            ans = Math.max(ans, getCnt());
            return;
        }
        boolean broken = false;
        if(d[idx] <= 0) dfs(idx+1);
        else{
            for(int i=0; i<n; i++){
                if(d[idx] <= 0 || idx == i) continue;
                d[idx] -= w[i];
                d[i] -= w[idx];
                
                broken = true;
                dfs(idx+1);
               
                d[idx] += w[i];
                d[i] += w[idx];
            }
            if(!broken) dfs(idx+1);
        }
        
    } 

    static int getCnt(){
        int val = 0;
        for(int i =0; i<n; i++){
            if(d[i] <= 0) val++;
        }
        return val;
    }

}