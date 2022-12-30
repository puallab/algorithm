import java.io.*;
import java.util.*;


public class Main{
    static int[][] board = new int[3][3];
    static int zeroCnt, player;
    static List<Integer> list = new ArrayList<>();
    
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 

        for(int i =0; i<3; i++){
            st = new StringTokenizer(br.readLine());
            for(int j=0; j<3; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0){
                    zeroCnt += 1;
                    list.add(i*3+j);
                }
            }
        }
       
        if(zeroCnt % 2 == 1){
            player = 1;
        }else{
            player = 2;
        }
        
        int ans = -dfs(player, list.get(list.size()-1));
        if(ans == 1){
            System.out.println("W");
        }else if(ans == -1){
            System.out.println("L");
        }else{
            System.out.println("D");
        }
        
    }

    static int dfs(int turn, int idx){

        if(isGameOver(idx)){
            return 1;
        }
        if(zeroCnt == 0){
            return 0;
        }

        int result = -2;
        for(int i : list){
            if(result == 1) break;
            int y= i/3, x = i%3;
            if(board[y][x] != 0) continue;
            board[y][x] = turn;
            zeroCnt -= 1;

            result = Math.max(result, dfs(3-turn, i));

            board[y][x] = 0;
            zeroCnt += 1;

        }

        return -result;
    }

    static boolean isGameOver(int idx){
        int y= idx/3, x= idx%3;
        if(board[y][x] == 0) return false;
        
        if(board[0][x] == board[1][x] && board[0][x] == board[2][x]) return true;
        if(board[y][0] == board[y][1] && board[y][0] == board[y][2]) return true;
        if(y == x && board[0][0] == board[1][1] && board[0][0] == board[2][2]) return true;
        if(y+x ==2 && board[0][2] == board[1][1] && board[1][1] == board[2][0]) return true;

        return false;
    }

   
    
}

