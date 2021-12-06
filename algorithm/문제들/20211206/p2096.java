import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine());
       int[][] arr = new int[n+1][3];
       for(int i =1; i<=n; i++){
           StringTokenizer st = new StringTokenizer(br.readLine());
           for(int j=0; j<3; j++){
               arr[i][j] = Integer.parseInt(st.nextToken());
           }            
       }
       
       int[][] maxDP = new int[n+1][3];
       int[][] minDP = new int[n+1][3];
       maxDP[1][0] = minDP[1][0] = arr[1][0];
       maxDP[1][1] = minDP[1][1] = arr[1][1];
       maxDP[1][2] = minDP[1][2] = arr[1][2];

       for(int i =2; i<= n; i++){
           // 최대값 찾기.
           maxDP[i][0] = Math.max(maxDP[i-1][0], maxDP[i-1][1]) + arr[i][0];
           maxDP[i][1] = Math.max(maxDP[i-1][0], Math.max(maxDP[i-1][1], maxDP[i-1][2])) + arr[i][1];
           maxDP[i][2] = Math.max(maxDP[i-1][1], maxDP[i-1][2]) + arr[i][2];
           
           // 최소값 찾기.
           minDP[i][0] = Math.min(minDP[i-1][0], minDP[i-1][1]) + arr[i][0];
           minDP[i][1] = Math.min(minDP[i-1][0], Math.min(minDP[i-1][1], minDP[i-1][2])) + arr[i][1];
           minDP[i][2] = Math.min(minDP[i-1][1], minDP[i-1][2]) + arr[i][2];
       }

       int max = 0, min = (int)1e7;
       for(int i = 0; i<3; i++){
           max = Math.max(max, maxDP[n][i]);
           min = Math.min(min, minDP[n][i]);
       }

       System.out.println(max +" " + min);

    }

    public static void input() throws Exception{
       
    }

    public static void pro(){

    }

    

    

    
}
   

