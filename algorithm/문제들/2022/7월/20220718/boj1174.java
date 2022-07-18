public class boj1174 {
    static int n;
    static List<Long> list;
    static Set<Long> set=  new HashSet<>();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        dfs(0L, 10);
        list = new ArrayList<>(set);
        Collections.sort(list);
        if(n > list.size()) System.out.println(-1);
        else System.out.println(list.get(n-1));
    }

    static void dfs(Long value, int units){
        set.add(value);
        for(int i =0; i<units; i++){
            dfs(value*10 + i, i);
        }
    }
}
