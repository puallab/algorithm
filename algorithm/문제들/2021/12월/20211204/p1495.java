import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       int n = Integer.parseInt(st.nextToken());
       int s = Integer.parseInt(st.nextToken());
       int m = Integer.parseInt(st.nextToken());
       int[] arr = new int[n+1];

       st = new StringTokenizer(br.readLine());
       for(int i =1; i<=n ;i++){
           arr[i] = Integer.parseInt(st.nextToken());
       }

       int[][] dp = new int[n+1][m+1];

       dp[0][s] = 1;

       for(int i = 1; i<=n; i++){
           for(int j= m; j>=0; j--){
               if(j  >= arr[i]) dp[i][j] = Math.max(dp[i][j], dp[i-1][j-arr[i]]);
               if(j +arr[i] <= m ) dp[i][j] = Math.max(dp[i][j], dp[i-1][j+arr[i]]);
           }
       }

       int ans = -1;
       for(int i = m; i>=0 ; i--){
           if(dp[n][i] == 0) continue;
           ans = i;
           break;
       }
       System.out.println(ans);
    }

    public static void input() throws Exception{
       
    }

    public static void pro(){

    }
}
   

