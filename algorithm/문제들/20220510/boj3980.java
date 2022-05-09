import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    static int c, ans;
    static int[][] board = new int[11][11];
    static boolean[] position = new boolean[11];
    public static void main(String[] args) throws Exception{
        input();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        c = Integer.parseInt(br.readLine());

        while(c-- > 0){
            // position array & ans init
            Arrays.fill(position, false);
            ans = 0;

            for(int i =0; i< 11; i++){
                StringTokenizer st = new StringTokenizer(br.readLine());
                for(int j=0; j<11; j++){
                    board[i][j] = Integer.parseInt(st.nextToken());
                }
            }
            dfs(0, 0);
            System.out.println(ans);
        }
    }

    static void dfs(int number, int sum){
        if(number == 11){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i =0; i<11; i++){
            if(position[i] || board[number][i] ==0) continue;
            position[i] = true;
            dfs(number+1, sum + board[number][i]);
            position[i] = false;
        }
    }
}
