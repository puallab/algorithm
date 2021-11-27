import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws Exception {
        
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int t= Integer.parseInt(br.readLine());

        final int MOD = 1000000009;
        long[][] dp = new long[100001][4];
        dp[1][1] = 1; dp[1][0] = 1;

        dp[2][2] = 1; dp[2][0] = 1;

        dp[3][1] = 1; dp[3][0] = 3;
        dp[3][2] = 1;
        dp[3][3] = 1;
        for(int i =4; i<= 100000; i++){
            dp[i][1] = (dp[i-1][0]-dp[i-1][1])%MOD;
            dp[i][2] = (dp[i-2][0]-dp[i-2][2])%MOD;
            dp[i][3] = (dp[i-3][0]-dp[i-3][3])%MOD;
            dp[i][0] = ((dp[i][1] + dp[i][2])%MOD +dp[i][3])%MOD;
        }
        while(t -- >0){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n][0]).append('\n');
        }
        System.out.println(sb);
    }
    
    public static void input() throws Exception{
        

    }

    public static void pro(){
        
    }

   

}

