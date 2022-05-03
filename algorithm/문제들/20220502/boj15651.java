import java.util.*;
import java.io.*;

public class boj15651 {
    static int n,m;
    static List<String> list = new ArrayList<>();
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception {
        input();
        dfs(0, "");
        System.out.println(sb);
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }

    static void dfs(int picked, String s){
        if(picked == m){
            sb.append(s.trim() + "\n");
            return;
        } 

        for(int i = 1; i<=n; i++){
            dfs(picked+1, s+" " + i);
        }
    }
}
