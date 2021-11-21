import java.util.*;
import java.io.*;

public class Main {
    static int n, k, x_min = (int)1e6, x_max = 0;
    static int[] arr = new int[1000001];
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());  
       for(int i =0; i<n; i++){
           st = new StringTokenizer(br.readLine());
           int g = Integer.parseInt(st.nextToken());
           int x = Integer.parseInt(st.nextToken());
           arr[x] = g;
           x_min = Math.min(x_min, x);
           x_max = Math.max(x_max, x);
       }
    }

    public static void pro(){
        long ans =0, cnt = 0;
        k = 2*k+1;
        int m = 0, right = x_min;
        for(int left = x_min; left <= x_max; left++){
            while(m < k && right < x_max+1){
                cnt += arr[right];
                m++;
                right++;
            }
            if(m == k){
                ans = Math.max(cnt, ans);
            }
            cnt -= arr[left];
            m--;
        }
        System.out.println(ans);
    }

}