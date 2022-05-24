import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;
public class boj6443 {
    static int n;
    static char[] arr;
    static boolean[] vis;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws Exception{
        input();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        while(n-- > 0){
            String s = br.readLine();
            arr = s.toCharArray();
            vis = new boolean[arr.length];
            Arrays.sort(arr);
            dfs(0, "");
        }
        System.out.println(sb.toString());
    }

    static void dfs( int picked, String s){
        if(picked == arr.length){
            sb.append(s+ "\n");
            return;
        }

        char prev = 0;
        for(int i =0; i< arr.length; i++){
            if(prev == arr[i]) continue;
            if(vis[i]) continue;
            vis[i] = true;
            prev = arr[i];
            dfs( picked+1, s + Character.toString(arr[i]));
            vis[i] = false;
        }
    }
}
