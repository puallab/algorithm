import java.io.*;
import java.util.*;

import javax.swing.colorchooser.ColorSelectionModel;

public class Main{
<<<<<<< HEAD
    static int r, c, n;
    static int[][] board;
=======
    static int n, m, ans;
    static String[] board;
    static boolean[][] vis;
>>>>>>> c66a16009bdfedda8d297760e3ad786ca4e7ef29
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static List<Integer> list = new ArrayList<>();

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }

        public Pair(){
            this(0, 0);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
<<<<<<< HEAD
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[r][c];

        for(int i =0; i<r ;i++){
           String k = br.readLine();
           for(int j =0; j<c; j++){
                if(k.charAt(j) !='O'){
                    board[i][j] = -1;
                } 
           }
        }

        simulate();
        printAns();
    }

    static void simulate(){
        int t = 1;
        while(++t <= n){
            if(t%2 == 1 ){
                bomb(t-3);
            }else{
                install(t);
            }
            
        }
    }

    static void bomb(int time){
        for(int i =0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == time){
                    board[i][j] = -1;
                    for(int k=0; k<4; k++){
                        int y = i+dy[k], x= j+dx[k];
                        if(y <0 || y>=r || x<0|| x>=c || board[y][x] == time) continue;
                        board[y][x] = -1;
                    }
                }
=======
        n = Integer.parseInt(br.readLine());
        board = new String[n];
        vis = new boolean[n][n];
        for(int i =0; i<n ;i++){
            board[i]  = br.readLine();
        }
        
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(vis[i][j] || board[i].charAt(j) == '0') continue;
                ans++;
                list.add(bfs(i,j));
>>>>>>> c66a16009bdfedda8d297760e3ad786ca4e7ef29
            }
        }

        Collections.sort(list);

        StringBuilder sb = new StringBuilder();
        sb.append(ans +"\n");
        for(int k : list){
            sb.append(k + "\n");
        }

        System.out.println(sb.toString());
    }

<<<<<<< HEAD
    static void install(int time){
        for(int i =0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == -1){
                    board[i][j]= time;
                }
            }
        }
    }

    static void printAns(){
        StringBuilder sb = new StringBuilder();

        for(int i =0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == -1){
                    sb.append(".");
                }else{
                    sb.append("O");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }

=======
    static int bfs(int y, int x){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        vis[y][x] = true;
        int ret = 1;

        while(!q.isEmpty()){
            Pair k = q.poll();
            for(int i =0; i<4; i++){
                int yy = dy[i] + k.y;
                int xx = dx[i] + k.x;
                if(isValid(yy,xx)){
                    q.add(new Pair(yy, xx));
                    vis[yy][xx] = true;
                    ret++;
                }
            }
        }
        return ret;
    }
>>>>>>> c66a16009bdfedda8d297760e3ad786ca4e7ef29

    static boolean isValid(int y, int x){
        return ( y>=0 && y <n && x>= 0 && x<n && vis[y][x] == false && board[y].charAt(x) =='1') ;
    }
}

