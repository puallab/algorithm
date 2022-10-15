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
    static int n, m, k, c, ans = 0;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[] ddy = {-1, -1, 1, 1};
    static int[] ddx = {-1, 1, -1, 1};
    static int[][] board, killWater;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        killWater = new int[n][n];
        for(int i =0; i<n ;i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                killWater[i][j] = -1;
            }
        }
        simul();
    }

    static void simul(){
        int year =0;
        while(year < m){
            
            // 성장
            growth(year);
            //번식
            spread(year);

            //제초제
            int val = killTree(year);
            if(val ==0 ) break;
            year += 1;
        }
        System.out.println(ans);

    }

    static void growth(int year){
        
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(board[i][j] > 0 ){
                    int cnt = 0;
                    for(int d=0; d<4; d++){
                        int y = dy[d] +i;
                        int x = dx[d] +j;
                        if(isValid(y, x) && board[y][x] > 0){
                            cnt += 1;
                        }
                    }
                    board[i][j] += cnt;
                }
            }
        }
    }

    static void spread(int year){

        int[][] temp = new int[n][n];

        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                
                if(board[i][j] > 0){
                    int cnt = 0;
                    for(int d=0; d<4; d++){
                        int y = dy[d] +i;
                        int x = dx[d] +j;
                        // 범위 안에 들어와있고, 아무것도 없으며, 제초제도 없어야함.
                        if(isValid(y, x) && board[y][x] == 0 && killWater[y][x] < year){
                            cnt += 1;
                        }
                    }

                    if(cnt == 0) continue;;

                    int val = board[i][j]/cnt;
                    for(int d =0; d<4; d++){
                        int y = dy[d] +i;
                        int x = dx[d] +j;
                        if(isValid(y, x) && board[y][x] == 0 && killWater[y][x] < year){
                            temp[y][x] += val;
                        }
                    }

                }
            }
        }

        addBoard(temp, board);

    }

    static int killTree(int year){
        int max_val = 0;
        Pair pos = null;
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(board[i][j] <= 0) continue;
                int val = getKilled(i, j);
                if(max_val < val){
                    pos = new Pair(i, j);
                    max_val = val;
                }
            }
        }

        ans += max_val;
        if(pos == null) return 0;
        kill(pos.y , pos.x, year);
        return max_val;
    }

    static int getKilled(int y, int x){
        int val = board[y][x];
        for(int i =0; i<4; i++){
            int yy = y;
            int xx = x;
            for(int j=0; j<k; j++){
                yy += ddy[i];
                xx += ddx[i];
                if(!isValid(yy, xx)) break;
                if(board[yy][xx] <= 0) break;
                val += board[yy][xx];
            }
        }
        return val;
    }

    static void kill(int y, int x, int year){

        board[y][x] = 0;
        killWater[y][x] = year+c;

        for(int i =0; i<4; i++){
            int yy = y;
            int xx = x;
            for(int j=0; j<k; j++){
                yy += ddy[i];
                xx += ddx[i];
                if(!isValid(yy, xx)) break;
                
                //벽이거나, 빈곳이면 뿌리고 멈춤.
                if(board[yy][xx] <= 0){
                    killWater[yy][xx] = year+c;
                    break;
                }
                board[yy][xx] =0;
                killWater[yy][xx] = year + c;
            }
        }

    }

    static boolean isValid(int y, int x){
        return ( y>=0 && x>=0 && y<n && x<n);
    }
    
    static void addBoard(int[][] from, int[][] to){
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                to[i][j] += from[i][j];       
            }
        }
    }

    static void showBoard(int[][] bb, int r, int c){
        System.out.println("\n");
        for(int i =0; i<r; i++){
            for(int j =0; j<c; j++){
                System.out.print(bb[i][j] + " ");
            }
            System.out.println();
        }
    }
}