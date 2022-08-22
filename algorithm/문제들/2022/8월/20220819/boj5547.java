public class boj5547 {
    static int w, h, ans;
    static int[][] board;
    static int[] dy ={0, 0, 1, -1, 1, -1};
    static int[] dx = {1, -1, 0, 0, 0, 0};

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        w = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        board = new int[h+2][w+2];
        for(int i=1; i<=h; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<=w; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();

        System.out.println(ans);
    }

    static void bfs(){
        Queue<Pair> q=  new LinkedList<>();
        q.add(new Pair(0, 0));
        board[0][0] = -1;
        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i =0; i<6; i++){
                int y = now.y + dy[i];
                int x= now.x + dx[i];
                if(i > 3){
                    if(now.y%2 == 0){
                        x = now.x -1;
                    }else{
                        x = now.x +1;
                    }   
                }

                if(y <0 || y> h+1 || x< 0 || x> w+1 || board[y][x] == -1) continue;
                if(board[y][x] == 1) {
                    ans++;
                    continue;
                }
                board[y][x] = -1;
                q.add(new Pair(y, x));
                
            }
            
        }
    }
}
