import java.util.*;
class Solution {
    static String com;
    static int[] parents = new int[50*50];
    static String[] values = new String[50*50];
    public List<String> solution(String[] commands) {
        init();
        List<String> answer = new ArrayList<>();
        StringTokenizer st;
        for(String command : commands){
            st = new StringTokenizer(command);
            String order = st.nextToken();
            switch(order){
                case "UPDATE":
                    if(st.countTokens() == 3){
                        int r = Integer.parseInt(st.nextToken()) - 1;
                        int c = Integer.parseInt(st.nextToken()) - 1;
                        String value = st.nextToken();
                        update(r, c, value);
                    }else{
                        String a = st.nextToken();
                        String b = st.nextToken();
                        update(a, b);
                    }
                    break;
                case "MERGE":
                    int r1 = Integer.parseInt(st.nextToken()) -1;
                    int c1 = Integer.parseInt(st.nextToken()) -1;
                    int r2 = Integer.parseInt(st.nextToken()) -1;
                    int c2 = Integer.parseInt(st.nextToken()) -1;
                    merge(r1,c1,r2,c2);
                    break;
                case "UNMERGE":
                    int r = Integer.parseInt(st.nextToken()) -1;
                    int c = Integer.parseInt(st.nextToken()) -1;
                    unmerge(r, c);
                    break;
                case "PRINT":
                    r = Integer.parseInt(st.nextToken()) - 1;
                    c = Integer.parseInt(st.nextToken()) - 1;
                    String val = print(r, c);
                    answer.add(val);
                    break;
                default:
                    break;
            }
        }

        return answer;
    }

   static void update(int r, int c, String value){
        int idx = r*50+c;
        int pIdx = find(idx);
        values[pIdx] = new String(value);
    }

    static void update(String a, String b){
        for(int i =0; i<parents.length; i++){
            if(i != parents[i]) continue;
            if(values[i] == null) continue;
            if(!values[i].equals(a)) continue;
            values[i] = new String(b);
        }
    }

    

    static void merge(int r1, int c1, int r2, int c2){
        int aIdx = find(r1*50+c1);
        int bIdx = find(r2*50+c2);
        // 같은 셀인지?
        if(aIdx == bIdx) return;
        
        //a가 값이 없는데 b가 있는 경우
        if(values[aIdx] == null && values[bIdx] != null){
            union(bIdx, aIdx);
            return; 
        }

        //그 외, a가 있거나, 둘다 없거나
        union(aIdx, bIdx);
        return;

    }

    static void unmerge(int r, int c){
        int aIdx = find(r*50 + c);
        String value = values[aIdx];
        List<Integer> list = new ArrayList<>();
        for(int i=0; i< parents.length; i++){
            if(find(i) == aIdx){
                list.add(i);
            }
        }

        for(Integer idx : list){
            parents[idx] = idx;
            values[idx] = null;
        }

        if(value == null) return;;
        values[r*50+c] = new String(value);
    }

    static String print(int r, int c){
        int aIdx = find(r*50 + c);
        if(values[aIdx] ==null){
            return "EMPTY";
        }
        return values[aIdx];
    }

    static void union(int a, int b){
        int pa = find(a);
        int pb = find(b);

        parents[pb] = pa;
    }

    static int find(int a){
        if(a == parents[a]){
            return a;
        }

        return parents[a] = find(parents[a]);
    }

    static void init(){
        for(int i =0; i<2500; i++) parents[i] =i;
        
    }
}