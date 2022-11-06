import java.util.*;
import java.io.*;

public class Main{
    static int t, n;
    static boolean flag;
    static int[] vis;
    static int[] selected;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        t = Integer.parseInt(br.readLine());

        while(t -- > 0){
            n = Integer.parseInt(br.readLine());
            selected = new int[n+1];
            vis = new int[n+1];
            st = new StringTokenizer(br.readLine());
            flag = false;
            for(int i =1; i<=n; i++){
                selected[i] = Integer.parseInt(st.nextToken());
            }

            for(int i=1; i<=n; i++){
                if(vis[i] != 0) continue;
                flag = false;
                dfs(i, i+1);
            }

            int val = 0;
            for(int i =1; i<=n; i++){
                if(vis[i] != -1) val++;
            }
            sb.append(val + "\n");
        }
        
        System.out.println(sb.toString());

    }

    static void dfs(int from, int idx){
        if(flag) return;
        if(vis[from] == -1) {
            flag = true;
            return;
        }
        if(vis[from] !=0 && vis[from] != idx){
            flag= true;
            return;
        } 

        if(vis[from] == idx) vis[from] = -1;
        else if(vis[from] == 0) vis[from] = idx;

        dfs(selected[from], idx);

    }
}