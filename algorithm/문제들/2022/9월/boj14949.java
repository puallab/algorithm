public class boj14949 {
    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y = yy;
            x = xx;
        }

        public Pair(){
            this(-1, -1);
        }
    }

    static int n, m;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] board;
    static int[][] dist;
    static boolean[][] vis;
    static Pair start;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());    
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        
        board = new int[n][m];
        dist = new int[n][m];
        vis = new boolean[n][m];
        
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 2){
                    start = new Pair(i, j);
                }
            }
        }
        
        find();
        show();
    }

    static void find(){
        Queue<Pair> q = new LinkedList<>();
        q.add(start);

        vis[start.y][start.x] = true;
        
        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i =0; i<4; i++){
                int y = dy[i] + now.y;
                int x = dx[i] + now.x;
                if(y <0 || x< 0|| y>=n || x>=m || vis[y][x] || board[y][x] == 0) continue;
                vis[y][x] = true;
                dist[y][x] = dist[now.y][now.x] + 1;
                q.add(new Pair(y,x ));
            }
        }
    }
    
    static void show(){
        StringBuilder sb= new StringBuilder();
        for(int i=0; i<n; i++){
            for(int j =0; j<m; j++){
                if(board[i][j] == 1 && dist[i][j] == 0){
                    dist[i][j] = -1;
                }
                sb.append(dist[i][j] + " ");
            }
            
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
