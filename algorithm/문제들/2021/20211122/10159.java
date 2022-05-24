import java.util.*;
import java.io.*;

public class Main {
    static int n, m;
    static int[][] board = new int[501][501];
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       n = Integer.parseInt(br.readLine());
       m = Integer.parseInt(br.readLine());  
       for(int i =0; i< m; i++){
           StringTokenizer st= new StringTokenizer(br.readLine());
           int a = Integer.parseInt(st.nextToken())-1;
           int b = Integer.parseInt(st.nextToken())-1;
           board[a][b] = 1;
           board[b][a] = -1; 
       }
     
    }

    public static void pro(){
        for(int k =0; k<n; k++){
            for(int i =0; i<n; i++){
                for(int j =0; j<n; j++){
                    if(board[i][j] != 0) continue;
                    if(board[i][k] == 1 && board[k][j] == 1){
                        board[i][j] = 1;
                        board[j][i] = -1;
                    }
                }
            }
        }

        StringBuilder sb = new StringBuilder();
        for(int i =0; i<n; i++){
            int cnt = 0;
            for(int j =0; j<n; j++){
                if(i == j ) continue;
                if(board[i][j] == 0) cnt++;
            }
            sb.append(cnt).append('\n');
        }

        System.out.println(sb);
        
    }

}