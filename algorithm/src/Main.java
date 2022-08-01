import java.io.*;
import java.util.*;


public class Main{
    static int[][] board = new int[3][3];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        for(int i=0 ;i <3; i++){
            st = new StringTokenizer(br.readLine());  
            for(int j =0; j<3; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
              

        
        
    }

    

}

