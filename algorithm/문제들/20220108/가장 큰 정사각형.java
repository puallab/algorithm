import java.util.*;
import java.io.*;

// 시간초과.,.
class Solution
{
    static int[] dy = {0,1,1};
    static int[] dx = {1,0,1};
    public int solution(int [][]board)
    {
        int answer = 0;
        int n = board.length;
        int m = board[0].length;
        for(int i =0; i<n; i++){
            for(int j =0; j<m; j++){
                if(board[i][j] == 0 ) continue;
                int k = bfs(i, j, board);
                
                answer = Math.max(answer, k);
            }
        }
        
        return answer*answer;
    }
    
    static int bfs(int y, int x, int[][] board){
        int n = board.length;
        int m = board[0].length;
        boolean[][] vis = new boolean[n][m];
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y,x));
        vis[y][x] = true;
        int ans = 1;
        while(!q.isEmpty()){
            int size = q.size();
            for(int j=0; j<size; j++){
                Pair now = q.poll();
                for(int i =0; i<3; i++){
                    y = now.y + dy[i];
                    x = now.x + dx[i];
                    
                    if( y < 0 || x <0 || y>=n || x>=m) return ans;
                    if(board[y][x] == 0 ) return ans;
                    if(vis[y][x]) continue;
                    vis[y][x] = true;
                    q.add(new Pair(y,x));
                }
            }
            
            ans++;
        }
        return ans;
    }
    
    static class Pair{
        int y,x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }
    }
}

//dp로 해결해야함.
/**
 *  board[i][j] == 1인 지점에서
 *  board[i-1][j], board[i][j-1], board[i-1][j-1] 중 가장 작은 값을 선택 + 1,
 *  정사각형울 만들기 위한 조건이래나 뭐래나.
 */
class Solution2
{
    public int solution(int [][]board)
    {
        int answer = 0;
        int m = board.length;
        int n = board[0].length;
        int[][] dp = new int[m+1][n+1];
        for(int i =0; i< m ; i++){
            for(int j=0; j<n; j++){
                dp[i+1][j+1] = board[i][j];
            }
        }
        
        for(int i =1; i<= m ; i++){
            for(int j=1; j<=n; j++){
                if(dp[i][j] == 0) continue;
                dp[i][j] = Math.min( Math.min(dp[i-1][j-1], dp[i-1][j]), dp[i][j-1] ) +1;
                answer = Math.max(answer, dp[i][j]);
            }
        }
        
        return answer*answer;
    }
}