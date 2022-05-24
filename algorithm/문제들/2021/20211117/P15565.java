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
        StringTokenizer st = new StringTokenizer(br.readLine());;
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            arr.add(Integer.parseInt(st.nextToken()));
        }
    }

    public static void pro(){
        int cnt = 0,  end =0, ans = Integer.MAX_VALUE;
        for(int start = 0; start < n; start++){
            while(cnt < k && end < n){
                if(arr.get(end) == 1) cnt++;
                end++;
            }
            if(cnt == k) ans = Math.min(ans, end-start);
            
            if(arr.get(start) == 1) cnt--;
            
        }
        if(ans == Integer.MAX_VALUE) System.out.println(-1);
        else System.out.println(ans);
    }
}
