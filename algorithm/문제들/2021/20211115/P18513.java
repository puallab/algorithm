import java.util.*;
import java.io.*;

public class P18513 {
    static int n, k;
    static Queue<Integer> q = new LinkedList<>();
    static HashSet<Integer> s = new HashSet<>();
    static int[] dx= {1,-1};
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
            int k =Integer.parseInt(st.nextToken()) ;
            q.add(k);
            s.add(k);
        }
    }

    public static void pro(){
        long ans = 0L;
        int cnt =0;
        int distance = 1;
        while(!q.isEmpty()){
            
            int qsize = q.size();
            while(qsize-- > 0){
                int x = q.poll();
                for(int i =0; i<2; i++){
                    int cx = x + dx[i];
                    if(s.contains(cx)) continue;
                    s.add(cx);
                    ans += distance;
                    q.add(cx);
                    cnt++;
                    if(cnt == k ){
                        System.out.println(ans);
                        return;
                    }
                }
            }
            distance++;
        }
        
    }
}
