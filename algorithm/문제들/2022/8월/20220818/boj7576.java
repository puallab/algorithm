public class boj7576 {
    static int n, m;
    static int[][] board;
    static int[] dy= {0, 0, -1, 1};
    static int[] dx = {1, -1, 0, 0};
    static int cnt, ans = -1;
    static Queue<Pair> q = new LinkedList<>();

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y= yy;
            x =xx;
        }

        public Pair(){
            this(-1 ,-1);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[n][m];

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0){
                    cnt++;
                }else if (board[i][j] == 1){
                    q.add(new Pair(i,j));
                }
            }
        }

        bfs();
        if(cnt !=0 ) System.out.println(-1);
        else System.out.println(ans);
    }

    static void bfs(){
        
        while(!q.isEmpty()){
            int qsize = q.size();
            ans++;
            while(qsize-- > 0){
                Pair now = q.poll();
                for(int i =0; i<4; i++){
                    int y = dy[i] + now.y, x = dx[i] + now.x;
                    if(y <0 || y>=n || x<0 || x>=m || board[y][x] != 0) continue;
                    q.add(new Pair(y, x));
                    board[y][x] = 1;
                    cnt--;
                }
            }

        }

    }
}
