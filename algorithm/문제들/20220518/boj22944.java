import java.util.*;
import java.io.*;

public class boj22944 {
    static int n, h, d;
    static int startY, startX, ans;
    static String[] board;
    static List<Pair> list = new ArrayList<>();
    static boolean[] vis;

    static class Pair{
        int y,x;
        public Pair(int yy, int xx){
            y = yy;
            x = xx;
        }
    }

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());  
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());

        board = new String[n];
        ans = n*n+1;
        
        for(int i =0; i<n; i++){
            board[i] = br.readLine();

            for(int j =0; j<n; j++){
                if(board[i].charAt(j) == 'S'){
                    startY = i;
                    startX = j;
                }
                else if(board[i].charAt(j) != '.'){
                    list.add(new Pair(i, j));
                }
            }
        }
        vis = new boolean[list.size()];
    }

    static void pro() {
        dfs(startY, startX, h, 0, 0);
        System.out.println(ans == n*n+1 ? -1 : ans);
    }

    static void dfs(int y, int x, int hp, int um, int sum){
        
        if(board[y].charAt(x) =='E'){
            ans = Math.min(sum, ans);
            return;
        }
        
        //if(hp == 0) return;

        for(int i =0; i<list.size(); i++){

            if(vis[i]) continue;
            Pair now = list.get(i);
            int len = getLen(now, y, x, um+hp);
            if(len == -1) continue;

            // 무조건 U로 간다 생각, E로 간다 해도 다음 턴에 바로 return 됨.
            vis[i] = true;
            if(len-1 > um) dfs(now.y, now.x, hp+um-len+1, d-1, sum+len);
            else dfs(now.y, now.x, hp, d-1, sum+len);
            vis[i] = false;
        }

    }

    static int getLen(Pair p, int y, int x, int sum){
        int len = Math.abs(p.y - y) + Math.abs(p.x - x);
        return len > sum ? -1 : len;
    }

}
