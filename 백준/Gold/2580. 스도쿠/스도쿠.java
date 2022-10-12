import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static boolean[][] rowVis, colVis, areaVis;
    static boolean flag = false;
    static int[][] board = new int[9][9];
    public static void main(String[] args) throws Exception{
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        rowVis = new boolean[9][10];
        colVis = new boolean[9][10];
        areaVis = new boolean[9][10];
        for(int i =0; i<9; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            for(int j =0; j<9; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                colVis[i][board[i][j]] = rowVis[j][board[i][j]] = areaVis[getIdx(i, j)][board[i][j]] = true;
            }
        }
    }

    static void pro(){
        dfs(0);
    }

    static void dfs(int num){
        if(flag) return;
        if(num == 81){
            showBoard();
            flag = true;
            return;
        }

        int y = num/9;
        int x = num%9;
        int idx = getIdx(y, x);
        
        if(board[y][x] != 0 ) dfs(num+1);
        else{
            for(int i =1; i<10; i++){
                if(colVis[y][i] || rowVis[x][i] || areaVis[idx][i]) continue;
                colVis[y][i] = rowVis[x][i] = areaVis[idx][i] = true;
                board[y][x] = i;
                dfs(num+1);
                board[y][x] = 0;
                colVis[y][i] = rowVis[x][i] = areaVis[idx][i] = false; 
            }
        }
        
    }

    static int getIdx(int y, int x){
        return (y/3)*3+x/3;
    }

    static void showBoard(){
        for(int i =0; i<9; i++){
            for(int j =0; j<9; j++){
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }
}
