import java.util.*;
import java.io.*;

public class Main {
    public static void main(String[] args) throws Exception {
        //input();
        //pro();
        int[][] dp = new int[41][2];
        dp[0][0] =1; dp[0][1] = 0;
        dp[1][0] =0; dp[1][1] = 1;
        for(int i =2; i<41; i++){
            dp[i][0] = dp[i-2][0] + dp[i-1][0];
            dp[i][1] = dp[i-2][1] + dp[i-1][1];
        }

        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb = new StringBuilder();
        while(t-- > 0){
            int k = Integer.parseInt(br.readLine());
            sb.append(dp[k][0] + " " + dp[k][1]).append('\n');
        }

        System.out.println(sb);
    }
    
    public static void input() throws Exception{
      
    }

    public static void pro(){
       
    }
}