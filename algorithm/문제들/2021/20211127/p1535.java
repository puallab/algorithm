import java.util.*;
import java.io.*;

public class Main {
 
    public static void main(String[] args) throws Exception {
        
        BufferedReader br= new BufferedReader(new InputStreamReader(System.in));
        int n= Integer.parseInt(br.readLine());
        int[] L = new int[n];
        int[] J = new int[n];
        
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i< n; i++){
            L[i] = Integer.parseInt(st.nextToken());
        }
        st = new StringTokenizer(br.readLine());
        for(int i=0; i<n; i++){
            J[i] = Integer.parseInt(st.nextToken());
        }

        int[] dp = new int[100];
        
        for(int i = 0; i<n; i++){
            for(int j = 100; j >= L[i]; j--){
                dp[j] = Math.max(dp[j-L[i]] + J[i], dp[j]);
            }
        }

        int ans = 0;
        for(int i =1; i<100; i++){
            ans = Math.max(ans, dp[i]);
        }
        System.out.println(ans);
    }
    
    public static void input() throws Exception{
        

    }

    public static void pro(){
        
    }

   

}

