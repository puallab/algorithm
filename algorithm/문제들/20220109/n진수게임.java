import java.util.*;
class Solution {
    static char[] code= {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A',
                         'B','C','D','E','F'};
    public String solution(int n, int t, int m, int p) {
        StringBuilder arr = new StringBuilder();
        arr.append("00");
        int val =1;
        while(arr.length() <= m*t){
            int k = val;
            StringBuilder temp = new StringBuilder();
            while(k > 0){
                temp.append(code[k%n]);
                k /=n;
            }
            arr.append(temp.reverse());
            val++;
        }
        StringBuilder answer = new StringBuilder();
        int idx = p;
        System.out.println(arr);
        for(int i=0; i<t; i++){
             char target = arr.charAt(idx);
             answer.append(target);
             idx +=m;
        }
        
        return answer.toString();
    }
}

// Integer.toString(int n, int radix) !!!!!!!
class Solution2 {
    public String solution(int n, int t, int m, int p) {
        StringBuilder arr = new StringBuilder();
        StringBuilder answer = new StringBuilder();
        int val =0;
        while(arr.length() <= t*m){
            arr.append(Integer.toString(val++, n).toUpperCase());
        }
        System.out.println(arr);
        int index = p;
        for(int i =0; i<t; i++){
            answer.append(arr.charAt(index-1));
            index += m;
        }
        
        return answer.toString();
    }
}

