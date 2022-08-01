public class boj2026 {
    static int n,k,f;
    static boolean[][] friends;
    static List<Integer> list = new ArrayList<>();
    static boolean flag = false;
    static StringBuilder sb = new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());        

        k = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());
        f = Integer.parseInt(st.nextToken());

        friends = new boolean[n+1][n+1];
    
        for(int i =0; i<f; i++){
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            friends[a][b] = friends[b][a] = true;
        }
        
        dfs(1, 0);

        

        if(!flag){
            sb.append(-1);
        }
        System.out.println(sb.toString());
    }

    static void dfs(int idx, int picked){
        if(flag) {
            
            return;
        }

        if(picked == k){
            flag = true;
            Collections.sort(list);
            for(int i : list){
                sb.append(i+"\n");
            }
            return;
        }

        for(int i= idx; i<=n; i++){
            if(check(i)){
                list.add(i);
                dfs(i+1, picked+1);
                list.remove(list.size()-1);
            }
        }
    }

    static boolean check(int idx){
        for(int i : list){
            if(friends[i][idx] != true){
                return false;
            }
        }
        return true;
    }
