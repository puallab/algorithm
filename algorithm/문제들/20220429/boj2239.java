import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.text.spi.CollatorProvider;
import java.util.ArrayList;
import java.util.List;

public class boj2239 {
    static char[][] board = new char[9][9];
    static boolean[][] colVis = new boolean[9][10];
    static boolean[][] rowVis = new boolean[9][10];
    static boolean[][] sqrVis = new boolean[9][10];
    static List<Integer> list = new ArrayList<>();
    
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i =0; i<9; i++){

            String t=  br.readLine();

            for(int j =0; j<9; j++){
                board[i][j] = t.charAt(j);
                if(board[i][j] != '0'){
                    int val = board[i][j]-'0';
                    colVis[j][val] = true;
                    rowVis[i][val] = true;
                    sqrVis[(i/3)*3 + j/3][val]  = true;
                }
                else list.add( i*9 + j );
            }
        }
    }

    static void pro(){
        backTracking(0);

        for(int i =0; i<9; i++){
            for(int j =0; j<9; j++){
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }

    static boolean  backTracking(int idx){
        if(idx == list.size()){
            return true;
        }

        int i = list.get(idx)/9, j = list.get(idx)%9;
        boolean flag = false;

        for(int n=1; n <= 9; n++){

            if(colVis[j][n] || rowVis[i][n] ||  sqrVis[(i/3)*3 + j/3][n]) continue;

            board[i][j] = n + '0';
            
            flag = backTracking(idx+1);
            if(flag) break;
        }
        
        return flag;
    }

    
}
