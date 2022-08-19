public class boj5547 {
    static int w, h, ans;
    static int[][] board;

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
        board = new int[w+2][h+2];
        for(int i=1; i<w; i++){
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j<h; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        bfs();
    }

    static void bfs(){
        Queue<Pair> q=  new LinkedList<>();
        q.add(new Pair(0, 0));
        board[0][0] = -1;
        while(!q.isEmpty()){
            Pair now = q.poll();
            if(now.y%2 == 0){

            }else{
                
            }
        }
    }
}
