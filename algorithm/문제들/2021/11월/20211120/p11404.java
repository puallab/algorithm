import java.util.*;
import java.io.*;

public class Main {

    static final int INF = (int)1e9;
    static int n, m;
    static int[][] board = new int[101][101];

    
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       m = Integer.parseInt(br.readLine());

       for(int i =0; i<n; i++){
           for(int j=0;j<n; j++){
               if(i == j) board[i][j] =0;
               else board[i][j] = INF;

           }
       }

       for(int i =0; i<m; i++){
           StringTokenizer st = new StringTokenizer(br.readLine());
           int from = Integer.parseInt(st.nextToken());
           int to = Integer.parseInt(st.nextToken());
           int coast = Integer.parseInt(st.nextToken());
           if(board[from-1][to-1] > 0 && board[from-1][to-1] != INF){
               board[from-1][to-1] = Math.min(board[from-1][to-1], coast);
           }
           else board[from-1][to-1] = coast;    
       }
    }

    public static void pro(){
       
        for(int k =0; k<n; k++){
            for(int i =0; i<n; i++){
                for(int j =0; j<n; j++){
                    board[i][j] = Math.min(board[i][j], board[i][k] + board[k][j]);
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
               if(board[i][j] == 0 || board[i][j] == INF) sb.append(0).append(' ');
               else sb.append(board[i][j]).append(' ');
            }
            sb.append('\n');
        }

        System.out.println(sb);
    }

}