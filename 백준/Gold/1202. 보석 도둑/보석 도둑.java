import java.util.*;
import java.io.*;

public class Main{
    static class Pair implements Comparable<Pair>{
        int weight, value;
        public Pair(int w, int v){
            weight = w;
            value = v;
        }

        @Override
        public int compareTo(Pair o){
            if(this.weight == o.weight){
                return this.value-o.value;
            }
            else return this.weight-o.weight;
        }
    }

    static int n, k, idx, startIdx;
    static Pair[] golds;
    static int[] values;
    static int[] bags;
    static boolean[] vis;
	public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        golds = new Pair[n];
        vis = new boolean[n];
        bags = new int[k];

        for(idx =1; (1<<idx) <= n; idx++);
        values = new int[(1 << idx+1)+1];
        startIdx = 1 << idx;
        
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            int w = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            golds[i] = new Pair(w, v);
        }

        for(int i =0; i<k; i++){
            bags[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(golds);
        Arrays.sort(bags); 
        long ans = 0;

        PriorityQueue<Pair> pq = new PriorityQueue<>(((o1, o2) -> o2.value-o1.value));
        int idx = 0;
        for(int i =0; i<k; i++){
            while(idx < golds.length && golds[idx].weight <= bags[i]){
                pq.add(golds[idx++]);
            }
            if(!pq.isEmpty()) ans += pq.poll().value;
        }
        System.out.println(ans);

    }
}