import java.util.*;
import java.io.*;
class Solution {
    public int solution(int n, int k) {
        int answer = 0;
        String[] arr = change(n,k).split("0");
        
        for(String s : arr){
            if(s.length() == 0 || s == "") continue;
            long v = Long.parseLong(s);
            if(!isPrime(v)) continue;
            answer++;
        }
        
        return answer;
    }
    
    public static String change(int n, int k){
        StringBuilder sb = new StringBuilder();
        
        while(n >= k){
            sb.append(n%k);
            n /= k;
        }
        sb.append(n);
        
        return sb.reverse().toString();
    }
    
    public static boolean isPrime(long value){
//         if(value < 2) return false;
        
//         for(int i = 2; i*i <= value; i++){
//             if(value % i == 0) return false;
//         }
        
//         return true;
        if(value == 0 || value == 1) { return false; }
        for(long i=2; i*i <= value; i++) {
            if(value % i == 0) {
                return false;
            }
        }

        return true;
    }
    
    
}