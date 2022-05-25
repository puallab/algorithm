import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj15650 {
    static int n,m;
    public static void main(String[] args) throws Exception{
        input();
        dfs(1, 0, new StringBuilder());
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }

    static void dfs(int idx, int picked, StringBuilder s){
        if(picked == m){
            System.out.println(s.toString().trim());
            return;
        }

        for(int i =idx; i<=n; i++){
            dfs(i+1, picked+1, s.append(" " + i));
            s.delete(s.length()-2, s.length());
        }
    }

}
