import java.io.*;
import java.util.*;

public class Main{

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }

        public Pair(){
            y = -1;
            x = -1;
        }
    }

    static int r,c,t;
    static int[][] dy = {{-1, 0, 1, 0}, {1, 0, -1, 0}};
    static int[][] dx = {{0, 1, 0, -1}, {0, 1, 0, -1}};
    static int[][] board, temp;
    static Pair[] pos;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());
        board = new int[r][c];
        for(int i =0; i<r; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<c; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == -1){
                    if(pos == null){
                        pos = new Pair[2];
                        pos[0] = new Pair(i, j);
                        pos[1] = new Pair(i+1, j);
                        
                    }
                }
            }
        }

        System.out.println(simul());
    }

    static int simul(){
        while(t-- > 0){
            findSpreadSpot();
            onAirFresher();
        }

        return getAns();
    }

    static void findSpreadSpot(){
        temp = new int[r][c];

        for(int i =0; i<r; i++){
            for(int j =0; j<c; j++){
                if(board[i][j] > 0){
                    spread(i, j);
                }
            }
        }
        
        copyBoard(board, temp);
        board[pos[0].y][pos[0].x] = -1;
        board[pos[1].y][pos[0].x] = -1;
    }

    static void spread(int y, int x){

        int spreadCnt = 0;
        int val = board[y][x];
        for(int i =0; i<4; i++){
            int yy = y + dy[0][i];
            int xx = x + dx[0][i];
            if(!isValid(yy, xx, 0, 0, r, c) || board[yy][xx] == -1) continue;
            
            spreadCnt++;
            temp[yy][xx] += val/5;
        }
        temp[y][x] += (val - val/5*spreadCnt);
    }

    static void copyBoard(int[][] to, int[][] from){
        for(int i=0; i<r ; i++){
            for(int j =0; j<c; j++){
                to[i][j] = from[i][j];
            }
        }
    }

    static boolean isValid(int y, int x, int ny, int nx, int my, int mx){
        return (y >= ny && x >= nx && y <my && x< mx);
    }

    static void onAirFresher(){
        moveDust(0);
        moveDust(1);
    }

    static void moveDust(int type){
        int dir = 0;
        int y = pos[type].y + dy[type][dir];
        int x =  pos[type].x + dx[type][dir];
        
        while(true){
            if(dir == 4) break;
            int yy = y + dy[type][dir];
            int xx = x + dx[type][dir];

            if(type == 0){
                if(!isValid(yy, xx, 0, 0, pos[type].y+1, c)){
                    dir+=1;
                    continue;
                }
            }else{
                if(!isValid(yy, xx, pos[type].y, 0, r, c)){
                    dir+=1;
                    continue;
                }
            }

            if(board[yy][xx] != -1){
                board[y][x] = board[yy][xx];
            }else{
                board[y][x] = 0;
                break;
            }
            y = yy;
            x = xx;
        }
    }

    static int getAns(){
        int val =0;
        for(int i =0; i<r; i++){
            for(int j =0; j<c; j++){
                if(board[i][j] > 0){
                    val += board[i][j];
                }
            }
        }
        return val;
    }
}