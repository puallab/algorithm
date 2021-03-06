import java.util.*;
import java.io.*;
public class boj22944 {
    static int n, h, d;
    static int startY = -1, startX = -1, ans = -1;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static String[] board;
    static int[][] map; // map[y][x][0] : hp+depense, map[y][x][1] : cnt

    static class Pair{
        int y, x, hp, um, cnt;
        public Pair(int yy, int xx, int hpp, int umm, int cc){
            y =yy;
            x =xx;
            hp = hpp;
            um = umm;
            cnt = cc;
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
        map = new int[n][n];
        //ans = -1;

        for(int i =0; i<n; i++){
            board[i] = br.readLine();

            if(startX != -1) continue;

            for(int j =0; j<n; j++){
                if(board[i].charAt(j) == 'S'){
                    startY = i;
                    startX = j;
                }
            }
        }
    }

    static void pro() {
        dfs(startY, startX, h, 0, 0);
    }

    static void dfs(int y, int x, int hp, int um, int cnt){
        if(board[y].charAt(x) == 'E'){
            ans = Math.min(cnt, ans);
            //show(list);
            return;
        }

        if(cnt > n*n) return;

        if(board[y].charAt(x) == 'U'){
            um = d;
        }
        
        if(board[y].charAt(x) !='S'){
            if(um > 0) um--;
            else hp--;
        }
        

        if(hp == 0) return;
        

        for(int i =0; i<4; i++){

            int yy = dy[i]+y;
            int xx = dx[i]+x;

            if(yy <0 || xx<0 || yy>=n || xx>= n) continue;
            if(map[yy][xx] >= hp + um) continue;


            map[yy][xx] = hp + um;
            //list.add(new Pair(yy, xx));
            dfs(yy, xx, hp, um, cnt+1);
            //list.remove(list.size()-1);
        }

    }

    
    static void show(List<Pair> list){
        System.out.println("####");
        for(Pair p : list){
            System.out.println(p.y + ", " + p.x);
        }
        System.out.println();
    }
}
