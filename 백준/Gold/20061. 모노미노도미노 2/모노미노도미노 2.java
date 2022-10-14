import java.util.*;
import java.io.*;

public class Main{
    static int n, ans = 0;
	static int[][] blocks;
	static int[][] board = new int[10][10];
    
	public static void main(String[] args)  throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        
		
		
		blocks = new int[n][3];
		for(int i =0; i<n; i++) {
            st = new StringTokenizer(br.readLine());
			int t = Integer.parseInt(st.nextToken());
			int y = Integer.parseInt(st.nextToken());
			int x = Integer.parseInt(st.nextToken());
			blocks[i][0] = t;
			blocks[i][1] = y;
			blocks[i][2] = x;
		}
		simul();
        System.out.println(ans);
        System.out.println(getAns());
	}
	
	
	static void simul() {
		for(int i =1; i<=n; i++) {
			
			int t = blocks[i-1][0];
			int y = blocks[i-1][1];
			int x = blocks[i-1][2];
			
            // board[y][x] = i;
            // if(t == 2)  board[y][x+1] = i;
            // else if(t == 3) board[y+1][x] =i;

            gravityDown(y, x, t);
            gravityRight(y, x, t);
            //showBoard(board, 10, 10);
			checkBingo();
            //showBoard(board, 10, 10);
            checkArea();
            //showBoard(board, 10, 10);
            

		}
	}

    static void gravityDown(int y, int x, int t){

        int k = findPos(y, x, true);
        if(t == 2){
            k = Math.min(k, findPos(y, x+1, true));
            board[k-1][x] = board[k-1][x+1] = 1;
        }else if(t == 3){
            board[k-1][x] = board[k-2][x] = 1;
        }else{
            board[k-1][x] = 1;
        }

    }

    static void gravityRight(int y, int x, int t){
        int k = findPos(y, x, false);
        if(t == 1){
            board[y][k-1] = 1;
        }else if(t ==2){
            board[y][k-1] = board[y][k-2] = 1;
        }else{
            k = Math.min(k, findPos(y+1, x, false));
            board[y][k-1] = board[y+1][k-1] = 1;
        }
    }
	
	
	
	static int findPos(int y1, int x1, boolean flag) {
		
		if(flag) {
			while(true) {
				
				if(y1 == 10 || board[y1][x1] != 0) {
					break;
				}
				
				y1 += 1;
			}
			return y1;
		}else {
			while(true) {
				
				if(x1 == 10 || board[y1][x1] != 0) {
					break;
				}
				
				x1 += 1;
			}
			return x1;
		}
		
	}
	
	static int checkBingo() {
		
        for(int j =6; j<10; j++){
            boolean flag = true;
            for(int i=0; i<4; i++){
                if(board[j][i] == 0){
                    flag = false;
                    break;
                }
            }

            if(flag){
                ans += 1;
                for(int k = j; k >=4; k--){
                    for(int i =0; i<4; i++){
                        board[k][i] = board[k-1][i];
                    }
                }
            }

        }

        for(int x = 6; x<10; x++){
            boolean flag = true;
            for(int y = 0; y<4; y++){
                if(board[y][x] == 0 ){
                    flag = false;
                    break;
                }
            }

            if(flag){
                ans += 1;
                for(int k =x; k>=4; k--){
                    for(int y=0; y<4; y++){
                        board[y][k] = board[y][k-1];
                    }
                }
            }
        }

        return ans;
	}
	
	static void checkArea() {
		point:for(int i=4; i<=5; i++){
            for(int j=0; j<4; j++){
                if(board[i][j] != 0){
                    shiftDown();
                    if(i== 4){
                        shiftDown();
                    }    
                    break point;
                }
            }
        }

        point:for(int i=4; i<=5; i++){
            for(int j=0; j<4; j++){
                if(board[j][i] != 0){
                    shiftRight();
                    if(i== 4){
                        shiftRight();
                    }    
                    break point;
                }
            }
        }
	}

    static void shiftDown(){

        for(int i =9; i>= 4; i--){
            for(int j =0; j<4; j++){
                board[i][j] = board[i-1][j];
            }
        }

    }

    static void shiftRight(){
        for(int i =9; i>=4; i--){
            for(int j =0; j<4; j++){
                board[j][i] = board[j][i-1];
            }
        }
    }


    static int getAns(){
        int val = 0;
        for(int i =4; i<10; i++){
            for(int j =0; j<4; j++){
                if(board[i][j] != 0) val += 1;
                if(board[j][i] != 0) val += 1;
            }
        }
        return val;
    }

    static void showBoard(int[][] bb, int r, int c){
        System.out.println("\n");
        for(int i =0; i<r; i++){
            for(int j =0; j<c; j++){
                System.out.print(bb[i][j] + " ");
            }
            System.out.println();
        }
    }

}