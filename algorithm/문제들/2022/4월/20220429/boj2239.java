import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.spi.CollatorProvider;
import java.util.ArrayList;
import java.util.List;

public class boj2239 {
    static int[][] board = new int[9][9];
    static boolean[][] colVis = new boolean[9][10];
    static boolean[][] rowVis = new boolean[9][10];
    static boolean[][] sqrVis = new boolean[9][10];
    static List<Integer> list = new ArrayList<>();
    static boolean flag = false;
    
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i =0; i<9; i++){

            String t=  br.readLine();

            for(int j =0; j<9; j++){
                
                board[i][j] = t.charAt(j) -'0';
                
                colVis[j][board[i][j]] = true;
                rowVis[i][board[i][j]] = true;
                sqrVis[(i/3)*3 + j/3][board[i][j]]  = true;
                
            }
        }
    }

    static void pro(){
        backTracking(0);
    }

    static void  backTracking(int idx){
        
        if(flag) return;
        if(idx == 81 ){
            flag = true;

            showAns();
            return;
        }

        int i = idx/9;
        int j = idx%9;
        if(board[i][j] != 0) backTracking(idx+1);
        else{
            for(int n=1; n <= 9; n++){

                int k = (i/3)*3 + j/3;
                if(colVis[j][n] || rowVis[i][n] ||  sqrVis[k][n]) continue;
    
                board[i][j] = n;
                colVis[j][n] = rowVis[i][n] = sqrVis[k][n] = true;
                backTracking(idx+1);
                
                colVis[j][n] = rowVis[i][n] = sqrVis[k][n] = false;
                board[i][j] = 0;
            }
        }
    }

    static void showAns(){

        System.out.println();
        System.out.println();

        for(int i =0; i<9; i++){
            for(int j =0; j<9; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
