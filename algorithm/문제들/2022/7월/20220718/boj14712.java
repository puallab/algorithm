public class boj14712 {
    static int n, m, ans;
    static boolean[][] board;
    static int[] dy= {-1,-1,0};
    static int[] dx ={-1,0,-1};
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        board = new boolean[n][m];
        for(int i =0; i<n; i++){
            board[i] = new boolean[m];
        }
        dfs(0);
        System.out.println(ans);
    }

    static void dfs(int idx){
        if(idx == n*m){
            ans++;
            return;
        }

        if(check(idx)){
            board[idx/m][idx%m] = true;
            dfs(idx+1);
            board[idx/m][idx%m] = false;
        }

        dfs(idx+1);

    }
    static boolean check(int idx){
        int y = idx/m;
        int x = idx%m;

        if(y ==0 || x ==0) return true;
        if(board[y-1][x-1] && board[y-1][x] && board[y][x-1] ) return false;

        return true;
    }
}
