import java.util.*;
import java.io.*;

public class Main{
    static int n, s;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());    
        arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i<n; i++){
            arr[i+1] = arr[i] + Integer.parseInt(st.nextToken());
        }

        int left = 0, right = 1;
        int ans = n+2;
        while(left <= right && right <n+1){
            if(arr[right]-arr[left] < s){
                right++;
            }else{
                ans = Math.min(right-left, ans);
                left++;
            }
        }
        if(ans == n+2) ans = 0;
        System.out.println(ans);
    }
}