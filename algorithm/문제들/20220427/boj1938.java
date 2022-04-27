import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class boj1938 {

    static class State{
        int cy, cx, ry, rx, ly, lx;

        public State(int ryy, int rxx, int cyy, int cxx, int lyy, int lxx){
            cy = cyy;
            cx = cxx;
            ry = ryy;
            rx = rxx;
            ly = lyy;
            lx = lxx;
        }

        @Override
        public boolean equals(Object obj) {
            
            return super.equals(obj);
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }

    static int n;
    static int[] dloc = {0,1,2,3,4}; // U, D, L, R, T;
    static String[] board;
    static State dest, state;
    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new String[n];
        for(int i =0; i < n; i++){
            board = br.readLine();
        }
    }

    static void pro() throws Exception{
        state = findLoc('B');
        dest = findLoc('E');

        
    }

    static State findBorE(char target){

        //find B;
        for(int i =0; i<n; i++){
            for(int j=0; j<n; j++){
                if(board[i].charAt(j) == target){

                    //가로 확인
                    if(j < n-2 && board[i].charAt(j+1) == target){
                        return new State(i, j, i, j+1, i, j+2);
                    }
                    else if( i < n-2 && board[i].charAt(i+1) == target){
                        return new State(i, j, i+1, j, i+2, j);
                    }

                }
            }
        }
        return null;
    }
}
