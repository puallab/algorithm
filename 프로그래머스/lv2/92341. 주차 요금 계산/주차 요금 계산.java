import java.util.*;
import java.io.*;
class Solution {
    static Map<String, Integer> map = new HashMap<>();
    static Map<String, Integer> totalTime= new HashMap<>();
    public int[] solution(int[] fees, String[] records) {
        int[] answer = {};
        int lastTime = toMinute("23:59");
        
        for(String s : records){
            
            StringTokenizer st = new StringTokenizer(s);
            int time = toMinute(st.nextToken());
            String number = st.nextToken();
            String status = st.nextToken();
            
            
            if(status.equals("IN")){
                map.put(number, time);
                
            }else{
                int from = map.get(number);
                int to = time;
                int diff = to- from;
                totalTime.put(number, totalTime.getOrDefault(number, 0) + diff);
                map.put(number, -1);
            }
        }
        
        //출차 안한거 찾기 및 list에 목록 담기
        List<String> list = new ArrayList<>();
        
        for(Map.Entry<String, Integer> entry : map.entrySet()){
            
            String number = entry.getKey();
            int time = entry.getValue();
            //출차 안했음. 23:59로 출차 계산
            if(time != -1){
                
                int diff = lastTime - time;
                totalTime.put(number, totalTime.getOrDefault(number, 0) + diff);
            }
            list.add(number);
        }
        
        Collections.sort(list);
        
        answer = new int[list.size()];
        
        for(int i =0; i<list.size(); i++){
            int total= totalTime.get(list.get(i));
            
            answer[i]  = calc(total, fees);
        }
        
        return answer;
    }
    
    static int calc(int t, int[] fees){
        int price = fees[1];
        
        if(t <= fees[0]){
            return price;
        }
        
        int overFee = 0;
        if((t-fees[0])%fees[2] != 0){
            overFee = (t-fees[0])/fees[2]+1;
        }else{
            overFee = (t-fees[0])/fees[2];
        }
        
        price += overFee*fees[3];
        
        return price;
    }
    
    static int toMinute(String s){
        StringTokenizer st = new StringTokenizer(s, ":");
        return Integer.parseInt(st.nextToken())*60+Integer.parseInt(st.nextToken());
    }
}