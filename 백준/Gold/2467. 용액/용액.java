import java.util.*;
import java.io.*;
public class Main{
    static int n;
    static int[] arr;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        n = Integer.parseInt(br.readLine());
        arr = new int[n];

        st = new StringTokenizer(br.readLine());
        for(int i =0; i<n; i++){
            arr[i] = Integer.parseInt(st.nextToken());
        }

        long min = Integer.MAX_VALUE;
        int left = 0, right = arr.length-1;
        int leftIdx =0, rightIdx = 0;
        while(left < right){
            long val = arr[left] + arr[right];
            if(checkAbs(min, val)){
                min =  val;
                leftIdx = left;
                rightIdx = right;
            }

            if(val > 0){
                right--;
            }else if(val < 0){
                left++;
            }else{
                break;
            }
        }

        System.out.println(arr[leftIdx] + " " + arr[rightIdx]);
    }

    static boolean checkAbs(long min, long val){
        return (Math.abs(min) > Math.abs(val));
    }
}