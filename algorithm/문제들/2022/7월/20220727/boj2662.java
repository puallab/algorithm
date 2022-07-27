public class boj2662 {
    static int n;
    static boolean flag = false;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dfs(0, new StringBuilder());
    }

    static void dfs(int len, StringBuilder sb){
        if(flag) return;
        if(len == n ){
            System.out.println(sb.toString());
            flag = true;
            return;
        }

        for(int i =1; i<=3; i++){
            if(check(sb.toString() + i)){
                dfs(len+1, sb.append(i));
                sb.setLength(sb.length()-1);
            }
        }

    }

    static boolean check(String s){
        int len = s.length();
        for(int i=1; i<= s.length()/2; i++){
            if(s.substring(len -2*i, len -i).equals(s.substring(len -i))) {
                return false;
            }
        }

        return true;
    }
}
