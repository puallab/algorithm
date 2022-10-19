import java.util.*;
import java.io.*;

public class Main{

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }
    }

    static class Tuple{
        int y, x, d;
        public Tuple(int yy, int xx ,int dd){
            y =yy;
            x =xx;
            d =dd;
        }
    }

    static class Visitor{
        int y, x;
        boolean isStart = false, isEnd = false;

        public Visitor(int yy, int xx){
            y = yy;
            x = xx;
        }
    }

    static int n, m;
    static int[] dy = {-1, 0, 0, 1};
    static int[] dx = {0, -1, 1, 0};
    static int[][] board;
    static Visitor[] visitors;
    static Pair[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int [n][n];
        for(int i=0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        visitors = new Visitor[m];
        pos = new Pair[m];

        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int y= Integer.parseInt(st.nextToken()) -1;
            int x= Integer.parseInt(st.nextToken()) -1;
            visitors[i] = new Visitor(-1, -1);
            pos[i] = new Pair(y, x);
        }

        System.out.println(simul());

    }

    static int simul(){
        int min = 0;
        while(true){
            if(isComplete()){
                break;
            }

            moveAllPeople();

            if(min < m){
                arrive(min);
            }
            min++;
        }


        return min;
    }

    static boolean isComplete(){
        for(Visitor visitor : visitors){
            if(visitor.isEnd == false){
                return false;
            }
        }
        return true;
    }

    static void moveAllPeople(){
        for(int i =0; i<visitors.length; i++){
            Visitor visitor = visitors[i];
            if(visitor.isStart){
                int dir = getDir(i);
                // 방향 얻어와서 움직임.
                visitor.y += dy[dir];
                visitor.x += dx[dir];
            }
        }

        
        for(int i =0; i<visitors.length; i++){
            Visitor visitor = visitors[i];
            if(visitor.isStart){
                if(isArrived(visitor.y, visitor.x, pos[i].y,  pos[i].x)){
                    board[visitor.y][visitor.x] = -2;
                    visitor.isEnd = true;
                    visitor.isStart = false;
                }
            }
        }
    }

    static int getDir(int idx){
        Visitor visitor = visitors[idx];
        boolean[][] vis = new boolean[n][n];
        vis[visitor.y][visitor.x] = true;

        Queue<Tuple> q = new LinkedList<>();
        q.add(new Tuple(visitor.y, visitor.x, -1));

        while(!q.isEmpty()){
            Tuple now = q.poll();

            if(isArrived(now.y, now.x, pos[idx].y, pos[idx].x)){
                return now.d;
            }

            for(int i=0; i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];
                // 격자 밖 벗어났거나, 텐트, 차 있으면 false
                if(!isValid(y, x) || board[y][x] < 0 || vis[y][x]) continue;
                if(now.d == -1) {
                    q.add(new Tuple(y, x, i));
                }
                else q.add(new Tuple(y, x, now.d));
                vis[y][x] = true;

                

            }
            
        }

        return -1;

    }

    static void arrive(int idx){
        boolean [][] vis = new boolean[n][n];
        int y = pos[idx].y;
        int x = pos[idx].x;
        vis[y][x] = true;

        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        int minY = n*n+1;
        int minX = n*n+1;
        boolean flag = false;
        while(!q.isEmpty()){
            int qsize = q.size();
            if(flag){
                //주차장 위치 찾음
                // board -1, start값 올려줌.
                board[minY][minX] = -1;
                visitors[idx].y = minY;
                visitors[idx].x = minX;
                visitors[idx].isStart = true;
                
                return; 
            }
            while (qsize-- > 0) {
                Pair now = q.poll();

                if(board[now.y][now.x] == 1){
                    if(now.y < minY){
                        minY = now.y;
                        minX = now.x;
                        
                    }
                    else if(now.y == minY && now.x < minX){
                        minY = now.y;
                        minX = now.x;
                        
                    }
                    flag= true;
                }

                for (int i = 0; i < 4; i++) {
                    y = now.y + dy[i];
                    x = now.x + dx[i];
                    if(!isValid(y, x) || vis[y][x] || board[y][x] < 0) continue;
                    vis[y][x] =true;
                    q.add(new Pair(y, x));
                }
            }
            
        }
        
    }

    static boolean isArrived(int y, int x, int yy, int xx){
        return (y == yy && x ==xx);
    }

    static boolean isValid(int y, int x){
        return (y>=0 && x>=0 && y<n && x<n);
    }
}