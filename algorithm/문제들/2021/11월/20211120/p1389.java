import java.util.*;
import java.io.*;

public class Main {

    static int n, m;
    static final int INF = (int)1e8;
    static int[][] arr = new int[101][101];
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
       BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
       StringTokenizer st = new StringTokenizer(br.readLine());
       n = Integer.parseInt(st.nextToken());
       m = Integer.parseInt(st.nextToken());

       for(int i =0; i<n; i++){
           for(int j =0; j<n; j++){
               if(i == j) arr[i][j] =0;
               else arr[i][j] = INF;
           }
       }

       for(int i =0; i<m; i++){
           st = new StringTokenizer(br.readLine());
           int a= Integer.parseInt(st.nextToken());
           int b = Integer.parseInt(st.nextToken());
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
        for(int i =0; i<n; i++){
            int cnt=0;
            for(int j=0; j<n; j++){
                if(arr[i][j] == INF) continue;
                cnt += arr[i][j];
            }
            if(cnt < value){
                ans = i;
                value = cnt;
            }
        }
        
        System.out.println(ans+1);
        
    }

}