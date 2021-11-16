import java.util.*;
import java.io.*;

public class P15961 {
    static int n, d, k, c;
    static boolean flag = false;
    static ArrayList<Integer> arr = new ArrayList<>();

    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       d = Integer.parseInt(st.nextToken());
       k = Integer.parseInt(st.nextToken());
       c = Integer.parseInt(st.nextToken());
       for(int i =0; i<n; i++){
           int t = Integer.parseInt(br.readLine());
           arr.add(t);

           //쿠폰의 음식 있는지 확인
           if(t == c) {
               flag = true;
           }
       }

       //원형으로 잇기 위해서 k+1개 만큼을 다시 이어 붙여줌.
       for(int i =0; i<= k; i++){
           arr.add(arr.get(i));
       }
    }

    public static void pro(){
        //m : 연속해서 고른 접시 수, cnt : 먹은 접시의 종류 수.
        int m =0, end = 0, cnt =0, ans = 0; 
        int length = arr.size();
        int[] vis = new int[d+1];
        for(int start = 0; start < length-1; start++){
            while(m < k && end < length-1){
                //처음 먹어보는 음식이면 cnt 증가.
                if(vis[arr.get(end)] == 0) cnt++;
                vis[arr.get(end)]++;
                m++;
                end++;
            }
            if(m == k){
                //쿠폰이 처음부터 포함
                if(flag){
                    //쿠폰 음식 먹고, end이 안먹은 음식인 경우.
                    if(vis[c] > 0 && vis[arr.get(end)] == 0){
                        ans = Math.max(ans, cnt+1);
                    }else{
                        ans = Math.max(ans, cnt);
                    }
                }
                else{
                    //쿠폰 음식이 없었던 경우
                    ans = Math.max(ans, cnt+1);
                }
            }

            vis[arr.get(start)]--;
            if(vis[arr.get(start)] == 0) cnt--;
            m--;
        }

        System.out.println(ans);
    }
}
