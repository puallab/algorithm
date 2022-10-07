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

    static int n, sum;
    static int[] py = {0, 0, 1, -1};
    static int[] px = {1, -1, 0, 0};
    static int[][] board;
    static int[][] plate;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for(int i =0; i<n ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                sum += board[i][j];
            }
        }

        System.out.println(getMinimum());
    }

    static int getMinimum(){
        int val = Integer.MAX_VALUE;
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                int k = getGap(i,j);
                val = Math.min(val, k);
            }
            
        }
        return val;
    }

    static int getGap(int y, int x){
        
        int val = Integer.MAX_VALUE;
        for(int d1 = 1; d1<n; d1++){

            for(int d2 = 1; d2<n; d2++){
                int topY = y-d1;
                int topX = x+d1;
                
                int botY = y+d2;
                int botX = x+d2;
                
                int rightY = y-d1+d2;
                int rightX = x+d1+d2;
                

                if(!isValid(topY, topX) || !isValid(botY, botX) || !isValid(rightY, rightX)) continue;

                int k = drawCalc(y, x, d1, d2);
                //showBoard();
                val = Math.min(val, k);
            }
        }
        return val;
    }

    static boolean isValid(int y, int x ,int sy, int sx, int ly, int lx){
        return (y>=sy && x>=sx && y<ly && x<lx);
    }

    static boolean isValid(int y, int x ){
        return (y>=0 && x>=0 && y<n && x<n);
    }

    static int drawCalc(int y, int x, int d1, int d2){
        int maxVal =0;
        int minVal = Integer.MAX_VALUE;
        int[] areas = new int[6];
        plate = new int[n][n];

        //0. 경계선 그리기
        for(int i=0; i<= d1; i++){
            int uy = y -i;
            int ux = x +i;

            int uyy = y +d2 -i;
            int uxx = x +d2 +i; 

            plate[uy][ux] = plate[uyy][uxx] = 5;
        }

        for(int i =1; i<d2; i++){
            int dy = y+i;
            int dx = x+i;

            int dyy = y-d1+i;
            int dxx = x+d1+i;

            plate[dy][dx] = plate[dyy][dxx] = 5;
        }
        
        //1 번 구역 탐색
        areas[1] = bfs(0, 0, 1, new Pair(0, 0), new Pair(y, x+d1+1));
        areas[2] = bfs(0, n-1, 2, new Pair(0, x+d1+1), new Pair(y-d1+d2+1, n));
        areas[3] = bfs(n-1, 0, 3, new Pair(y,0), new Pair(n, x+d2));
        areas[4] = bfs(n-1, n-1, 4, new Pair(y-d1+d2+1, x+d2), new Pair(n, n));

        
        //5번 구역 더하기
        areas[5] = sum;
        for(int i =1; i<5; i++){
            areas[5] -= areas[i];
            maxVal = Math.max(maxVal, areas[i]);
            minVal = Math.min(minVal, areas[i]);
        }
        
        maxVal = Math.max(maxVal, areas[5]);
        minVal = Math.min(minVal, areas[5]);

        return maxVal-minVal;
    }

    static int bfs(int y, int x ,int idx, Pair start, Pair end){
        int val = board[y][x];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y,x));
        plate[y][x] = idx;
        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i=0; i<4; i++){
                int yy = now.y + py[i];
                int xx = now.x +px[i];
                if(!isValid(yy, xx, start.y, start.x, end.y, end.x) || plate[yy][xx] > 0) continue;
                val += board[yy][xx];
                plate[yy][xx] = idx;
                q.add(new Pair(yy, xx));
            }
        }

        return val;
    }

    static void showBoard(){
        System.out.println("\n");
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                System.out.print(plate[i][j] +" ");
            }
            System.out.println();
        }
    }

    
}