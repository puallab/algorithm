import java.util.*;
import java.util.Map.Entry;
/**
 *  2개의 string의 조합을 각각의 맵에 저장하여 관리한다. 이때 맵을 각각 m1, m2라함
 *  m1의 원소들을 하나씩 m2에 존재하는지 비교한다.
 *  비교하는 원소가 m2에 존재하지 않다면 합집합의 값을 비교하는 원소의 개수만큼 증가시켜준다.
 *  m2가 해당 원소를 갖고 있다면 교집합과 합집합에 min과 max값을 더해준다. 이때 m2에서 해당 원소를 제거해준다 
 * 
 *  m1에 모든 원소를 끝내면 m2엔 m2-m1만 남아있게 되므로 합집합에 m2의 원소 각각의 개수를 더해준다.
 */
class Solution {
    public static void makeMap(String str1, HashMap<String,Integer> map){
        for(int i =0; i<str1.length()-1; i++){
            if(str1.charAt(i) < 'A' || str1.charAt(i) > 'Z') continue;
            if(str1.charAt(i+1) < 'A' || str1.charAt(i+1) > 'Z') continue;
            
            String s = str1.substring(i, i+2);
            if(map.containsKey(s)){
                map.put(s, map.get(s) +1);
            }else map.put(s, 1);
        }
    }
    public int solution(String str1, String str2) {
        double answer = 0;
        HashMap<String, Integer> map1 = new HashMap<>();
        HashMap<String, Integer> map2 = new HashMap<>();       
        str1 = str1.toUpperCase();
        str2 = str2.toUpperCase();
        
        makeMap(str1, map1);
        makeMap(str2, map2);
        if(map1.size() == 0 && map2.size() == 0) return 65536;
        if(map1.size() == 0 || map2.size() == 0 ) return 0;
        
        double cross = 0;
        double union = 0;
        for(Entry<String, Integer> entry : map1.entrySet()){
            String key = entry.getKey();
            int value = entry.getValue();
            
            if(map2.containsKey(key)){
                //교집합.
                int value2 = map2.get(key);
                union += Math.max(value, value2);
                cross += Math.min(value, value2);
                map2.remove(key);
            }else{
                //합집합
                union += value;
            }
            
        }
        
        for(Entry<String, Integer> entry : map2.entrySet()){
            union += entry.getValue();
        }
        
        answer = cross/union*65536;
        
        return (int)answer;
    }
}