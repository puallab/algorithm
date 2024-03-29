public class boj16571 {
    static int[][] board = new int[3][3];
    static List<Integer> list = new ArrayList<>();
    static int player, cnt;
    static int ans;
    static StringBuilder sb =new StringBuilder();
    public static void main(String[] args) throws IOException{
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st; 
        for(int i=0 ;i <3; i++){
            st = new StringTokenizer(br.readLine());  
            for(int j =0; j<3; j++){
                board[i][j] = Integer.parseInt(st.nextToken());
                if(board[i][j] == 0 ){
                    list.add(3*i+j);
                }
            }
        }

        if(list.size() % 2 != 0){
            player = 1;
        }else{
            player = 2;
        }
        cnt = list.size();
        ans = -dfs(player, list.get(list.size()-1), 0); // 가장 중요..!! 항상 return 값은 상대방 입장에서 생각하므로 반대로 나온다.

        if(ans == 1){
            System.out.println("W");
        }else if( ans == -1){
            System.out.println("L");
        }else{
            System.out.println("D");
        }

        
    }

    static int dfs(int turn, int idx, int depth){
        
        if(isGameOver(idx)){
            return 1;
        }

        if(cnt == 0){
            return 0;
        }

        int result =-2;

        for(int i : list){
            if(depth ==1){
                int k=3;
            }
            if(result == 1) break;
            int y = i/3;
            int x = i%3;
            if(board[y][x] == 0){
                board[y][x] = turn;
                cnt -= 1;
                result = Math.max(dfs(3-turn, i, depth+1), result); //최고의 선택에 대한 결과가 들어 있음. 1,0,-1
                cnt += 1;
                board[y][x] = 0;
            }
        }
        
        return -result;
    }

    static boolean isGameOver(int idx){
        int y = idx/3;
        int x = idx%3;
        int k = board[y][x];

        if(k == 0) return false;
        //가로, 세로
        if(board[y][0] == board[y][1] && board[y][0] == board[y][2]) return true;
        if(board[0][x] == board[1][x] && board[0][x] == board[2][x]) return true;
        
        // 좌상 대각선
        if( y+x ==2 &&board[2][0] == board[1][1] && board[0][2] == board[1][1]) return true;

        // 우상 대각선
        if(y == x &&board[0][0] == board[1][1] && board[2][2] == board[1][1]) return true;
        
        return false;
    }


  
}
