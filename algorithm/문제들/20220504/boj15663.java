import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class boj15663 {
    static int n,m;
    static int[] arr;
    static boolean[] vis;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n =Integer.parseInt(st.nextToken());
        m= Integer.parseInt(st.nextToken());
        arr = new int[n];
        vis = new boolean[n];

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        Arrays.sort(arr);
    }

    static void pro(){
        dfs(0, "");
        System.out.println(sb.toString());
    }

    static void dfs(int picked, String s){
        if(picked == m){
            sb.append(s.trim() + "\n");
            return;
        }

        int prev = -1;
        for(int i =0; i<n; i++){
            if(vis[i]) continue;
            if(arr[i] == prev) continue;
            prev = arr[i];
            vis[i] = true;
            dfs(picked+1, s+" " + arr[i]);
            vis[i] = false;
        }
    }
}
