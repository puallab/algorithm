import java.util.*;


import java.io.*;


public class Main{
    static int n, m, k;
    static int[][] board, energy;
    static int dy[] = {-1, -1, -1, 0, 0, 1, 1, 1};
    static int dx[] = {-1, 0, 1, -1, 1, -1, 0, 1};
    static ArrayList<Integer>[][] tree;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        n = Integer.parseInt(st.nextToken());
        m = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        board = new int[n][n];
        energy = new int[n][n];

        tree = new ArrayList[n][n];

        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                board[i][j] += 5;
                tree[i][j] = new ArrayList<>();
            }
        }

        for(int i =0; i<n; i++){
            st = new StringTokenizer(br.readLine());
            for(int j =0; j<n; j++){
                energy[i][j] = Integer.parseInt(st.nextToken());
            }
        }
        
        for(int i =0; i<m; i++){
            st = new StringTokenizer(br.readLine());
            int y = Integer.parseInt(st.nextToken()) - 1;
            int x = Integer.parseInt(st.nextToken()) - 1;
            int z = Integer.parseInt(st.nextToken());
            tree[y][x].add(z);
        }
        
        
        System.out.println(simul());

    }


    static int simul(){
        int age = 0;
        while(age < k){

            ss();

            fw();

            age++;
        }

        return getTrees();
    }

    static void ss(){
        ArrayList<Integer> list;
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){

                int deadPoint = 0;
                list = new ArrayList<>();
                Collections.sort(tree[i][j]);
                
                for(int t = 0; t< tree[i][j].size(); t++){
                    int value = tree[i][j].get(t);
                    if(value <= board[i][j]){
                        list.add(value+1);
                        board[i][j] -= value;
                    }
                    else{
                        deadPoint += value/2;
                    }
                }

                tree[i][j] = list;
                board[i][j] += deadPoint;

            }
        }
    }
    
    static void fw(){
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                for(int t = 0; t<tree[i][j].size(); t++){
                    int val = tree[i][j].get(t);

                    if(val % 5 == 0){
                        spread(i, j);
                    }
                }
                
                board[i][j] += energy[i][j];
            }
        }
    }

    static void spread(int y, int x){
        for(int  i=0 ;i<8; i++){
            int yy = y + dy[i];
            int xx = x + dx[i];
            if(yy < 0 || yy >= n || xx< 0 || xx>= n) continue;
            tree[yy][xx].add(1);
        }
    }
    
    static int getTrees(){
        int val= 0;
        for(int i =0; i<n; i++){
            for(int j =0; j<n; j++){
                val += tree[i][j].size();
            }
        }
        return val;
    }
}