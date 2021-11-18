import java.util.*;
import java.io.*;
/**
 *  플루이드 와샬 기초.
 */
public class Main {
    public static final int INF = (int)1e9;
    public static int n;
    public static int[][] graph = new int[101][101];
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());


        for(int i =0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j=0; j<n; j++){
                int k= Integer.parseInt(st.nextToken());
                if(k == 1) graph[i][j] =  1;
                else graph[i][j] = INF;
            }
        }
    }

    public static void pro(){
       for(int k =0; k< n; k++){
           for(int a= 0; a < n; a++){
               for(int b = 0; b<n; b++){
                   graph[a][b] = Math.min(graph[a][b], graph[a][k] + graph[k][b]);
               }
           }
       }

       StringBuilder sb = new StringBuilder();

       for(int i =0; i<n; i++){
           for(int j=0; j<n; j++){
               if(graph[i][j] == INF){
                   sb.append(0).append(' ');
               }else{
                   sb.append(1).append(' ');
               }
           }
           sb.append('\n');
        }
        System.out.println(sb);
    }

    
}
