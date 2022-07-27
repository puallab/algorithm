public class boj1062{
    static int n, k, alpha, ans, antic =  1<<'a'-'a' | 1 <<'t'-'a' | 1<< 'i'-'a' | 1 <<'c'-'a' | 1<<'n'-'a';
    static int[] nbits;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        nbits = new int[n];
        for(int i =0; i<n; i++){
            String str= br.readLine();
            for(char c : str.substring(4, str.length()-4).toCharArray()){
                if(antic == (antic| (1 << c-'a'))) continue;
                alpha |= 1 << c-'a';
                nbits[i] |= 1 << c-'a';
            }
        }

        dfs(antic, 0, 5);
        System.out.println(ans);
    }

    static void dfs(int bits, int idx, int picked){
        if(picked == k){
            ans = Math.max(ans, checkCanRead(bits));
            return;
        }

        for(int i = idx; i < 26; i++){
            if( (bits | (1 << i)) == bits) continue;
            dfs(bits| (1<<i) , i, picked+1);
        }
    }

    static int checkCanRead(int bits){
        int val = 0;
        for(int i =0; i< n; i++){
            if( bits == (bits | nbits[i]))  val++;
        }

        return val;
    }
}