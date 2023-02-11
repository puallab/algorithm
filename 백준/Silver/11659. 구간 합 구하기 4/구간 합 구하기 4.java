import java.util.*;
import java.io.*;
public class Main{
	static int n, m;
	static long[] arr;
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		arr = new long[n+1];
		st = new StringTokenizer(br.readLine());
		for(int i =1; i<=n; i++){
			arr[i] = arr[i-1] + Integer.parseInt(st.nextToken()); 
		}

		for(int i =0; i<m; i++){
			st = new StringTokenizer(br.readLine());
			int a = Integer.parseInt(st.nextToken())-1;
			int b = Integer.parseInt(st.nextToken())-1;
			long val = arr[b+1] - arr[a];
			sb.append(val+"\n");
		}

		System.out.println(sb.toString());
	}
}