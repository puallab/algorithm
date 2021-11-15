import java.util.*;
import java.io.*;

public class App {
    static int f, s, g;
    static int[] dx = new int[2];
    static Queue<Integer> q = new LinkedList<>();
    static int[] vis;
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        f = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());
        g = Integer.parseInt(st.nextToken());
        dx[0] = Integer.parseInt(st.nextToken());
        dx[1] = -Integer.parseInt(st.nextToken());
        vis = new int[f+1];
        vis[s] = 0; 
        q.add(s);
    }

    public static void pro(){
        if(s == g){
            System.out.println(0);
            return;
        }
        while(!q.isEmpty()){
            int x = q.poll();
            for(int i =0; i<2; i++){
                if(dx[i] == 0 ) continue;
                int cx = x + dx[i];
                if(cx <1 || cx>f || vis[cx] != 0) continue;
                q.add(cx);
                vis[cx] = vis[x]+1;
                if(cx == g){
                    System.out.println(vis[cx]);
                    return;
                }
            }
        }
        System.out.println("use the stairs");
    }
}
