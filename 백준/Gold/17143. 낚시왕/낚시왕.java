import java.util.*;

import java.io.*;

public class Main{

    static class Shark{
        int y, x, size, vc, dir;
        boolean alive;
        public Shark(int yy, int xx, int s, int v, int d, boolean flag){
            y =yy;
            x =xx;
            size =s;
            vc =v;
            dir =d;
            alive = flag;
        }
    }

    static int r, c, m, ans=0;
    static int[] dy = {0, -1, 1, 0, 0};
    static int[] dx = {0, 0, 0, 1, -1};
    static int[][] board, temp;
    static Shark[] sList;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new int[r][c];

        sList = new Shark[m+1];
        sList[0] = new Shark(-1, -1, 0, 0, 0, false);
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            int v = Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int s = Integer.parseInt(st.nextToken());
            sList[i+1] = new Shark(y, x, s, v, d, true);
            board[y][x] = i+1;
        }

        simul();
        System.out.println(ans);
    }

    static void simul(){
        int pos = -1;
        while(++pos< c){

            // 사냥
            hunt(pos);
            //상어 이동
            moveShark();
        }
    }

    static void showBoard(){
        System.out.println("\n");
        for(int i =0; i<r; i++){
            for(int j =0; j<c; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void hunt(int pos){
        int y= 0;
        while(y < r){
            int num = board[y][pos];
            if(num > 0){
                ans += sList[num].size;
                sList[num].alive = false;
                board[y][pos] = 0;
                break;
            }
            y++;
        }
    }

    static void moveShark(){
        temp = new int[r][c];
        for(int i=1; i< sList.length; i++){
            if(sList[i].alive){
                move(i);
            }
        }
        copyBoard(board, temp);
    }

    static void copyBoard(int[][] to, int[][] from){
        for(int i =0; i<r; i++){
            for(int j =0; j<c; j++){
                to[i][j] = from[i][j];
            }
        }
    }

    static void move(int idx){
        Shark target = sList[idx];
        int dir = target.dir;
        int modY = 2*(r-1);
        int modX = 2*(c-1);
        int y = (target.y + dy[dir]*target.vc)%modY;
        int x = (target.x + dx[dir]*target.vc)%modX;
        while(true){
            if( y < 0 ){
                dir = getDir(dir);
                y = dy[dir]*Math.abs(y);
            }else if( y >=r ){
                dir = getDir(dir);
                y = 2*(r-1)+dy[dir]*y;
            }else if(x< 0){
                dir = getDir(dir);
                x = dx[dir]*Math.abs(x);
            }else if(x >= c){
                dir = getDir(dir);
                x = 2*(c-1)+dx[dir]*x;
            }
            else{
                break;
            }
        }

        board[target.y][target.x] = 0;
        target.dir = dir;
        
        if(temp[y][x] > 0){
            int num = temp[y][x];
            if(target.size < sList[num].size){
                target.alive= false;
            }else{
                sList[num].alive = false;
                temp[y][x] = idx;
                target.y = y;
                target.x = x;
            }
        }else{
            temp[y][x] = idx; 
            target.y = y;
            target.x = x;
            
        }
        

    }

    static int getDir(int d){
        return d%2==1?d+1:d-1;
    }
}