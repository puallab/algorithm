import java.util.*;
import java.io.*;

public class Main{
	static int n;
	static long ans = Long.MAX_VALUE;
	static long[] arr;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		n = Integer.parseInt(br.readLine());
		st = new StringTokenizer(br.readLine());
		arr = new long[n];
		for(int i =0; i<n; i++){
			arr[i] = Long.parseLong(st.nextToken());
		}

		Arrays.sort(arr);

		for(int i =0; i<n; i++){
			for(int j =i+3; j<n; j++){
				ans = Math.min(ans, getGap(i, j));
			}
		}
		System.out.println(ans);
	}

	static long getGap(int p1, int p2){
		int left = 0, right =n-1;
		long val = arr[p1]+arr[p2];
		long ret = Long.MAX_VALUE;
		if(left == p1){
			left += 1;
		}
		if(right == p2){
			right -= 1;
		}

		while(left < right){

			long gap = arr[left] + arr[right];
			ret = Math.min(ret, Math.abs(gap-val));
			if(gap == val) return 0;
			
			if(gap < val){
				left += 1;
			}else{
				right -= 1;
			}

			if(left == p1 || left == p2) left += 1;
			if(right == p1 || right == p2) right -=1;
		}
		return ret;
	}


	
}