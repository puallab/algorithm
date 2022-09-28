import java.io.*;
import java.util.*;

public class Main{
    static int n, l, r;
    static int[][] board;
    static boolean[][] vis;
    static int[] dy= {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    
    
    static class Pair{
        private int y, x;

        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }

        public Pair(){
            this(-1, -1);
        }
    }
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        r = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        int ans = doSimulate();
        System.out.println(ans);
    }

    static int doSimulate(){
        int ret= -1;
        boolean flag = true;

        while(flag){
            flag = false;
            ret++;
            vis = new boolean[n][n];
            for(int i =0; i<n; i++){
                for(int j =0; j<n; j++){
                    if(!vis[i][j] && isCanMove(i, j)){
                        flag= true;
                        vis[i][j] = true;
                        bfs(i, j);
                    }
                }
            }

            
        }

        return ret;
    }


    static boolean isCanMove(int y, int x){
        for(int i =0; i<4; i++){
            int yy = dy[i] + y;
            int xx=  dx[i] + x;
            if(isValid(y, x, yy, xx)) return true;
        }
        return false;
    }

    static boolean isValid(int y, int x, int yy, int xx){
        if(yy<0 || yy>=n || xx<0 || xx>=n || vis[yy][xx]) return false;

        int gap = Math.abs(board[y][x]-board[yy][xx]);
        if(gap <l || gap>r) return false;

        return true;
    }
    
    static void bfs(int y, int x){
        Pair start = new Pair(y, x);
        Queue<Pair> q = new LinkedList<>();
        q.add(start);
        
        List<Pair> list = new ArrayList<>();
        list.add(start);

        vis[y][x] = true;

        int pCnt =board[y][x], cCnt=1;

        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i=0; i<4; i++){
                int yy = dy[i] + now.y;
                int xx=  dx[i] + now.x;
                if(!isValid(now.y, now.x, yy, xx)) continue;
                Pair point = new Pair(yy, xx);
                q.add(point);
                list.add(point);
                vis[yy][xx] = true;
                cCnt++;
                pCnt += board[yy][xx];
            }
        }
        
        int val = pCnt/cCnt;
        for(Pair node : list){
            board[node.y][node.x] = val;
        }

    }


}

