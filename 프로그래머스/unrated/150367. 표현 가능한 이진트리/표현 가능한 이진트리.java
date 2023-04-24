import java.util.*;
import java.io.*;
class Solution {
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i =0; i<numbers.length; i++){
            
            String s= Long.toBinaryString(numbers[i]);
            
            int len = s.length();
            int k =1; //2진수 자리수, number보다 큰 2진수 
            for(k = 1; 1<<k < len ; k++); 
            
            //length = 7
            // k = 3, (1<<1) ->  
            // (1<<k) - 1 -> 총 트리의 노드 개수
            // 3층 
            
            //추가해야할 0의 개수 세기
            int additional = (1<<k)-1-len;
            int rootIdx = 1<<(k-1);
            // 1<<k -> 전체 노드의 개수 +1
            // 1<<(k-1) 전체 노드의 개수 +1/ 2 /2
            if(numbers[i] == 2) answer[i] =1;
            else if(numbers[i] == 8) answer[i] =1;
            else if(numbers[i] == 128) answer[i] = 1;
            else answer[i] = check(s, additional, rootIdx/2);
        }
        
        return answer;
    }
    
    static int check(String s, int zero, int depth){
        
        StringBuilder sb = new StringBuilder();
        for(int i =0; i<= zero; i++){
                sb.append("0");
        }
            
        s = sb.toString() + s;
        
        //System.out.println(s);
        
        if(s.charAt(s.length()/2) == '0') return 0;
        return dfs(s, s.length()/2, false, depth) ? 1 : 0;
    }
    
    static boolean dfs(String s, int idx, boolean isNull, int depth){
        
        //부모가 0인데 자식이 1인지 확인
        if(isNull){
            if(s.charAt(idx) == '1') return false;
        }
        
        //리프노드인지?
        if(depth == 0) return true;
        
        //현재 상태 확인.
        if(s.charAt(idx) == '0') isNull = true;
        
        return dfs(s, idx-depth, isNull, depth/2)&&dfs(s, idx+depth, isNull, depth/2);
    }
}