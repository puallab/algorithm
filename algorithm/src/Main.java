import java.util.*;
import java.io.*;



public class Main {
    static int[][] board;
    static int n, k, l;
    static int[][] pair;
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    public static void input() throws Exception{
        BufferedReader br =new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        k = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for(int i =0; i<k; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken())-1;
            int x = Integer.parseInt(st.nextToken())-1;
            board[y][x] = 1;
        }

        l = Integer.parseInt(br.readLine());
        pair = new int[l][2];
        for(int i =0; i<l; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int t = Integer.parseInt(st.nextToken());
            int d = 0;
            if(st.nextToken().equals("D")) d =1;
            pair[i][0] = t; pair[i][1] = d;
        }

    }

    public static void pro(){
        int ans = 0;
        int idx = 0; //l에 관한 index 정보.
        int dir = 1; //현재 방향을 나타냄

        int[] dy = {0,1};
        int[] dx = {1,0};
        
        int[] head = {0, 0}; //현재 머리의 위치 기록.
        boolean[][] frint = new boolean[n][n];
        frint[0][0] = true;
        Queue<Integer> qy = new LinkedList<>();
        Queue<Integer> qx = new LinkedList<>();
        qy.add(head[0]); qx.add(head[1]);
        while(true){
            int cy = head[0] + dy[dir];
            int cx = head[1] + dx[dir];
            if(cy < 0 || cy >= n || cx <0 || cx>=n) break;
            if(frint[cy][cx] == true ) break;
            frint[cy][cx] = true;
            if(board[cy][cx] == 1){
                //사과있으면 그냥 머리만 늘리기.
                board[cy][cx] = 0;
                frint[cy][cx] =true;
                qy.add(cy); qx.add(cx);
            }else{
                //사과 없으면 발자취 없에고 
                //추가하기.
                frint[cy][cx] = true;
                int yy = qy.poll();
                int xx = qx.poll();
                frint[yy][xx] = false;
            }   
            ans++;
        }

        System.out.println(ans);
    }

   

}