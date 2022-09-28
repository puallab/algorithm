import java.util.*;
import java.io.*;

public class Main{

    static class Status{
        int cnt;
        int depth;
        boolean flag;

        public Status(int cc, int dd, boolean f){
            cnt = cc;
            depth = dd;
            flag = f;
        }

        public void check(){
            if(flag == false) return;
            if(l <= cnt){
                cnt -= l;
                flag = false;
            }
        }

        public void set(int cc, int dd, boolean f){
            cnt = cc;
            depth = dd;
            flag = f;
        }

    }

    static int n, l, ans;
    static int[][] board;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        l = Integer.parseInt(st.nextToken());
        board = new int[n][n];
        
        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for(int i =0; i<n; i++){
            ans += checking_col(i);
        }

        for(int j =0; j<n; j++){
            ans += checking_row(j);
        }

        System.out.println(ans);
    }

    static int checking_col(int idx){
        
        Status sts = new Status(1, board[idx][0], false);
        for(int x =1; x < n; x++){
            if( Math.abs(sts.depth - board[idx][x]) > 1) return 0;
            if(sts.depth == board[idx][x]){
                sts.set(sts.cnt+1, board[idx][x], sts.flag);
            }else if(sts.depth < board[idx][x]){
                if(sts.flag){
                    if(sts.cnt < 2*l) return 0;
                }else{
                    if(sts.cnt < l) return 0;
                }
                sts.set(1, board[idx][x], false);
            }else{ //sts.depth > board[idx][x]
                if(sts.flag){
                    if(sts.cnt < l) return 0;
                }
                sts.set(1, board[idx][x], true);
            }
        }

        if(sts.flag){
            if(sts.cnt < l) return 0;
        }

        return 1;
    }

    static int checking_row(int idx){
        
        Status sts = new Status(1, board[0][idx], false);
        for(int y =1; y < n; y++){
            if( Math.abs(sts.depth - board[y][idx]) > 1) return 0;
            if(sts.depth == board[y][idx]){
                sts.set(sts.cnt+1, board[y][idx], sts.flag);
            }else if(sts.depth < board[y][idx]){
                if(sts.flag){
                    if(sts.cnt < 2*l) return 0;
                }else{
                    if(sts.cnt < l) return 0;
                }
                sts.set(1, board[y][idx], false);
            }else{ //sts.depth > board[y][idx]
                if(sts.flag){
                    if(sts.cnt < l) return 0;
                }
                sts.set(1, board[y][idx], true);
            }
        }

        if(sts.flag){
            if(sts.cnt < l) return 0;
        }

        return 1;
    }
}