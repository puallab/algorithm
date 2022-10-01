import java.util.*;
import java.io.*;

public class Main{
    static class Dragon{
        int y,x,d,g;
        public Dragon(int xx, int yy, int dd, int gg){
            x =xx;
            y= yy;
            d= dd;
            g =gg;
        }
    }
    static int n, tailY, tailX;
    static int[][] board = new int[101][101];
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static List<Dragon> list = new ArrayList<>();
    static List<Integer> dirs;
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for(int i =0; i<n; i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            tailX= Integer.parseInt(st.nextToken());
            tailY= Integer.parseInt(st.nextToken());
            int d = Integer.parseInt(st.nextToken());
            int g= Integer.parseInt(st.nextToken());

            board[tailY][tailX] = 1;
            dragonCurve(d, 0, g);
            
        }
        System.out.println( findSquare() );

    }

    static void dragonCurve(int dir, int gen, int targetGen){
        dirs = new ArrayList<>();
        while(gen <= targetGen){
            if(gen == 0){
                draw(dir);
                dirs.add(dir);
            }else{
                int size = dirs.size()-1;
                for(int i = size; i >= 0; i--){
                    int nDir = (dirs.get(i)+1)%4;
                    draw(nDir);
                    dirs.add(nDir);
                }
            }
            gen++;
        }
    }

    static void draw(int dir){
        tailY += dy[dir];
        tailX += dx[dir];

        board[tailY][tailX] = 1;
    }

    static int findSquare(){
        int reVal = 0;
        for(int i =0; i<100; i++){
            for(int j =0; j<100; j++){
                if(board[i][j] ==1 && board[i+1][j] == 1 && board[i][j+1] == 1 && board[i+1][j+1] == 1){
                    reVal += 1;
                }
            }
        }
        return reVal;
    }
}