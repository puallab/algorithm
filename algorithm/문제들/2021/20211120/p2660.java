import java.util.*;
import java.io.*;

public class Main {

    static int n;
    static final int INF = (int)1e8;
    static int[][] arr = new int[51][51];
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());

       for(int i =0; i<n; i++){
           for(int j =0; j<n; j++){
               if(i == j) arr[i][j] =0;
               else arr[i][j] = INF;
           }
       }

       while(true){
           st = new StringTokenizer(br.readLine());
           int a= Integer.parseInt(st.nextToken());
           int b = Integer.parseInt(st.nextToken());
           if(a == -1 && b == -1) break;
           arr[a-1][b-1] = 1;
           arr[b-1][a-1] = 1;
       }
    }

    public static void pro(){
       
        for(int k =0; k < n; k++){
            for(int i =0; i<n; i++){
                for(int j =0; j<n; j++){
                    arr[i][j] = Math.min(arr[i][j], arr[i][k]+ arr[k][j]);
                }
            }
        }

        int value = INF;
        int ans = 0;
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<n; i++){
            int cnt=0;
            for(int j=0; j<n; j++){
                cnt = Math.max(cnt, arr[i][j]);
            }
            if(cnt < value){
                value = cnt;
                ans = 1;
                sb = new StringBuilder();
                sb.append(i+1).append(' ');
            }else if(cnt == value){
                ans++;
                sb.append(i+1).append(' ');
            }
        }
        System.out.println(value + " " + ans);
        System.out.println(sb);
    }

}