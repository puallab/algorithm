import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws Exception {
        
        int[] dp = new int[1000001]; 
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        dp[3] = 4;
        final int MOD = 1000000009;
        for(int i =4; i<= 1000000; i++){
            dp[i] = ((dp[i-1] + dp[i-2])%MOD + dp[i-3])%MOD; 
        }
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int t = Integer.parseInt(br.readLine());
        StringBuilder sb= new StringBuilder();
        while(t -- > 0){
            int n = Integer.parseInt(br.readLine());
            sb.append(dp[n]).append('\n');
        }

        System.out.println(sb);
    }
    
   
}

