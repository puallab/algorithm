public class boj13549 {
    static int n, k;
    static boolean[] vis;
    static int[] dist;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());    
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        vis = new boolean[100001];
        dist = new int[100001];
        bfs(n);
    }

    static void bfs(int start){
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        vis[start] = true;
        dist[start] =0;

        while(!q.isEmpty()){
            int from = q.poll();

            if(from == k){
                System.out.println(dist[from]);
                return;
            }
            
            if(from *2 < 100001 && !vis[from*2]){
                q.add(from*2);
                vis[from*2] = true;
                dist[from*2] = dist[from];
            }

            if(from-1 >= 0 && !vis[from-1]){
                q.add(from-1);
                vis[from-1] = true;
                dist[from-1] = dist[from] + 1;
            }

            if(from+1 < 100001 && !vis[from+1]){
                q.add(from+1);
                vis[from+1] = true;
                dist[from+1] = dist[from] + 1;
            }

        }
    }
}
