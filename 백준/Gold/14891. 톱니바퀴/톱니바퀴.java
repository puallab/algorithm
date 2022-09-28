import java.util.*;
import java.io.*;

public class Main{
    static class Order{
        int idx, dir;
        public Order(int i, int d){
            idx = i;
            dir = d;
        }
    }
    static char[][] circles= new char[4][8];
    static boolean[] vis;
    static int k, ans;
    static int[] dirs = new int[4];
    static List<Order> orders = new ArrayList<>();
    
    
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        for(int i =0; i<4; i++){
            String s = br.readLine();
            circles[i] = s.toCharArray();
        }
        k = Integer.parseInt(br.readLine());
        for(int i =0; i<k ;i++){
            StringTokenizer st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken())-1;
            int dir = Integer.parseInt(st.nextToken());
            orders.add(new Order(idx, dir));
        }

        System.out.println(simul());
        
    }

    static int simul(){
        for(Order order : orders){
            vis = new boolean[4];
            bfs(order);
            selectDir();
        }
        return getResult();
    }

    static void bfs(Order start){
        Queue<Order> q = new LinkedList<>();
        q.add(start);
        vis[start.idx] = true;
        while(!q.isEmpty()){
            Order now = q.poll();
            dirs[now.idx] = now.dir;

            if(now.idx > 0 && !vis[now.idx-1]){
                if(circles[now.idx-1][2] != circles[now.idx][6]){
                    q.add(new Order(now.idx-1, -now.dir));
                    vis[now.idx-1] = true;
                }
            }

            if(now.idx < 3 && !vis[now.idx+1]){
                if(circles[now.idx][2] != circles[now.idx+1][6]){
                    q.add(new Order(now.idx+1, -now.dir));
                    vis[now.idx+1] = true;
                }
            }

        }
    }
    
    static void selectDir(){
        for(int i =0; i<4; i++){
            if(!vis[i]) continue;
            move(i, dirs[i]);
        }
    }

    static void move(int idx, int d){
        char temp;
        if(d == 1){
            temp = circles[idx][7];
            for(int i=7; i>=1; i--){
                circles[idx][i] = circles[idx][i-1];
            }
            circles[idx][0] = temp;
        }else{
            temp = circles[idx][0];
            for(int i =0; i<7; i++){
                circles[idx][i] = circles[idx][i+1];
            }
            circles[idx][7] = temp;
        }
    }

    static int getResult(){
        int sum = 0;
        for(int i=0; i<4; i++){
            if(circles[i][0] == '1'){
                sum += 1<<i;
            }
        }
        return sum;
    }
}