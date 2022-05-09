import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class boj1174 {
    static int n;
    static List<Long> list;
    static HashSet<Long> set = new HashSet<>();
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        dfs(0, 10);
        list = new ArrayList<>(set);
        Collections.sort(list);
        if(n > list.size()) System.out.println(-1);
        else{
            System.out.println(list.get(n-1));
        }
    
        
    }

    static void dfs(long now, int one){
        set.add(now);
        for(int i = 0; i < one; i++){
            dfs(now*10+i, i);
        }
    }
}
