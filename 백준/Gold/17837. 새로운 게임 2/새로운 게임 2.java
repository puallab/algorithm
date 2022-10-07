import java.util.*;
import java.io.*;

public class Main{

    static class Chess{
        int idx, dir, y, x;
        public Chess(int n, int yy, int xx ,int d){
            idx = n;
            y = yy;
            x =xx;
            dir =d;
        }
    }

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y = yy;
            x = xx;
        }
    }

    static boolean flag= false;
    static int n, k;
    static int[] dy = {0, 0, 0, -1 ,1};
    static int[] dx = {0, 1, -1, 0, 0};
    static int[][] board;
    static ArrayList<Chess>[][] stage;
    static Pair[] pos;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        stage = new ArrayList[n][n];

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                stage[i][j] = new ArrayList<>();
            }
        }

        pos = new Pair[k];
        

        for(int i=0; i<k; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());


            pos[i] = new Pair(y, x);
            stage[y][x].add(new Chess(i, y, x, dir));
        }
        
        System.out.println(simul());
    }   

    static int simul(){
        int t= 0;
        while(!flag && ++t <= 100000){
            moveAllChess();   
        }

        return t>1000? -1: t;
    }

    static void moveAllChess(){
        for(int i =0; i<pos.length; i++){
            //if(flag) break;
            doMove(pos[i].y, pos[i].x, i); 
        }
    }

    static void doMove(int y, int x, int idx){

        int size = stage[y][x].size();
        for(int i =0; i<size; i++){
            if(stage[y][x].get(i).idx == idx){
                checkColor(y, x, i);
                break;
            }
        }
    }

    static void checkColor(int y, int x, int loc){
        Chess now = stage[y][x].get(loc);
        int size = stage[y][x].size();
        int ny = y + dy[now.dir];
        int nx = x + dx[now.dir];
        
        if(isBlue(ny, nx)){
            now.dir = now.dir%2==1?now.dir+1:now.dir-1;
            ny = y+ dy[now.dir];
            nx = x+ dx[now.dir];
            if(isBlue(ny, nx)){
                return;
            }
        }
        
        if(board[ny][nx] == 1){
            for(int i = size-1; i >= loc; i--){
                moveChess(stage[y][x].get(i), ny, nx);
            }
            
        }else{
            for(int i =loc; i<size; i++){
                moveChess(stage[y][x].get(i), ny, nx);
            }
        }
        
        for(int i = loc; i<size; i++){
            stage[y][x].remove(loc);
        }

        if(stage[ny][nx].size() >= 4){
            flag = true;
        }

        

    }

    static void moveChess(Chess chess, int y, int x){
        chess.y = pos[chess.idx].y = y;
        chess.x = pos[chess.idx].x = x;
        stage[y][x].add(chess);
    }

    static boolean isBlue(int y, int x){
        return (y< 0 || x <0 || y>=n || x >= n || board[y][x] == 2);
    }

    static void showBoard(int y, int x ,int ny, int nx, int size, int dir){
        System.out.println("\n" + "{"+ y +", " + x +"} -> {" + ny +", " + nx +"} dir : " +dir + "  size: " + size );
        for(int i =0; i<n ;i++){
            for(int j =0; j<n; j++){
                System.out.print(stage[i][j].size() + " ");
            }
            System.out.println();
        }
    }
}