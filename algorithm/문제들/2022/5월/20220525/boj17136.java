import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class boj17136 {
    static int[][] board = new int[10][10];
    static int[] sCnt ={0, 5, 5, 5, 5, 5};
    static int oCnt = 0;
    static int ans = 0;
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }
    
    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i =0; i<10; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0; j< 10; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 1 ) oCnt++;
            }
        }
    }

    static void pro(){
        dfs();
    }

    static void dfs(int cnt, int idx){
        if(oCnt == 0){
            ans = cnt;
            return;
        }

        int y = idx/10;
        int x=  idx%10;
        if(board[y][x] == 0) dfs(cnt, idx+1);
        else{
            for(int i =5; i>=1; i--){
                if(sCnt[i] == 0 ) continue;
                if(!isPossible(y, x, i)) continue;
                fill(y, x, i, 1);
                sCnt[i]--;
                dfs(cnt-i);
                sCnt[i]++;
                fill(y, x, i, 0);
            }
        }
    }

    static void fill( int y, int x, int len, int val){
        for(int j= y; j < y+len; j++){
            for(int i = x; i < x+len; i++){
                board[j][i] = val;
            }
        }
    }

    static boolean isPossible(int y, int x ,int len){
        if(y+len >= 10 || x+len >= 10) return false;
        for(int j = y; j < y+len; j++){
            for(int i = x; i < x+len; i++){
                if(board[j][i] == 0 ) return false;
            }
        }
        return true;
    }
}
