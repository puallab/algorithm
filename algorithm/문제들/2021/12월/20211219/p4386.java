import java.util.*;
import java.io.*;

public class Main{

    static int n;
    static int[] root;
    static List<Point> list = new ArrayList<>();  
    static class Point{
        double y, x;
        int idx;
        public Point(double yy, double xx, int d){
             y = yy;
             x = xx;
             idx = d;
        }
    }

    static class Edge{
        int from, to;
        double weight;
        public Edge(int a, int b, double w){
            from =a;
            to = b;
            weight = w;
        }
    }
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        root = new int[n];
        for(int i =0; i<n; i++) root[i]= i;
    
        for(int i =0; i<n; i++){
            StringTokenizer st= new StringTokenizer(br.readLine());
            double y = Double.parseDouble(st.nextToken());
            double x = Double.parseDouble(st.nextToken());
            list.add(new Point(y,x,i));
        }

        PriorityQueue<Edge> pq = new PriorityQueue<>((o1,o2) -> {
            if(o1.weight <= o2.weight) return -1;
            else return 1;}
        );

        //graph만들기
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(i == j ) continue;
                double w = calc(list.get(i), list.get(j));
                pq.add(new Edge(i,j,w));
            }
        }

        double result =0;
        int cnt =0;
        while(!pq.isEmpty()){
            if(cnt == n-1) break;
            Edge poll = pq.poll();
            if(union(poll.from, poll.to)){
                cnt++;
                result += poll.weight;
            }
        }

        System.out.printf("%.2f",result);

    }
    static double calc(Point point, Point point2) {
        double dy = Math.abs(point.y - point2.y);
        double dx = Math.abs(point.x - point2.x);
        return Math.sqrt(dy*dy+dx*dx);
    }

    static int find(int n ){
        if(root[n] == n) return n;
        return root[n] = find(root[n]);
    }

    static boolean union(int a, int b){
        a = find(a);
        b = find(b);
        if(a == b )return false;
        if(a>b) root[b] = a;
        else root[a] = b;
        return true;
    }
}