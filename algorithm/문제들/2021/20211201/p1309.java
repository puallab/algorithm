import java.util.*;
import java.io.*;

public class Main {


    public static void main(String[] args) throws Exception {
        
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());

        int[][] dp = new int[n+1][3];
        final int MOD = 9901;
        dp[1][0] = dp[1][1] = dp[1][2]  = 1;
        
        for(int i =2; i <= n ; i++){
            dp[i][0] = ((dp[i-1][0] + dp[i-1][1])%MOD + dp[i-1][2])%MOD;
            dp[i][1] = (dp[i-1][0] + dp[i-1][2])%MOD;
            dp[i][2] = (dp[i-1][0] + dp[i-1][1])%MOD;
        }

        System.out.println( ((dp[n][0] + dp[n][1])%MOD + dp[n][2] ) % MOD );
       
    }

    
    
}

