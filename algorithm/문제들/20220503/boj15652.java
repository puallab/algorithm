import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.BufferUnderflowException;
import java.util.StringTokenizer;

public class boj15652 {
    static int n,m;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
    }

    static void pro(){
        dfs(1, 0, "");
        System.out.println(sb.toString());
    }

    static void dfs(int idx, int picked, String s){
        if(picked == m){
            sb.append(s.trim()+"\n");
            return;
        }

        for(int i = idx; i<=n; i++){
            dfs(i, picked+1, s + " " + i);
        }
    }


}
