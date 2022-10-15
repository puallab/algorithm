import java.util.*;
import java.io.*;

public class Main {

    static class Pair {
        int y, x;

        public Pair(int yy, int xx) {
            y = yy;
            x = xx;
        }
    }

    static int n, idx;
    static List<Integer> groupCnt = new ArrayList<>(), groupNum = new ArrayList<>();
    static int[] dy = { 0, 0, 1, -1 };
    static int[] dx = { 1, -1, 0, 0 };
    static int[][] groupAdj;
    static int[][] board, groupBoard;
    static boolean[][] vis;
    static List<Pair> groupPos = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        n = Integer.parseInt(br.readLine());
        board = new int[n][n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < n; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        simul();

    }

    static void simul() {
        int ans = 0;
        for (int i = 0; i < 4; i++) {
            init();
            makeGroup();
            //showBoard(groupBoard, n, n);
            getAdjcnt();
            ans += getArtPoint();
            //showBoard(board, n, n);
            crossRotate();
            //showBoard(board, n, n);
            squreRote(0, 0, n/2);
            //showBoard(board, n, n);
            squreRote(0, n/2+1, n/2);
            //showBoard(board, n, n);
            squreRote(n/2+1, 0, n/2);
            //showBoard(board, n, n);
            squreRote(n/2+1, n/2+1, n/2);
            //showBoard(board, n, n);
        }
        System.out.println(ans);
    }

    static void makeGroup() {
        vis = new boolean[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (vis[i][j])
                    continue;
                groupPos.add(new Pair(i, j));
                bfs(i, j, idx++);
            }
        }
    }

    static void bfs(int y, int x, int idx) {

        int val = board[y][x];
        int cnt = 1;
        vis[y][x] = true;
        groupBoard[y][x] = idx;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));

        while (!q.isEmpty()) {
            Pair now = q.poll();
            for (int i = 0; i < 4; i++) {
                y = now.y + dy[i];
                x = now.x + dx[i];
                if (y < 0 || x < 0 || y >= n || x >= n || vis[y][x])
                    continue;
                if (board[y][x] != val)
                    continue;
                vis[y][x] = true;
                q.add(new Pair(y, x));
                cnt += 1;
                groupBoard[y][x] = idx;
            }
        }

        groupCnt.add(cnt);
        groupNum.add(val);
    }

    static void init() {
        groupCnt = new ArrayList<>();
        groupNum = new ArrayList<>();
        groupPos = new ArrayList<>();
        groupBoard = new int[n][n];
        idx = 0;
    }

    static void getAdjcnt() {
        int size = groupCnt.size();
        groupAdj = new int[size][size];

        for (Pair pos : groupPos) {
            getAdj(pos.y, pos.x);
        }
    }

    static void getAdj(int y, int x) {
        vis = new boolean[n][n];
        int val = groupBoard[y][x];
        vis[y][x] = true;
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));

        while (!q.isEmpty()) {
            Pair now = q.poll();
            for (int i = 0; i < 4; i++) {
                y = now.y + dy[i];
                x = now.x + dx[i];
                if (y < 0 || x < 0 || y >= n || x >= n || vis[y][x])
                    continue;
                if (groupBoard[y][x] != val) {
                    groupAdj[val][groupBoard[y][x]] += 1;
                }else{
                    vis[y][x] = true;
                    q.add(new Pair(y, x));
                }
                
            }
        }

    }

    static int getArtPoint(){
        int size= groupCnt.size();
        int val = 0;
        for(int i =0; i< size; i++){
            for(int j = i+1; j<size; j++ ){
                val += (groupCnt.get(i)+groupCnt.get(j))*groupNum.get(i)*groupNum.get(j)*groupAdj[i][j];
            }
        }
        return val;
    }

    static void crossRotate(){

        int[] temp = new int[n/2];
        int len = n/2;
        for(int i =0; i<len; i++){
            temp[i] = board[i][len];
            board[i][len] = board[len][2*len-i];
            board[len][2*len-i] = board[2*len-i][len];
            board[2*len-i][len] = board[len][i];
            board[len][i] = temp[i];
        }

    }

    static void squreRote(int y, int x, int len){
        while(len >= 2){
            int[] temp = new int[len];
            for(int i =0; i<len; i++){
                temp[i] = board[y][x+i];
                board[y][x+i] = board[y+len-1-i][x];
            }

            for(int i =0; i<len; i++){
                board[y+i][x] = board[y+len-1][x+i];
            }
            
            for(int i =0; i<len; i++){

                board[y+len-1][x+i] = board[y+len-1-i][x+len-1];
            }

            for(int i =0; i<len; i++){
                board[y+i][x+len-1] = temp[i];
            }
            y += 1;
            x += 1;
            len -= 2;
        }
        
    }

    static void showBoard(int[][] bb, int r, int c) {
        System.out.println("\n");
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                System.out.print(bb[i][j] + " ");
            }
            System.out.println();
        }
    }
}