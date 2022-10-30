import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Main {
    static int n;
    static boolean flag = false;
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
    }

    static void pro(){
        dfs("");
    }

    static void dfs(String s){
        
        if(s.length() == n){
            System.out.println(s);
            flag = true;
            return;
        }

        for(int i =1; i<=3; i++){
            if(!isValid(s+i)) continue;
            dfs(s+i);
            if(flag) return;
        }
    }

    static boolean isValid(String s){
        int L = s.length();
        for(int i =1; i<=L/2; i++ ){
            if( s.substring(L-i, L).equals( s.substring(L-2*i, L-i) ) ) return false; 
        }
        return true;
    }
}
