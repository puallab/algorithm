public class boj16918 {
    static int r, c, n;
    static int[][] board;
    static int[] dy = {0, 0, 1, -1};
    static int[] dx = {1, -1, 0, 0};

    static class Pair{
        int y, x;
        public Pair(int yy, int xx){
            y =yy;
            x =xx;
        }

        public Pair(){
            this(0, 0);
        }
    }

    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        r = Integer.parseInt(st.nextToken());
        c = Integer.parseInt(st.nextToken());
        n = Integer.parseInt(st.nextToken());

        board = new int[r][c];

        for(int i =0; i<r ;i++){
           String k = br.readLine();
           for(int j =0; j<c; j++){
                if(k.charAt(j) !='O'){
                    board[i][j] = -1;
                } 
           }
        }

        simulate();
        printAns();
    }

    static void simulate(){
        int t = 1;
        while(++t <= n){
            if(t%2 == 1 ){
                bomb(t-3);
            }else{
                install(t);
            }
            
        }
    }

    static void bomb(int time){
        for(int i =0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == time){
                    board[i][j] = -1;
                    for(int k=0; k<4; k++){
                        int y = i+dy[k], x= j+dx[k];
                        if(y <0 || y>=r || x<0|| x>=c || board[y][x] == time) continue;
                        board[y][x] = -1;
                    }
                }
            }
        }
    }

    static void install(int time){
        for(int i =0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == -1){
                    board[i][j]= time;
                }
            }
        }
    }

    static void printAns(){
        StringBuilder sb = new StringBuilder();

        for(int i =0; i<r; i++){
            for(int j=0; j<c; j++){
                if(board[i][j] == -1){
                    sb.append(".");
                }else{
                    sb.append("O");
                }
            }
            sb.append("\n");
        }

        System.out.println(sb.toString());
    }
}
