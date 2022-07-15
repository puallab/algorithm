public class boj16987 {
    static int n, s, ans;
    static int[] arr;
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        s = Integer.parseInt(st.nextToken());

        arr = new int[n];
        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }
        if(s == 0) ans = -1;
        dfs(0,0);
        System.out.println(ans);
    }

    static void dfs(int idx, int sum){

        if(sum == s){
            ans++;
        }

        if(idx == n ) return;

        dfs(idx+1, sum);
        dfs(idx+1, sum + arr[idx]);
    } 
}
