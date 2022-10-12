import java.util.*;
import java.io.*;

public class Main {

    static class Node {
        int y, x, d = -1;

        public Node(int yy, int xx) {
            y = yy;
            x = xx;
        }

        public Node(int yy, int xx, int dd) {
            y = yy;
            x = xx;
            d = dd;
        }
    }

    static int r, c, k, w;
    static int[] dy = { 0, 0, 0, -1, 1 };
    static int[] dx = { 0, 1, -1, 0, 0 };
    static int[][] board, temp;
    static boolean[][][] wall;
    static List<Node> area = new ArrayList<>();
    static List<Node> machines = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[r][c];
        wall = new boolean[r][c][5];

        for (int i = 0; i < r; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < c; j++) {
                int val = Integer.parseInt(st.nextToken());
                if (val == 5) {
                    area.add(new Node(i, j));
                } else if (val > 0) {
                    machines.add(new Node(i, j, val));
                }
            }
        }

        w = Integer.parseInt(br.readLine());
        for (int i = 0; i < w; i++) {
            st = new StringTokenizer(br.readLine());
            
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int t = Integer.parseInt(st.nextToken());

            if (t == 0) {
                wall[y][x][3] = true;
                if (y > 0)
                    wall[y - 1][x][4] = true;
            } else {
                wall[y][x][1] = true;
                if (x + 1 < c)
                    wall[y][x + 1][2] = true;
            }
        }

        simul();

    }

    static void simul() {
        int ans = 0;
        while (ans < 101) {

            // showBoard(board, r, c);
            turnOn();
            //showBoard(board, r, c);
            divideTemp();
            //showBoard(board, r, c);
            downOutside();

            ans++;

            //showBoard(board, r, c);
            if (isOver())
                break;
        }

        System.out.println(ans);
    }

    static void turnOn() {

        for (Node machine : machines) {
            spreadOn(machine.y, machine.x, machine.d);
            // showBoard(board, r, c);
        }
    }

    static void spreadOn(int y, int x, int d) {

        temp = new int[r][c];
        Queue<Node> q = new LinkedList<>();

        y += dy[d];
        x += dx[d];

        q.add(new Node(y, x));
        int depth = 5;
        temp[y][x] = depth;
        while (!q.isEmpty()) {
            if (--depth == 0)
                break;
            int qsize = q.size();
            while (qsize-- > 0) {
                Node now = q.poll();
                int yy = now.y + dy[d];
                int xx = now.x + dx[d];
                // 앞방향
                if (isValid(yy, xx) && !wall[now.y][now.x][d] && temp[yy][xx] == 0) {
                    q.add(new Node(yy, xx));
                    temp[yy][xx] = depth;
                }

                if (d < 3) {
                    if (isValid(now.y - 1, now.x) && isValid(now.y - 1, now.x + dx[d])
                            && !wall[now.y][now.x][3] && !wall[now.y - 1][now.x][d]
                            && temp[now.y - 1][now.x + dx[d]] == 0) {

                        q.add(new Node(now.y - 1, now.x + dx[d]));
                        temp[now.y - 1][now.x + dx[d]] = depth;
                    }

                    if (isValid(now.y + 1, now.x) && isValid(now.y + 1, now.x + dx[d])
                            && !wall[now.y][now.x][4] && !wall[now.y + 1][now.x][d]
                            && temp[now.y + 1][now.x + dx[d]] == 0) {

                        q.add(new Node(now.y + 1, now.x + dx[d]));
                        temp[now.y + 1][now.x + dx[d]] = depth;
                    }
                } else {

                    if (isValid(now.y, now.x - 1) && isValid(now.y + dy[d], now.x - 1)
                            && !wall[now.y][now.x][2] && !wall[now.y][now.x - 1][d]
                            && temp[now.y + dy[d]][now.x - 1] == 0) {

                        q.add(new Node(now.y + dy[d], now.x - 1));
                        temp[now.y + dy[d]][now.x - 1] = depth;
                    }

                    if (isValid(now.y, now.x + 1) && isValid(now.y + dy[d], now.x + 1)
                            && !wall[now.y][now.x][1] && !wall[now.y][now.x + 1][d]
                            && temp[now.y + dy[d]][now.x + 1] == 0) {

                        q.add(new Node(now.y + dy[d], now.x + 1));
                        temp[now.y + dy[d]][now.x + 1] = depth;
                    }

                }

            }

        }

        //showBoard(temp, r, c);
        addBoard(temp, board);
    }

    static void addBoard(int[][] from, int[][] to) {
        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                to[i][j] += from[i][j];
            }
        }
    }

    static void divideTemp() {

        temp = new int[r][c];

        for (int i = 0; i < r; i++) {
            for (int j = 0; j < c; j++) {
                
                /* 
                for (int k = 1; k <= 4; k++) {
                    int y = dy[k] + i;
                    int x = dx[k] + j;
                    if (!isValid(y, x) || wall[i][j][k])
                        continue;
                    int gap = Math.abs(board[i][j] - board[y][x])/4;
                    if (board[i][j] > board[y][x]) {
                        temp[i][j] -= gap;
                    } else if (board[i][j] < board[y][x]) {
                        temp[i][j] += gap;
                    }
                }
                */
                // 좌우
                if (j + 1 < c && wall[i][j][1] == false) {
                    int gap = Math.abs(board[i][j] - board[i][j + 1]) / 4;

                    if (board[i][j] > board[i][j + 1]) {
                        temp[i][j] -= gap;
                        temp[i][j + 1] += gap;

                    } else if (board[i][j] < board[i][j + 1]) {
                        temp[i][j + 1] -= gap;
                        temp[i][j] += gap;

                    }
                }

                // 상하
                if (i + 1 < r && wall[i][j][4] == false) {
                    int gap = Math.abs(board[i][j] - board[i + 1][j]) / 4;

                    if (board[i][j] > board[i + 1][j]) {
                        temp[i][j] -= gap;
                        temp[i + 1][j] += gap;
                    } else if (board[i][j] < board[i + 1][j]) {
                        temp[i + 1][j] -= gap;
                        temp[i][j] += gap;
                    }
                }
            }
        }

        addBoard(temp, board);
    }

    static void downOutside() {

        for(int y= 0; y<r-1; y++){
            if(board[y][0] > 0 ){
                board[y][0] -=1;;
            }
        }

        for(int x = 0; x<c-1; x++){
            if(board[r-1][x] >0) board[r-1][x] -= 1;
        }

        for(int y= 1; y<r; y++){
            if(board[y][c-1] > 0) board[y][c-1] -= 1;
        }

        for(int x = 1; x<c; x++){
            if(board[0][x] > 0) board[0][x] -= 1;
        }


    }

    static boolean isOver() {
        boolean flag = true;
        for (Node node : area) {
            if (board[node.y][node.x] < k) {
                flag = false;
                break;
            }
        }

        return flag;
    }

    static boolean isValid(int y, int x) {
        return (y >= 0 && x >= 0 && y < r && x < c);
    }

    static void showBoard(int[][] bb, int rr, int cc) {
        System.out.println("\n");
        for (int i = 0; i < rr; i++) {
            for (int j = 0; j < cc; j++) {
                System.out.print(bb[i][j] + " ");
            }
            System.out.println();
        }
    }
}