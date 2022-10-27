import java.io.*;
import java.util.*;

public class Main{
    static int n, m, val;
    static ArrayList<Integer>[] board;
    static boolean[] vis, toSet;
    static List<Integer> ans = new ArrayList<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new ArrayList[n+1];

        for(int i=0; i<n+1; i++){
            board[i] = new ArrayList<>();
        }

        toSet = new boolean[n+1];

        for(int i=0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int to= Integer.parseInt(st.nextToken());
            int from= Integer.parseInt(st.nextToken());
            board[from].add(to);
        }
        
        StringBuilder sb = new StringBuilder();
        for(int i =1; i<=n; i++){
            int ret = bfs(i);
            if(val < ret){
                val = ret;
                sb = new StringBuilder();
                sb.append(i);
            }else if(val == ret){
                sb.append(" "+ i);
            }

        }

        System.out.println(sb.toString());

    }

    static int bfs(int from){
        int ret = 0;
        Queue<Integer> q=  new LinkedList<>();
        q.add(from);
        vis = new boolean[n+1];
        vis[from] = true;

        while(!q.isEmpty()){
            int now = q.poll();
            for(int to : board[now]){
                if(!vis[to]){
                    ret++;
                    vis[to] = true;
                    q.add(to);
                }
            }
        }
        
        return ret;
    }
}

