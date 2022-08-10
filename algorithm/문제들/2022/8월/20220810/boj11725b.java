public class boj11725b {
    static int n;
    static int[] parents;
    static ArrayList<Integer>[] board;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        board = new ArrayList[n+1];
        for(int i=0; i<n+1; i++){
            board[i] = new ArrayList<>();
        }
        parents = new int[n+1];
        
        for(int i=0; i<n-1; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int a= Integer.parseInt(st.nextToken());
            int b= Integer.parseInt(st.nextToken());
            board[a].add(b);
            board[b].add(a);
        }

        parents[1] = -1;
        bfs();
        for(int i =2; i<n+1; i++){
            System.out.println(parents[i]);
        }
    }


    static void bfs(){
        Queue<Integer> q = new LinkedList<>();
        q.add(1);
        parents[1] = -1;

        while(!q.isEmpty()){
            int from = q.poll();
            for(int i : board[from]){
                if(parents[i] == 0){
                    q.add(i);
                    parents[i] = from;
                }
            }
        }

      
    }
}
