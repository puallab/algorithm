import java.util.*;
import java.io.*;

public class Main{

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }
    }

    static int n, m, ans;
    static int[] dy  ={0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] board, group;
    static boolean[][] vis;
    static boolean[] grpVis;
    static List<Pair> list = new ArrayList<>();
    static List<Integer> groupPoint = new ArrayList<>();
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new int[n][m];
        group = new int[n][m];

        for(int i =0; i< n; i++){
            st= new StringTokenizer(br.readLine());
            for(int j=0 ; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0){
                    list.add(new Pair(i, j));
                }
            }
        }

    
        makeGroup();
        getAns();

        System.out.println(ans);

    }

    static void makeGroup(){
        vis = new boolean[n][m];
        groupPoint.add(0);
        int groupNum = 1;

        for(int i =0; i<n; i++){
            for(int j=0; j<m; j++){
                if(vis[i][j] || board[i][j] == 0) continue;
                int gp = bfs(i, j, groupNum++);
                groupPoint.add(gp);
            }
        }

    }

    static int bfs(int y, int x, int num){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        vis[y][x] = true;
        group[y][x] = num;

        int val = 1;

        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i =0; i<4; i++){
                int yy = now.y + dy[i];
                int xx = now.x + dx[i];
                if(yy <0 || xx<0 || yy>=n || xx>= m || vis[yy][xx] || board[yy][xx] == 0) continue; 
                val += 1;
                q.add(new Pair(yy, xx));
                vis[yy][xx] = true;
                group[yy][xx] = num;
            }
        }

        return val;
    }

    static void getAns(){
        
        grpVis = new boolean[groupPoint.size()];
        
        for(Pair now : list){
            HashSet<Integer> hash = new HashSet<>();
            int val = 1;
            
            for(int i =0; i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];
                if(y <0 || x<0 || y>=n || x>= m || board[y][x] == 0) continue;
                if(!hash.add(group[y][x])) continue;
                hash.add(group[y][x]);
                val += groupPoint.get(group[y][x]);
            }
            ans = Math.max(ans, val);
        }
    }
}