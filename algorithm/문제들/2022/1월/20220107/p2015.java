import java.io.*;
import java.util.*;

public class Main{
    static int n, k;
    static int[] arr;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());
        arr = new int[n+1];
        st = new StringTokenizer(br.readLine());
        Map<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);

        long answer = 0;
        for(int i =0; i<n; i++){
            arr[i+1] = arr[i]+Integer.parseInt(st.nextToken());
            answer += map.getOrDefault(arr[i+1]-k, 0);
            map.put(arr[i+1], map.getOrDefault(arr[i+1],0) + 1);
        }
        
        System.out.println(answer);
    }
}