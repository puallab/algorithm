import java.io.*;
import java.util.*;


public class Main{
    static int n, m, h, ans =-1;
    static boolean[][] board, vis;
    static List<Integer> list = new ArrayList<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());


        board = new boolean[h][n];
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken())-1;
            int b = Integer.parseInt(st.nextToken())-1;
            board[a][b] = true;
        }

        for(int i =0; i<h ;i++){
            for(int j =0; j<n-1; j++){
                if(board[i][j] != true){
                    int idx = i*n + j;
                    list.add(idx);
                }
            }
        }

        for(int i=0; i<=3; i++){
            if(doBacktracking(0, i, 0) == true){
                ans = i;
                break;
            }
        }

        System.out.println(ans);
        
    }

    static void showboard(){
        System.out.println("\n");
        for(int i =0; i<h; i++){
            for(int j=0; j<n; j++){
                if(board[i][j]) System.out.print("1 ");
                else System.out.print("0 ");
            }
            System.out.println();
        }
    }

    static boolean doBacktracking(int idx, int len, int picked){
        if(picked == len){
            //showboard();
            return checkMove();
        }

        for(int i = idx; i<list.size(); i++){
            int y = list.get(i)/n;
            int x = list.get(i)%n;
            if(checkLeft(y, x) || checkRight(y, x+1) ){
                continue;
            }
            board[y][x] = true;
            if(doBacktracking(i+1, len, picked+1)){
                return true;
            }
            board[y][x] = false;
        }
        return false;
    }

    static boolean checkRight(int y, int x){
        
        if( x< n-1){
            if(board[y][x] == true){
                return true;
            }
        }

        return false;
    }

    static boolean checkLeft(int y, int x){
        if( x > 0){
            if(board[y][x-1] == true) {
                return true;
            }
        }

        return false;
    }

    static boolean checkMove(){
        
        for(int x =0; x<n; x++){
            if(startMove(x) == false){
                return false;
            }
        }
        return true;
    }

    static boolean startMove(int startIdx){
        int y = 0;
        int x = startIdx;
        while(y != h){
            if(checkLeft(y, x)){
                x--;
            }else if(checkRight(y, x)){
                x++;
            }
            y++;
        }

        return (x == startIdx);
    }
}