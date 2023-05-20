import java.util.*;
class Solution {
static int gapPoint =0;
    static int[] ans;
    public int[] solution(int n, int[] info) {

        //피치의 현재 점수 
        int pPoint = 0;
        for(int i =0; i < info.length; i++){
            if(info[i] > 0){
                pPoint += 10-i;
            }
        }

        dfs(n, 0, pPoint, 0, info, new int[11]);
        if(ans == null) ans = new int[]{-1};
        return ans;
    }
    
    static void dfs(int n, int i, int pPoint, int lPoint, int[] peach, int[] lion){
        
        //마지막까지 옴
        if(i == 10){
            lion[10] = n; //남은 화살 다 떄려박기
            if(pPoint<lPoint) calcAns(lion, lPoint-pPoint);
            lion[10] = 0;
            return;
        }

        //화살 다 쏨
        if(n == 0){
            if(pPoint < lPoint) calcAns(lion, lPoint-pPoint);
            return;
        }
        
        //이번판 가져가기  
        if(peach[i] < n){
            lion[i] = peach[i]+1;
            dfs(n-lion[i], i+1, peach[i]>0?pPoint-(10-i):pPoint ,lPoint+10-i , peach, lion);
            lion[i] = 0;
        }
        //이번판 안가져가기
        dfs(n, i+1, pPoint,lPoint, peach, lion);
    }

    static void calcAns(int[] lion, int point){

        //점수 계산 ㅋㄷㅋㄷ
        if(gapPoint > point ) return;
        
        if(gapPoint < point){
            ans = Arrays.copyOf(lion, lion.length);
            gapPoint = point;
            return;
        }

        // 같을 경우 비교 해야해
        for(int i= 10; i>= 0; i--){
            if(ans[i] > lion[i]) return;
            
            if(ans[i] < lion[i]){
                ans = Arrays.copyOf(lion, lion.length);
                return;
            }
        }
    }
}