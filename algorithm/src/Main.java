import java.io.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;

public class Main{
    static int n;
    static int[] parents;
    static ArrayList<Integer>[] board;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new ArrayList[n+1];
        for(int i=0; i<n+1; i++){
            board[i] = new ArrayList<>();
        }
        parents = new int[n+1];
        
        for(int i=0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a= Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());
            board[a].add(b);
            board[b].add(a);
        }

        parents[1] = -1;
        dfs(1);

        for(int i =2; i<n+1; i++){
            System.out.println(parents[i]);
        }
    }


    static void dfs(int from){

        
    }

  

    
   


}

