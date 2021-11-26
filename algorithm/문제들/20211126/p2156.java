import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws Exception {
        
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n = Integer.parseInt(br.readLine());
        int[] arr = new int[n+1];
        for(int i =1; i<=n; i++){
            arr[i] = Integer.parseInt(br.readLine());
        }

        int[][] dp = new int[n+1][3];
        dp[1][0] =0; dp[1][1] = arr[1]; dp[1][2] = 0;
   
        for(int i =2; i<=n; i++){

            //1. 현재 주스 안먹음
            dp[i][0] = Math.max(Math.max(dp[i-1][1], dp[i-1][2]),dp[i-1][0]);

            //2. 이전꺼 안먹고 현재 주스 먹음 
            dp[i][1] = dp[i-1][0] + arr[i];

            //3. 이전꺼 먹고 현재 주스 먹음
            dp[i][2] = dp[i-1][1] + arr[i];
        }
        
        int ans =0;
        for(int i =0; i<3; i++){
            ans = Math.max(ans, dp[n][i]);
        }

        System.out.println(ans);

    }
}

