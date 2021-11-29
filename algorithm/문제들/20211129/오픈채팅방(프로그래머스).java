public class 오픈채팅방(프로그래머스) {
    
}
import java.util.*;
import java.util.*;
class Solution {
    public String[] solution(String[] record) {
        String[] answer = {};
        ArrayList<String> arr = new ArrayList<>();
        HashMap<String, String> map = new HashMap<>();
        for(int i = 0; i<record.length; i++){
            StringTokenizer st = new StringTokenizer(record[i]);
            String inout = st.nextToken();
            String id = st.nextToken();
            if(inout.equals("Enter") || inout.equals("Change")){
                String name = st.nextToken();    
                map.put(id, name);
                if(inout.equals("Enter")){
                    arr.add(inout +" " + id);
                }
            }else{
                arr.add(inout + " " + id);
            }
        }
        
        answer = new String[arr.size()];
        for(int i =0; i< answer.length; i++){
            String[] text = arr.get(i).split(" ");
            
            //System.out.println(text[0]);
            
            if(text[0].equals("Enter")){
               answer[i] =  map.get(text[1])+"님이 들어왔습니다.";
            }else{
               answer[i]  = map.get(text[1])+"님이 나갔습니다.";
            }
        }
        
        return answer;
    }
}