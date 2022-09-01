public class boj17836 {
    static class Node{
        int y, x, flag;
        public Node(int yy, int xx, int f){
            y= yy;
            x =xx;
            flag = f;
        }

        public Node(){
            this(-1,-1,-1);
        }
    }

    static int n, m, t;
    static int[] dy=  {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};
    static int[][] board;
    static int[][][] vis;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());    
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        t = Integer.parseInt(st.nextToken());

        vis = new int[2][n][m];
        board = new int[n][m];

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<m; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        bfS();
    }

    static void bfS(){
        Queue<Node> q = new LinkedList<>();
        q.add(new Node(0, 0, 0));
        vis[0][0][0] = 0;

        while(!q.isEmpty()){
            Node now = q.poll();

            if(now.y == n-1 && now.x == m-1){
                int ans = vis[now.flag][now.y][now.x];
                if(ans > t) System.out.println("Fail");
                else System.out.println(ans);
                return;
            }

            for(int i=0; i<4; i++){
                int y = now.y + dy[i];
                int x = now.x + dx[i];
                int f = now.flag;

                if(y < 0 || x < 0 || y>= n|| x>=m || vis[f][y][x] > 0) continue;

                if(f == 0){
                    if(board[y][x] == 1) continue;
                    if(board[y][x] == 2) f = 1;
                }

                Node next = new Node(y, x, f);
                q.add(next);
                vis[f][y][x] = vis[now.flag][now.y][now.x] + 1;
                
            }
        }

        System.out.println("Fail");
    }

}
