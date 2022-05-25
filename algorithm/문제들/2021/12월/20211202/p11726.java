
import java.io.*;

public class p11726 {

    public static void main(String[] args) throws Exception {
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       int n = Integer.parseInt(br.readLine());
       final int MOD = 10007;

       int[] dp = new int[n+1];
       dp[1] = 1;
       dp[2] = 2;

       for(int i =3 ; i<=n; i++){
           dp[i] = (dp[i-2] + dp[i-1])%MOD;
       }
       System.out.println(dp[n]);
    }

   
    

}

