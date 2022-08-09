public class boj2606 {
    static int n, t, ans;
    static boolean[][] board;
    static boolean[] vis;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        t = Integer.parseInt(br.readLine());
        board = new boolean[n+1][n+1];
        vis = new boolean[n+1];
        for(int i =0; i<t; i++){
            st = new StringTokenizer(br.readLine()); 
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            board[a][b] = true;
            board[b][a] = true;
        }
        
        bfs();
        System.out.println(ans-1);
    }

    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        vis[1] = true;

        while(!q.isEmpty()){
            ans += 1;
            int now = q.poll();
            for(int i = 1; i<n+1; i++){
                if(board[now][i] == true && !vis[i]){
                    vis[i] = true;
                    q.add(i);
                }
            }

        }
    }
}
