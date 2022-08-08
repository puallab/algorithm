public class boj15659 {
    static int n, minValue = Integer.MAX_VALUE, maxValue = Integer.MIN_VALUE;
    static ArrayList<Integer> nums = new ArrayList<>();
    static ArrayList<Integer> opers = new ArrayList<>();
    static int[] oCnt = new int[4];
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            int k = Integer.parseInt(st.nextToken());
            nums.add(k);
        }

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<4; i++){
            oCnt[i] =Integer.parseInt(st.nextToken());
        }

        dfs(0, 0);
        System.out.println(maxValue + "\n" + minValue);
       
    }

    static void dfs(int depth, int idx){
        if(idx == n-1 ){
            int val = getAns();
            minValue = Math.min(val, minValue);
            maxValue = Math.max(val, maxValue);
            return;
        }

        for(int i =0; i<4; i++){
            if(oCnt[i] > 0){
                oCnt[i]--;
                if(i > 1){
                    int a= nums.get(depth);
                    int b = nums.get(depth+1);
                    if(i == 3 && b == 0) {
                        continue;
                    }
                    int k = calc(i, a, b);
                    nums.remove(depth);
                    nums.remove(depth);
                    nums.add(depth, k);

                    dfs(depth, idx+1);

                    nums.remove(depth);
                    nums.add(depth, b);
                    nums.add(depth, a);
                }else{
                    opers.add(i);
                    dfs(depth+1, idx+1);
                    opers.remove(opers.size()-1);
                }
                oCnt[i]++;
            }
        }
    }

    static int calc(int oper, int a, int b){
        switch (oper) {
            case 0:
                return a+b;
            case 1:
                return a-b;
            case 2:
                return a*b;
            case 3:
                return a/b;
        }
        return -1;

    }


    static int getAns(){
        int ret = nums.get(0);
        for(int i =0; i< opers.size(); i++){
            ret = calc(opers.get(i), ret, nums.get(i+1));
        }
        return ret;
    }
}
