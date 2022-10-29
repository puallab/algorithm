import java.io.*;
import java.util.*;

public class Main{
    static class Pair{
        int dest, val;
        public Pair(int d, int v){
            dest = d;
            val = v;
        }
    }
    static int n;
    static ArrayList<Pair>[] list;
    static boolean[] vis;
    static int ans = 0, idx;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());

        list = new ArrayList[n+1];
        for(int i =0; i<=n; i++){
            list[i] = new ArrayList<>();
        }
        vis = new boolean[n+1];
        for(int i =0; i<n-1; i++){
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int val = Integer.parseInt(st.nextToken());
            list[from].add(new Pair(to, val));
            list[to].add(new Pair(from, val));
        }

        vis[0] = true;
        dfs(0, 1);
       
        ans = 0;
        vis = new boolean[n+1];
        vis[idx] = true;
        dfs(0, idx);

    
        System.out.println(ans);

        
    }

    static void dfs(int sum, int to){

        for(int i =0; i<list[to].size(); i++){
            if(vis[list[to].get(i).dest]) continue;
            vis[list[to].get(i).dest] = true;
            dfs(sum + list[to].get(i).val, list[to].get(i).dest);
        }
        
        if(ans < sum){
                idx = to;
                ans = sum;
        }
    }
}