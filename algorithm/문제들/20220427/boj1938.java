import java.io.*;
import java.util.*;

public class boj1938 {

    static class State {
        int[] y = new int[3], x = new int[3];
        int cnt;
        boolean shape; //  true: ㅡ, false: ㅣ

        public State(int[] yy, int[] xx, boolean flag) {
            for (int i = 0; i < 3; i++) {
                y[i] = yy[i];
                x[i] = xx[i];
            }
            cnt = 0;
            shape = flag;
        }

        @Override
        public boolean equals(Object obj) {

            if(obj instanceof State){ 
                State B = (State) obj; 
                if(this.y[1] != B.y[1] || this.x[1] != B.x[1]) return false;
                if(Arrays.equals(this.y, B.y) && Arrays.equals(this.x, B.x)) return true;
                if(this.y[0] ==B.y[2] && this.y[2] == B.y[0] && this.x[0] ==B.x[2] && this.x[2] == B.x[0]) return true;
            }

            return false;
        }

        @Override
        public int hashCode() {
            int code = y[0]+y[2]*n+ x[0]*n*n + x[2]*n*n*n; 
            return code;
        }
    }

    static int n;
    static int[] dy = { -1, 1, 0, 0, -1, -1, 1, 1 }; // 상, 하, 좌, 우, 좌상, 우상, 좌하, 우하
    static int[] dx = { 0, 0, -1, 1, -1, 1, -1, 1 };
    static String[] board;
    static State dest, state;

    public static void main(String[] args) throws IOException {
        input();
        pro();
    }

    static void input() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());
        board = new String[n];
        for (int i = 0; i < n; i++) {
            board[i] = br.readLine();
        }
    }

    static void pro() {
        state = findLoc('B');
        dest = findLoc('E');

        int ans = bfs();

        System.out.println(ans);

    }

    static State findLoc(char target) {

        // find B;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (board[i].charAt(j) == target) {

                    // 가로 확인
                    if (j < n - 2 && board[i].charAt(j + 1) == target) {
                        return new State(new int[] { i, i, i }, new int[] { j, j + 1, j + 2 }, true);
                    } else if (i < n - 2 && board[i+1].charAt(j) == target) {
                        return new State(new int[] { i, i + 1, i + 2 }, new int[] { j, j, j }, false);
                    }

                }
            }
        }
        return null;
    }

    static int bfs() {
        HashSet<State> vis = new HashSet<>();
        vis.add(state);
        Queue<State> q = new LinkedList<>();
        q.add(state);

        while (!q.isEmpty()) {
            State now = q.poll();
            if (now.equals(dest)) {
                return now.cnt;
            }

            for (int i = 0; i < 5; i++) {
                State cState = validate(now, i);
                if(cState == null) continue;
                if(vis.contains(cState)) continue;
                cState.cnt = now.cnt+1;
                vis.add(cState);
                q.add(cState);
            }
        }
        return 0;
    }

    static State validate(State now, int k){
        State cState = null;

        if(k != 4){
            int[] cy = new int[3];
            int[] cx = new int[3];
            for(int i =0; i<3; i++){
                cy[i] = now.y[i] + dy[k];
                cx[i] = now.x[i] + dx[k];
                if(!isRange(cy[i],cx[i])) return null;
            }

            cState = new State(cy, cx, now.shape);

        }else{
            for(int i =0; i<8; i++){
                int cy = dy[i] + now.y[1];
                int cx = dx[i] + now.x[1];
                if(!isRange(cy, cx)) return null;
            }
            
            // true : -, false :l;
            if(now.shape){
                if(now.x[0] < now.x[1]) cState = new State(new int[]{now.y[0]-1, now.y[1], now.y[2]+1}, new int[]{now.x[0]+1, now.x[1], now.x[2]-1}, false); 
                else cState = new State(new int[]{now.y[0]+1, now.y[1], now.y[2]-1}, new int[]{now.x[0]-1, now.x[1], now.x[2]+1}, false); 
            }else{
                if(now.y[0] < now.y[1]) cState = new State(new int[]{now.y[0]+1, now.y[1], now.y[2]-1}, new int[]{now.x[0]+1, now.x[1], now.x[2]-1}, true);
                else cState = new State(new int[]{now.y[0]-1, now.y[1], now.y[2]+1}, new int[]{now.x[0]+1, now.x[1], now.x[2]-1}, true);
            }
            
        }

        return cState;
    }

    static boolean isRange(int y, int x) {
        return (y >= 0 && y < n && x >= 0 && x < n && board[y].charAt(x) != '1');
    }

}
