import java.io.*;
import java.util.*;

public class Main{
    static int n, m, v;
    static boolean[][] board;
    static boolean[] vis;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        v = Integer.parseInt(st.nextToken());
        
        board = new boolean[n+1][n+1];
        vis = new boolean[n+1];
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = board[b][a] = true;
        }

        dfs(v);
        sb.append("\n");
        bfs();
        System.out.println(sb.toString());
    }

    static void dfs(int from){
        vis[from] = true;
        sb.append(from + " ");

        for(int i =1; i<n+1; i++){
            if(board[from][i] && !vis[i]){
                dfs(i);
            }
        }
    }

    static void bfs(){
        vis = new boolean[n+1];
        Queue<Integer> q = new LinkedList<>();
        q.add(v);
        vis[v] = true;
        while(!q.isEmpty()){
            int now = q.poll();
            sb.append(now + " ");
            for(int i =1; i<n+1; i++){
                if(board[now][i] && !vis[i]){
                    vis[i] = true;
                    q.add(i);
                }
            }
        }
    }

    
   


}

