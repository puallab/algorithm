import java.util.*;

import javax.imageio.ImageTypeSpecifier;

import java.io.*;

public class Main{

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y = yy;
            x = xx;
        }
    }

    static int n, m, k, point = 0;
    static int[] dy = {0, -1, 0, 1};
    static int[] dx = {1, 0, -1, 0};
    static int[][] map, board;
    static boolean[][] isThere, vis;
    static Pair[] heads, tails;
    static boolean[] dirs;
    static Deque<Pair>[] teams;

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        heads = new Pair[m+1];
        heads[0] = new Pair(-1, -1);

        tails = new Pair[m+1];
        tails[0] = new Pair(-1, -1);

        dirs = new boolean[m+1];
        teams = new LinkedList[m+1];
        for(int i=0; i<=m; i++){
            teams[i] = new LinkedList<>();
        }
        
        map = new int[n][n];
        board = new int[n][n];
        isThere = new boolean[n][n];

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                
            }
        }

        makeMap();
        //showBoard(map, n, n);
        simul();
    }
    
    static void makeMap(){
        vis = new boolean[n][n];
        int idx = 1;
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                if(vis[i][j] || board[i][j] == 0) continue;
                bfs(i,j, idx++);
            }
        }

        for(int i =1; i<heads.length; i++){
            teams[i].add(new Pair(heads[i].y, heads[i].x));
            findTeam(heads[i].y, heads[i].x, i);
            teams[i].add(new Pair(tails[i].y, tails[i].x));
        }

    }

    static void bfs(int y, int x, int idx){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        vis[y][x] = true;
        map[y][x] = idx;
        while(!q.isEmpty()){
            Pair now = q.poll();
            if(board[now.y][now.x] == 1) heads[idx] = now;
            else if(board[now.y][now.x] == 3) tails[idx] = now;
            if(board[now.y][now.x] <4 ) isThere[now.y][now.x] = true;
            for(int i =0; i<4; i++){
                int yy = now.y +dy[i];
                int xx = now.x +dx[i];
                if(!isValid(yy, xx) || vis[yy][xx] || board[yy][xx] ==0 ) continue;
                
                
                map[yy][xx] = idx;
                vis[yy][xx] = true;
                q.add(new Pair(yy, xx));
            }
        }
    }

    static void findTeam(int y, int x, int idx){
        Queue<Pair> q = new LinkedList<>();
        q.add(new Pair(y, x));
        boolean[][] vis = new boolean[n][n];
        vis[y][x] = true;
        while(!q.isEmpty()){
            Pair now = q.poll();
            for(int i =0; i<4; i++){
                int yy = now.y+dy[i];
                int xx = now.x +dx[i];
                if(!isValid(yy, xx) || map[yy][xx] != map[y][x] || vis[yy][xx] || !isThere[yy][xx] || board[yy][xx] != 2 ) continue;
                teams[idx].add(new Pair(yy,xx));
                vis[yy][xx] = true;
                q.add(new Pair(yy, xx));
            }
        } 
    }

    static void simul(){
        for(int i =1; i<=k; i++)
        {
            
            allMove();
            //showBoard(isThere, n, n);
            throwBall(i);
        }

        System.out.println(point);
    }

    static void allMove(){

        for(int i =1; i<teams.length; i++){
            boolean flag = false;
            if(dirs[i]){
                // tail 방향으로
                Pair tail = teams[i].peekLast();
                for(int j=0; j<4; j++){
                    int y = tail.y + dy[j];
                    int x = tail.x + dx[j];
                    if(!isValid(y, x) || isThere[y][x] || map[y][x] != i) continue;
                    // 새로운 꼬리 추가.
                    teams[i].addLast(new Pair(y,x));
                    //빼기
                    Pair head = teams[i].poll();

                    isThere[head.y][head.x] = false;
                    isThere[y][x] = true;
                    flag= true;
                    break;
                }

                if(flag == false){
                    // 빈곳 못찾음
                    Pair head = teams[i].pollFirst();
                    teams[i].addLast(head);
                }

            }else{
                // 머리 방향으로 이동
                Pair head = teams[i].peek();
                for(int j=0; j<4; j++){
                    int y = head.y + dy[j];
                    int x = head.x + dx[j];
                    if(!isValid(y, x) || isThere[y][x] || map[y][x] != i) continue;
                    // 새로운 머리 추가.
                    teams[i].addFirst(new Pair(y,x));

                    //빼기
                    Pair tail = teams[i].pollLast();

                    isThere[tail.y][tail.x] = false;
                    isThere[y][x] = true;
                    flag= true;
                    break;
                }

                if(!flag){
                    //빈 곳을 찾지 못함 -> 꼬리랑 이어져있음
                    Pair tail = teams[i].pollLast();
                    teams[i].addFirst(tail);
                }

            }

        }

    }

    static void throwBall(int round){
        int t = (round-1)/n;
        t =t%4;
        int y =0, x =0;
        if(t == 0){
            y = (round-1)%n;
            x = 0;
        }else if (t == 1){
            y = n-1;
            x = (round-1)%n;
        }else if( t == 2){
            y = n-1-(round-1)%n;
            x = n-1;
        }else if( t == 3){
            y = 0;
            x= n-1-(round-1)%n;
        }

        while(true){
            
            if(!isValid(y, x)) break;
            if(isThere[y][x]){

                int idx = map[y][x];
                int num = 0;
                Iterator it = teams[idx].iterator();
                while(it.hasNext()){
                    num += 1;
                    Pair now = (Pair)it.next();
                    if(now.y == y && now.x == x){
                        if(dirs[idx]){
                            num = teams[idx].size() - num +1;
                        }
                        point += (num*num);
                        dirs[idx] = !dirs[idx];
                        break;
                    }
                    
                }
                
                break;
            }

            y += dy[t];
            x += dx[t];
        }

    }

    static boolean isValid(int y, int x){
        return (y>=0 && x>= 0 && y<n && x<n);
    }

    static void showBoard(int[][] bb, int r, int c){
        System.out.println("\n");
        for(int i =0; i<r; i++){
            for(int j =0; j<c; j++){
                System.out.print(bb[i][j] + " ");
            }
            System.out.println();
        }
    }

    static void showBoard(boolean[][] bb, int r, int c){
        int[][] t= new int[n][n];
        for(int i =0; i<r; i++){
            for(int j =0; j<c; j++){
                if(bb[i][j]){
                    t[i][j] = 2;
                }else{
                    t[i][j] = 0;
                }
            }
        }

        for(int i =1; i<teams.length; i++){
            Pair head = teams[i].peekFirst();
            Pair tail = teams[i].peekLast();
            t[head.y][head.x] = 1;
            t[tail.y][tail.x] = 3;
        }

        showBoard(t, n, n);

    }
}