public class boj22944 {
    static class Pair{
        int y,x;
        
        public Pair(int yy,int xx){
            y= yy;
            x =xx;
        }

        public Pair(){
            this(0,0);
        }
    }

    static int n, h, d, ans;
    static Pair s,e;
    static List<Pair> list = new ArrayList<>();
    static boolean[] vis;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        h = Integer.parseInt(st.nextToken());
        d = Integer.parseInt(st.nextToken());
        
        for(int i =0; i<n; i++){
            String str = br.readLine();
            for(int j=0; j<n; j++){
                if(str.charAt(j) == 'S'){
                    s = new Pair(i,j);
                }else if (str.charAt(j) == 'E'){
                    e = new Pair(i,j);
                }else if(str.charAt(j) =='U'){
                    list.add(new Pair(i,j));
                }
            }
        }

        ans = n*n+1;
        vis = new boolean[list.size()];
        dfs(s.y, s.x, h, 0, 0);
        if(ans == n*n+1) ans =-1;
        System.out.println(ans);
    }

    static void dfs(int y, int x, int life, int shield, int len){
        int tLen = getLen(y, x, e.y, e.x);
        if( tLen-1 < life+shield){
            ans = Math.min(ans, tLen+len);
            return; 
        }

        for(int i=0; i<list.size(); i++){
            int cLen = getLen(y, x, list.get(i).y, list.get(i).x);
            if(vis[i] || cLen-1 >= shield+life ) continue;
            vis[i] = true;
            if(shield >= cLen){
                dfs(list.get(i).y, list.get(i).x, life, d, len+cLen);
            }else{
                dfs(list.get(i).y, list.get(i).x, life -(cLen-shield) , d, len+cLen);
            }

            vis[i] = false;
        }

    }


    static int getLen(int y, int x, int yy, int xx){
        return Math.abs(y-yy) + Math.abs(x-xx);
    }

}
