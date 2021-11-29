import java.util.*;
import java.io.*;
import java.nio.Buffer;

//36분 소요,
public class Main {
 
    static class Rotate{
        public int time;
        public char dir;

        public Rotate(){

        }

        public Rotate(int t, char d){
            this.time = t;
            this.dir = d;
        }
    }

    static class Pair{
        int y;
        int x;
        public Pair(int y, int x){
            this.y= y;
            this.x =x;
        }
    }

    static int n;
    static boolean[][] board, snake_footprints;
    static List<Rotate> list = new ArrayList<>();
    public static void main(String[] args) throws Exception {
        input();
        pro();
    }

    static void input() throws Exception{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());    
        board = new boolean[n][n];
        snake_footprints = new boolean[n][n];    
        int k = Integer.parseInt(br.readLine());
        for(int i =0; i<k; i++){
            String[] st = br.readLine().split(" ");
            int y= Integer.parseInt(st[0])-1;
            int x = Integer.parseInt(st[1])-1;
            board[y][x] = true;
        }

        k = Integer.parseInt(br.readLine());
        for(int i =0; i<k; i++){
            String[] st = br.readLine().split(" ");
            int t = Integer.parseInt(st[0]);
            char d = st[1].charAt(0);
            list.add(new Rotate(t,d));
        }        
    }

    static int pro_rotate(int cdir, char d){
        //현재 방향으로부터 위치 계산.
        if(d == 'D'){
            //오른쪽 회전
            return (cdir+1)%4;

        }else{
            //왼쪽 회전
            return cdir == 0? 3 : cdir-1;
        }

    }

    static void pro(){
        int global_time = 0;
        int index = 0;

        //움직임 -> 오른, 아래, 왼, 위
        int[] dy = {0, 1, 0, -1};
        int[] dx = {1, 0, -1, 0};
        int c_dir = 0; //현재 방향.
        int cy = 0, cx = 0; //현재 위치.
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(0,0));
        snake_footprints[0][0] = true;
        while(true){
            global_time++;

            //현재 뱀 위치에서 머리를 늘린 다음 다음칸 이동
            cy += dy[c_dir]; cx += dx[c_dir];
            
            //범위 넘어가는지 확인.
            if(cy < 0 || cy>=n || cx < 0 || cx>=n) break;
            if(snake_footprints[cy][cx] == true) break;

            //사과 있는지 확인 
            if(board[cy][cx] == false){
                //사과 없으면 마지막에서 발자취없애
                Pair yx = q.poll();
                snake_footprints[yx.y][yx.x] = false;
            }
            board[cy][cx] = false;

            //현재 위치 뱀 자취남기기
            snake_footprints[cy][cx] = true;
            q.add(new Pair(cy,cx));

            //뱀 방향 회전.
            if(index != list.size() && list.get(index).time == global_time){
                c_dir = pro_rotate(c_dir, list.get(index).dir);
                index++;
            } 
            
        }

        System.out.println(global_time);
    }
    
}

