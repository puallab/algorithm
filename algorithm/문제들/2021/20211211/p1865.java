import java.util.*;
import java.io.*;

public class Main {
    
    static class Edge{
        int to, weight;
        public Edge(int t, int w){
            to =t;
            weight = w;
        }
    }

    static int tc, N, M, W;
    static List<Edge>[] edges;

    public static void main(String[] args) throws Exception {
       
        input();
        
    }

    static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       tc = Integer.parseInt(br.readLine());
       StringBuilder sb = new StringBuilder();
       
       while(tc-- > 0){
           StringTokenizer st = new StringTokenizer(br.readLine());
           N = Integer.parseInt(st.nextToken());
           M = Integer.parseInt(st.nextToken());
           W = Integer.parseInt(st.nextToken());

           edges = new ArrayList[N+1];
           for(int i =1; i<= N; i++) edges[i] = new ArrayList<>();

           for(int i =0; i<M; i++){
                st= new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[from].add(new Edge(to, w));
                edges[to].add(new Edge(from, w));
            }

            for(int i =0; i<W; i++){
                st= new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int w = Integer.parseInt(st.nextToken());
                edges[from].add(new Edge(to,-w));
            }

            if(!pro()) sb.append("YES\n");
            else sb.append("NO\n");
           
       } 
       System.out.println(sb);
    }


    static boolean pro(){
        int[] dist = new int[N+1];
        for(int i =0; i<N; i++){
            for(int j =1; j<=N; j++){
                for(Edge edge : edges[j]){
                    if(dist[j] + edge.weight < dist[edge.to]){
                        dist[edge.to] = dist[j] + edge.weight;
                        if(i == N-1) return false;
                    }
                }
            }
        }
        return true;
    }
 
}
