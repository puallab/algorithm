import java.util.*;

import javax.swing.tree.VariableHeightLayoutCache;

import java.io.*;
import java.time.temporal.Temporal;

public class Main{
	static int n, ans = Integer.MIN_VALUE;
	static char[] opers;
	static int[] nums;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		String s= br.readLine();
		opers = new char[n/2];
		nums = new int[n/2+1];

		for(int i =0; i<n; i++){
			if(i%2 == 0){
				nums[i/2] = s.charAt(i)-'0';
			}else{
				opers[i/2] = s.charAt(i);
			}
		}

		for(int i =0; i<=opers.length/2; i++){
			dfs(0, 0, i, new int[i]);
		}

		System.out.println(ans);

    }

	static void dfs(int idx, int picked, int n, int[] index){
		if(picked == n){

			ans = Math.max(ans, getCalc(index));
			return;
		}

		for(int i =idx; i<opers.length; i++){
			if(picked > 0 && index[picked-1] == i-1) continue;
			index[picked] = i;
			dfs(i+1, picked+1, n, index);
			index[picked] = -1;
		}
	}

	static int getCalc(int[] index){
		boolean[] isUsed = new boolean[opers.length];
		int[] tempNum = new int[nums.length];
		for(int i =0; i<tempNum.length; i++){
			tempNum[i] = nums[i];
		}
		
		//우선순위 먼저 계산하기
		for(int i :index){
			isUsed[i] = true;
			int ret = calc(tempNum[i], tempNum[i+1], opers[i]);
			tempNum[i] = ret;
		}

		//순서대로 계산
		for(int i =0; i<opers.length; i++){
			if(isUsed[i]){
				tempNum[i+1] = tempNum[i];
			}else{
				tempNum[i+1] = calc(tempNum[i], tempNum[i+1], opers[i]);
			}
		}
		return tempNum[opers.length];
	}

	static int calc(int a, int b, char op){
		int val =0;
		switch (op) {
			case '+':
				val = a + b;
				break;
			case '-':
				val = a - b;
				break;
			case '*':
				val = a * b;
				break;
		}
		return val;
	}
}