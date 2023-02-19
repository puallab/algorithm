import java.util.*;
import java.io.*;

public class Main{
	static int n, m, k;
	static long[] arr, tree;
	
	public static void main(String[] args)throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		StringBuilder sb = new StringBuilder();

		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		k = Integer.parseInt(st.nextToken());

		arr = new long[n+1];
		for(int i=1; i<=n; i++){
			arr[i] = Long.parseLong(br.readLine());
		}

		tree = new long[n*4];
		init(1, 1, n);

		for(int i =0; i< m+k; i++){
			st = new StringTokenizer(br.readLine());

			int a = Integer.parseInt(st.nextToken());
			int b = Integer.parseInt(st.nextToken());
			long c = Long.parseLong(st.nextToken());

			if(a == 1){
				long diff = c - arr[b];
				arr[b] = c;
				update(1, 1, n, b, diff);
			}else{
				long val = sum(1, 1, n, b, (int)c);
				sb.append(val + "\n");
			}

		}

		System.out.println(sb.toString());

	}

	static long init(int node, int start, int end){
		if(start == end) return tree[node] = arr[start];

		int mid = (start+end)/2;

		return tree[node] = init(node*2, start, mid) + init(node*2+1, mid+1, end);
	}

	static long sum(int node, int start, int end, int left, int right){
		if(left > end || right < start) return 0;

		if(left <= start && end <= right) return tree[node];

		int mid = (start+end)/2;
		return sum(node*2, start, mid, left, right) + sum(node*2+1, mid+1, end, left, right);
	}

	static void update(int node, int start, int end, int idx, long diff){
		if(idx < start || idx > end){
			return;
		}

		tree[node] += diff;
		if(start == end ) return;

		int mid = (start+end)/2;
		update(node*2, start, mid, idx, diff);
		update(node*2+1, mid+1, end, idx, diff);
	}
}