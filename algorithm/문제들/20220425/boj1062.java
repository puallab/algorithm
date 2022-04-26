import java.util.*;
import java.io.*;
public class boj1062{  
    static int n, k, ans, antic = 1 << 'a'-'a' | 1 << 'n'-'a' | 1 << 't'-'a' | 1 << 'i'-'a' | 1 <<'c'-'a'; //초기 포함된 anta, tica
    static int[] words;
    static int bits;
    public static void main(String[] args) throws Exception {
        input();
        pro();
        //showTest();
	}

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new int[n];

        
        bits |= antic;

        for(int i =0; i< n; i++){
            String s = br.readLine();
            
            for(int j =4; j< s.length()-4; j++){
                words[i] |= 1 << (s.charAt(j) - 'a'); 
                bits |= 1 << (s.charAt(j) - 'a');
            }
            words[i] |= antic;
        }
    }

    static void pro(){
        if(k == 26) ans = n;
        else if(k >= 5 ) dfs(0, 0, antic);
        else ans = 0;
        
        System.out.println(ans);
    }

    static void dfs(int idx, int picked, int cbit){
        if(picked == k-5){
            getAns(cbit);
            return;
        }

        for(int i =idx; i< 26; i++){
            int bit = 1 << i;
            //bits에 포함되어 있고, 이미 뽑은 거엔 포함 x;
            if( (bits&bit) != 0 && (antic&bit) == 0){
                dfs(i+1, picked+1, cbit|bit);
            }
        }
    }

    static void getAns(int bit){
        int cnt = 0;
        
        for(int i=0; i<n; i++){
            if( (bit|words[i]) == bit) cnt++;
        }
        ans = Math.max(ans, cnt);
    }

    static void showTest(){
        System.out.println(Integer.toBinaryString(antic));
    }
}