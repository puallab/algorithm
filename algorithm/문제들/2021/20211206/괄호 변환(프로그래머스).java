import java.util.*;
/**
 *  쫄지 말고 문제가 요구하는대로 풀면됨.
 */
class Solution {
    
    static boolean isCorrect(String s){
        int k =0;
        for(int i =0; i< s.length(); i++){
            if(s.charAt(i) == '(') {
                k++;
            }else{
                if(k == 0) return false;
                k--;
            }
        }
        return true;
    }
    
    static String makeCorrectString(String s){
        int len = s.length();
        if( len == 0 ) return "";
        
        String u = "" , v = "";
        int len0 = 0;
        for(int i =0; i<s.length(); i++){
            char t = s.charAt(i);
            u += t;
            if(t == '(') len0++;
            else len0--;
            if( len0 == 0){
                v = s.substring(i+1, s.length());
                break;
            }
        }
        
        if(isCorrect(u)) {
            return u+makeCorrectString(v);
        }else{
            String temp = "(" + makeCorrectString(v) + ")";
            String midU = "";
            for(int i =1; i<u.length()-1; i++){
                if(u.charAt(i) == ')') midU += "(";
                else midU += ")";
            }
            return temp + midU;
        }
        
    }
    
    public String solution(String p) {
        String answer = makeCorrectString(p);
        return answer;
    }
}