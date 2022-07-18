public class boj14888 {
    static int n, _min = Integer.MAX_VALUE, _max = Integer.MIN_VALUE;
    static int[] A, oper;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        A = new int[n];
        oper = new int[4];

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            A[i] = Integer.parseInt(st.nextToken());
        }

        st= new StringTokenizer(br.readLine());
        for(int i =0; i<4; i++){
            oper[i] = Integer.parseInt(st.nextToken());
        }

        dfs(0, A[0]);
       System.out.println(_max + "\n" + _min);
    }

    static void dfs(int idx, int sum){
        if(idx == n-1){
            _min = Math.min(_min, sum);
            _max = Math.max(_max, sum);
            return;
        }

        for(int i=0; i<4; i++){
            if(oper[i] > 0){
                oper[i]--;
                dfs(idx+1,  calc(sum, i, idx));
                oper[i]++;
            }
        } 
    }

    static int calc(int sum, int oper, int idx){
        int val =0;
        switch (oper) {
            case 0:
                val =  sum + A[idx+1];
                break;
            case 1:
                val = sum - A[idx+1];
                break;
            case 2:
                val = sum*A[idx+1];
                break;
            case 3:
                val = sum/A[idx+1];    
                break;
        }

        return val;
    }
}
