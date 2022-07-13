public class boj10971 {
    static int n, ans = 987654321;

    static int[][] arr;
    static boolean[] vis;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());

        arr = new int[n][n];
        vis = new boolean[n];
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                arr[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i =0; i<n; i++){
            dfs(i, i, 0, 0);
        }
        
        
        System.out.println(ans);
    }

    static void dfs(int start, int now, int idx, int sum){
        if(idx == n && now == start){
            ans = Math.min(ans, sum);
            return;
        }

        for(int i =0; i<n; i++){
            if(arr[now][i] == 0) continue;
            if(vis[i]) continue;
            vis[i] = true;
            dfs(start, i, idx+1, sum+arr[now][i]);
            vis[i] = false;
        }

    }
}
