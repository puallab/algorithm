import java.util.*;
import java.io.*;

public class Main {
    public static final int INF = Integer.MAX_VALUE;
    public static int n, m;
    public static int[][] graph = new int[101][101];
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());
        
        
        for(int i =0; i<m; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            graph[a][b] = 1;
            graph[b][a] = -1;
        }
    }

    public static void pro(){
        StringBuilder sb = new StringBuilder();
        for(int k =0; k<n; k++){
            for(int from = 0; from<n; from++){
                for(int to =0; to<n; to++){
                    //from - >to에 k가 있고 from > k 혹은 from <n 인경우.
                    if(graph[from][k] != 0 && graph[k][to] != 0){
                        graph[from][to] = graph[from][k];
                        graph[to][from] = graph[k][from];
                    } 
                }
            }
        }

        for(int i =0; i<n; i++){
            int cnt = 0;
            for(int j=0; j<n; j++){
                if(i == j) continue;
                if(graph[i][j] == 0) cnt++;
            }
            sb.append(cnt).append('\n');
        }
        System.out.println(sb);
    }

    
}
