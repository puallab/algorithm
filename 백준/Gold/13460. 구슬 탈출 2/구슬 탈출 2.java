import java.io.*;
import java.util.*;


public class Main{
    
    static class Ball{
        int y, x;
        public Ball(int yy, int xx){
            y =yy;
            x =xx;
        }

        public void init(int yy, int xx){
            y = yy;
            x = xx;
        }

    }

    static int n, m;
    static int initRy, initRx;
    static int initBy, initBx;
    static int dfsCnt = 0;
    static int ans = 11;
    static String[] board;
    static int[] sDirect = new int[10];
    static int[] dy = {0, 0, 1, 0, -1};  // 좌,하,우,상
    static int[] dx = {0, -1, 0, 1, 0};
    static Ball red, blue;
    

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());

        board = new String[n];

        for(int i =0; i<n; i++){
            board[i]  = br.readLine();
            for(int j =0; j<m; j++){
                if(board[i].charAt(j) == 'R'){
                    red = new Ball(i, j);
                }
                if(board[i].charAt(j) == 'B'){
                    blue = new Ball(i, j);
                }
            }
        }

        initRy = red.y; initRx = red.x;
        initBy = blue.y; initBx = blue.x;

        dfs(0);
        if(ans >= 11) ans = -1;
        System.out.println(ans);
    }

    /**
     *  최대 10회까지 기울일 수 있음.
     *  기울이는 방법 뽑아놓고 해당 횟수 안에 돌릴 수 있는지 확인.
     *  sDirects 배열에 가능한 기울이는 방법을 저장함.
     *  방향 (1: 좌, 2: 하, 3: 우, 4: 상)
     */
    static void dfs(int idx){
        if(idx == 10){
            
            /* test DFS */
            //showDFS();
            //dfsCnt++;

            // init ball location
            red.init(initRy, initRx);
            blue.init(initBy, initBx);

            int result = selectDirect();
            if(result > 0) ans = Math.min(result, ans); 

            return;
        }

        for(int i =1; i<=4; i++){
            if(idx > 0 && i == sDirect[idx-1]) continue;
            sDirect[idx] = i;
            dfs(idx+1);
            sDirect[idx] = -1;
        }
    }

    /**
     * sDirect 배열을 기준으로 순차적으로 상자를 기울인다.
     * 방향별로 먼저 기울이는 구슬의 우선순위가 정해진다.
     * 기울이는 방향과 가까운 구슬을 먼저 굴림.
     * @return > 0 : 빨간 구슬만 탈출 할 수 있는 기울이기 횟수, -1 : 해당 방법엔 탈출 불가 or 파란 구슬이 탈출
     */
    static int selectDirect(){
        
        for(int i = 0; i< 10; i++){
            int priority= -1;
            int rResult, bResult;

            switch(sDirect[i]){
                case 1:
                    if(red.x <= blue.x){
                        priority = 1;
                    }
                    break;
                case 2:
                    if(red.y >= blue.y){
                        priority = 1;
                    }
                    break;
                case 3:
                    if(red.x >= blue.x){
                        priority = 1;
                    }
                    break;
                case 4:
                    if(red.y <= blue.y){
                        priority = 1;
                    }
                    break;
            }

            if(priority == 1){
                rResult = move(sDirect[i], red);
                bResult = move(sDirect[i], blue);
            }else{
                bResult = move(sDirect[i], blue);
                rResult = move(sDirect[i], red);
            }
            
            if(bResult == 1) return -1; //파란 구슬이 구멍을 통해 빠져나옴.
            if(rResult == 1) return i+1;
        }

        return -1; //10회 이내에 기울이지 못함
    }

    /**
     * 구슬을 굴릴 수 있는 만큼 최대한 굴린다.
     * 벽이나 다른 구슬을 만나면 굴리는 것을 멈춘다.
     * @param dir 굴린 방향
     * @param ball 굴릴 구슬 객체
     * @return 1 : 구멍을 통해 구슬이 나옴, 0: 구멍을 통해 나오지 못함
     */
    static int move(int dir, Ball ball){
        
        while(true){
            ball.y += dy[dir];
            ball.x += dx[dir];

            //O를 만나면 return 1, 
            if(board[ball.y].charAt(ball.x) == 'O') {
                ball.y = 0;
                ball.x = 0;
                return 1;
            }
            if(board[ball.y].charAt(ball.x) == '#' ) break;
            if(isSameLocation()) break;
        }

        ball.y -= dy[dir];
        ball.x -= dx[dir];

        return 0;
    }

    /**
     * 동일 위치에 두 개의 구슬의 존재 여부.
     * @return 두 개의 구슬이 같은 위치에 있으면 true, 아니면 false
     */
    static boolean isSameLocation(){
        return (red.y == blue.y && red.x == blue.x);
    }
    
    static void showDFS(){
        for(int i =0; i< 10; i++){
            System.out.print(sDirect[i] + " ");
        }
        System.out.println();
    }

   
}

