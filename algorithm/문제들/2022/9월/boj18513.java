public class boj18513 {
    import java.io.*;
import java.util.*;

public class Main{
    static int n, k;
    static long ans;
    static int[] vis;
    static int[] dy = {1, -1};
    static Queue<Integer> q = new LinkedList<>();
    static final int MAX = 200_000_001;
    static final int addedVal = 100_000_000;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());    
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        vis = new int[MAX];

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            int val = Integer.parseInt(st.nextToken())+ addedVal;
            q.add(val);
            vis[val] = 1;
        }

        find();
        System.out.println(ans-k);
    }

    static void find(){
        int cnt = 0;
        while(cnt != k){
            int now = q.poll();
            for(int i =0; i<2; i++){
                int next = now + dy[i];
                if(next <0 || next >= MAX || vis[next] > 0) continue;
                
                q.add(next);
                vis[next] = vis[now] + 1;
                ans += vis[next];
                cnt++;
                if(cnt == k){
                    return;
                }
            }
        }
    }
    
}


}
