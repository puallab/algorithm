import java.util.*;
import java.io.*;

public class Main {
    static int n, k;
    static ArrayList<Integer> arr = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       st = new StringTokenizer(br.readLine());
       for(int i =0; i<n; i++){
           arr.add(Integer.parseInt(st.nextToken()));
       }
    }

    public static void pro(){
        int cm =0, end = 0;
        long ans = -(1<<30), cnt =0;
        for(int start = 0; start < n; start++){
            if(end == n) break;
            
            while(cm < k && end < n){
                cm ++;
                cnt += arr.get(end);
                end++;
            }
            ans = Math.max(ans, cnt);
            cnt -= arr.get(start);
            cm--;
        }

        System.out.println(ans);
    }
}
