import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj15649 {
    static int n, m;
    static boolean[] vis;
    public static void main(String[] args) throws Exception{
        input();
        dfs(0, "");
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        vis = new boolean[n+1];
    }

    static void dfs(int picked, String s){
        if(picked == m){
            System.out.println(s.trim());
            return;
        }

        for(int i =1; i <= n; i++){
            if(vis[i]) continue;
            vis[i] = true;
            dfs(picked+1, s + " " + i);
            vis[i] = false;
        }
    }
}
