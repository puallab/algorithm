import java.util.*;
import java.io.*;

public class Main {
    
    static class Node{
        int num, weight;
        public Node(int n, int w){
            num = n;
            weight = w;
        }
    }

    static int N, M, start, dest;
    static ArrayList<Node>[] edges;
    static int[] dist;

    public static void main(String[] args) throws Exception {
       //input();
       int i = 10; 
       String s = Integer.toString(i);
       System.out.println(s);
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());
        M = Integer.parseInt(br.readLine());
        edges = new ArrayList[N+1];
        dist = new int[N+1];
        Arrays.fill(dist, 987654321);
        for(int i = 0; i<=N; i++) edges[i] = new ArrayList<>();
        for(int i =0; i<M; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken());
            int to = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());
            edges[from].add(new Node(to, w));
        }

        StringTokenizer st= new StringTokenizer(br.readLine());
        start = Integer.parseInt(st.nextToken());
        dest = Integer.parseInt(st.nextToken());
    }

    static int dijkstra(int start){
        dist[start] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>( (o1,o2) -> o1.weight - o2.weight );
        pq.add(new Node(start, 0));

        while(!pq.isEmpty()){
            Node cur = pq.poll();
            if(cur.weight > dist[cur.num]) continue;
            for(int i =0; i<edges[cur.num].size(); i++){
                Node next = edges[cur.num].get(i);
                if(dist[next.num] > cur.weight + next.weight){
                    dist[next.num] = cur.weight + next.weight;
                    pq.add(new Node(next.num, dist[next.num]));
                }
            }
        }
        return dist[dest];
    }


}


