public class boj7569 {
    static int n, m, h;
    static int[][][] board;
    static int[] dy= {0, 0, -1, 1, 0, 0};
    static int[] dx = {1, -1, 0, 0, 0, 0};
    static int[] dz= {0, 0, 0, 0, 1, -1};
    static int cnt, ans = -1;
    static Queue<Pair> q = new LinkedList<>();

    static class Pair{
        int y, x, z;
        public Pair(int zz, int yy, int xx){
            y= yy;
            x =xx;
            z =zz;
        }

        public Pair(){
            this(-1 ,-1, -1);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        m = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());

        board = new int[h][n][m];

        for(int z =0 ; z<h; z++){
            for(int y=0; y<n; y++){
                st = new StringTokenizer(br.readLine());
                for(int x =0; x<m; x++){
                    int k = Integer.parseInt(st.nextToken());
                    board[z][y][x] = k;
                    if(k == 1){
                        q.add(new Pair(z, y, x));
                    }else if(k == 0){
                        cnt++;
                    }
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
                
                for(int i =0; i<6; i++){
                    int z = now.z + dz[i];
                    int y = now.y + dy[i];
                    int x = now.x + dx[i];
                    if(z <0 || z>=h || y<0 || y>=n || x<0 || x>=m || board[z][y][x] !=0) continue;
                    q.add(new Pair(z,y,x));
                    board[z][y][x] = 1;
                    cnt--;
                }
            }
        }

    }
}
