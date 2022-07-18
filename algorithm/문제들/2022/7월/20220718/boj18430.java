public class boj18430 {
    static int n,m, ans;
    static boolean[][] board;
    static int[][] map;
    static int[][][] dShape = new int[][][]{
        { {0, 0}, {0, 1}, {1, 0} },
        { {0, 0}, {0, -1}, {1, 0} },
        { {0, 0}, {0, -1}, {-1, 0} },
        { {0, 0}, {0, 1}, {-1, 0} }
    };

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new boolean[m][n];
        map = new int[m][n];
        
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(0,0);

        System.out.println(ans);
    }

    static void dfs(int idx, int sum){
        if(idx == n*m){
            ans = Math.max(ans, sum);
            return;
        }

        for(int i =0; i<4; i++){
            if(check(idx, i)){
                int val = cover(idx, i, true);
                dfs(idx+1, sum + val);
                cover(idx, i, false);
            }
        }
        dfs(idx+1, sum);
    }

    static boolean check(int idx, int type){
        int y = idx/n;
        int x = idx%n;
        for(int i =0; i <3; i++){
            int yy = y + dShape[type][i][0];
            int xx = x + dShape[type][i][1];
            if(yy < 0 || yy>= m || xx< 0 || xx>=n || board[yy][xx]) return false;
        }

        return true;
    }

    static int cover(int idx, int type, boolean iVal){
        int y = idx/n;
        int x = idx%n;
        int rVal = map[y][x]; // 중앙은 2배
        for(int i =0; i <3; i++){
            int yy = y + dShape[type][i][0];
            int xx = x + dShape[type][i][1];
            board[yy][xx] = iVal;
            rVal += map[yy][xx];
        }

        return rVal;
    }
}
