import java.io.*;
import java.security.interfaces.RSAKey;
import java.util.*;


public class Main{
    static int[][] board = new int[3][3];
    static List<Integer> list = new ArrayList<>();
    static int player, cnt;
<<<<<<< HEAD
    static int ans = -1;
=======
    static int ans;
    static StringBuilder sb =new StringBuilder();
>>>>>>> a8df62cb5be4906af008d110af7a0f5a8a72ff36
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        for(int i=0 ;i <3; i++){
            st = new StringTokenizer(br.readLine());  
            for(int j =0; j<3; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0 ){
                    list.add(3*i+j);
                }
            }
        }

        if(list.size() % 2 != 0){
            player = 1;
        }else{
            player = 2;
        }
        cnt = list.size();
<<<<<<< HEAD
        dfs(player, list.get(list.size()-1));

        if(ans == 1){
            System.out.println("W");
        }else if( ans == -1){
=======
        ans = dfs(player, list.get(list.size()-1), 0);

        if(ans == -1){
            System.out.println("W");
        }else if( ans == 1){
>>>>>>> a8df62cb5be4906af008d110af7a0f5a8a72ff36
            System.out.println("L");
        }else{
            System.out.println("D");
        }
<<<<<<< HEAD
        
    }

    static int dfs(int turn, int idx){
        
        if(ans == 1){
            return 1;
        }

        if(isGameOver(idx)){
            if(turn != player){
                return 1;
            }else{
                return -1;
            }
        }

        if(cnt == 0){
            ans = 0;
            return 0;
        }

        int max = -2;

        for(int i : list){
            int y = i/3;
            int x = i%3;
            if(board[y][x] == 0){
                board[y][x] = turn;
                cnt -= 1;
                max = Integer.min(max ,dfs(3-turn, i));
                cnt += 1;
                board[y][x] = 0;
            }
        }
        return max;
=======

        //printStrignBuilder();
        
    }

    static int dfs(int turn, int idx, int depth){
        //showBoard(depth, 3-turn);
        if(isGameOver(idx)){
            return 1;
        }

        if(cnt == 0){
            return 0;
        }

        int result =-2;

        for(int i : list){
            if(depth ==1){
                int k=3;
            }
            if(result == 1) break;
            int y = i/3;
            int x = i%3;
            if(board[y][x] == 0){
                board[y][x] = turn;
                cnt -= 1;
                result = Math.max(dfs(3-turn, i, depth+1), result); //최고의 선택에 대한 결과가 들어 있음. 1,0,-1
                cnt += 1;
                board[y][x] = 0;
            }
        }
        
        return -result;
>>>>>>> a8df62cb5be4906af008d110af7a0f5a8a72ff36
    }

    static boolean isGameOver(int idx){
        int y = idx/3;
        int x = idx%3;
        int k = board[y][x];

        if(k == 0) return false;
        //가로, 세로
        if(board[y][0] == board[y][1] && board[y][0] == board[y][2]) return true;
        if(board[0][x] == board[1][x] && board[0][x] == board[2][x]) return true;
<<<<<<< HEAD

        // 좌상 대각선
        if(y+x ==2 && board[2][0] == board[1][1] && board[0][2] == board[1][1]) return true;

        // 우상 대각선
        if(y == x && board[0][0] == board[1][1] && board[2][2] == board[1][1]) return true;
        
        return false;
    }

    static void showBoard(){
        System.out.println("\n");
        for(int i =0; i<3; i++){
            for(int j =0; j<3; j++){
                System.out.print( board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println("\n");
    }
=======
        
        // 좌상 대각선
        if( y+x ==2 &&board[2][0] == board[1][1] && board[0][2] == board[1][1]) return true;

        // 우상 대각선
        if(y == x &&board[0][0] == board[1][1] && board[2][2] == board[1][1]) return true;
        
        return false;
    }

    static void showBoard(int depth, int turn){
       System.out.print("\ndepth =" + depth + " turn = " +turn + "\n");
        for(int i =0; i<3; i++){
            for(int j =0; j<3; j++){
                 System.out.print(board[i][j] + " ");
            }
            System.out.print("\n");
        }
        
    }


    static void saveSb(int depth, int turn){
        sb.append("\ndepth =" + depth + " turn = " +turn + "\n");
        for(int i =0; i<3; i++){
            for(int j =0; j<3; j++){
                sb.append( board[i][j] + " ");
            }
            sb.append("\n");
        }
    }

    static void printStrignBuilder(){
        File file = new File("./result.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.append(sb.toString());
         
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
>>>>>>> a8df62cb5be4906af008d110af7a0f5a8a72ff36
}

