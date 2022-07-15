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
        
        dfs(0,  new boolean[n]);
        
        System.out.println(ans);
    }

    static void dfs(int idx,  boolean[] check){
        if(idx == n){
            ans = Math.max(ans, getCnt(check));
            return;
        }
        boolean broken = false;
        if(check[idx]) dfs(idx+1, check);
        else{
            for(int i=0; i<n; i++){
                if(check[i] || idx == i) continue;
                d[idx] -= w[i];
                d[i] -= w[idx];
                if(d[idx] <= 0) check[idx] = true;
                if(d[i] <= 0) check[i] = true;
                broken = true;
                dfs(idx+1, check);
    
                if(d[idx] <= 0) check[idx] = false;
                if(d[i] <= 0) check[i] = false;
                d[idx] += w[i];
                d[i] += w[idx];
            }
            if(!broken) dfs(idx+1, check);
        }
        
    } 

    static int getCnt(boolean[] check){
        int val = 0;
        for(int i =0; i<n; i++){
            if(check[i]) val++;
        }
        return val;
    }

}