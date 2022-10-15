import java.util.Scanner;

class Pair {
	int first, second;

	public Pair(int first, int second) {
		this.first = first;
		this.second = second;
	}
}

class Tuple {
	int first, second, third;

	public Tuple(int first, int second, int third) {
		this.first = first;
		this.second = second;
		this.third = third;
	}
}

public class Main {
    public static final Pair BLANK = new Pair(-1, -1);
    public static final Pair TAGGER = new Pair(-2, -2);
    public static final int MAX_N = 4;
    public static final int DIR_NUM = 8;
    
    public static int n = 4;
    
    public static Pair[][] board = new Pair[MAX_N][MAX_N];
    
    // 문제에서 주어진 순서대로
    // 방향을 정의합니다.
    // ↑, ↖, ←, ↙, ↓, ↘, →, ↗ 
    public static int[] dx = new int[]{-1, -1,  0,  1, 1, 1, 0, -1};
    public static int[] dy = new int[]{ 0, -1, -1, -1, 0, 1, 1,  1};
    
    public static int maxScore;
    
    public static boolean inRange(int x, int y) {
        return 0 <= x && x < n && 0 <= y && y < n;
    }
    
    // 도둑말이 이동할 수 있는 곳인지를 판단합니다.
    // 격자 안이면서, 술래가 없어야 합니다.
    public static boolean thiefCanGo(int x, int y) {
        return inRange(x, y) && board[x][y] != TAGGER;
    }
    
    // 술래가 이동할 수 있는 곳인지를 판단합니다.
    // 격자 안이면서, 도둑말이 있어야만 합니다.
    public static boolean taggerCanGo(int x, int y) {
        return inRange(x, y) && board[x][y] != BLANK;
    }
    
    public static boolean done(int x, int y, int d) {
        // 현재 위치에도 한 곳이라도 갈 수 있는지 확인합니다.
        // 존재한다면, 아직 게임은 끝나지 않았습니다.
        for(int dist = 1; dist <= n; dist++) {
            int nx = x + dx[d] * dist, ny = y + dy[d] * dist;
            if(taggerCanGo(nx, ny))
                return false;
        }
        
        return true;
    }
    
    public static Tuple getNext(int x, int y, int moveDir) {
        // 45'씩 8번 회전해보면서 최초로 이동 가능한 곳으로 움직입니다.
        for(int rotateNum = 0; rotateNum < 8; rotateNum++) {
            int adjustedDir = (moveDir + rotateNum) % 8;
            int nextX = x + dx[adjustedDir];
            int nextY = y + dy[adjustedDir];
            if(thiefCanGo(nextX, nextY))
                return new Tuple(nextX, nextY, adjustedDir);
        }
        // 이동이 불가능하다면 현재 위치, 현재 방향 그대로 유지되어야합니다.
        return new Tuple(x, y, moveDir);
    }
    
    public static void swap(int x, int y, int nextX, int nextY) {
        Pair tempPiece = board[x][y];
        board[x][y] = board[nextX][nextY];
        board[nextX][nextY] = tempPiece;
    }
    
    public static void move(int targetNum) {
        for(int x = 0; x < n; x++)
            for(int y = 0; y < n; y++) {
                int pieceNum = board[x][y].first;
                int moveDir = board[x][y].second;
                if(pieceNum == targetNum) {
                    // 이동해야할 위치와 바라보게 될 방향을 구합니다.
                    Tuple next = getNext(x, y, moveDir);
                    int nextX = next.first;
                    int nextY = next.second;
                    int nextDir = next.third;
                    // 현재 말의 방향을 바꿔준 뒤, 두 말의 위치를 교환합니다.
                    board[x][y] = new Pair(pieceNum, nextDir);
                    swap(x, y, nextX, nextY);
                    return;
                }
            }
    }
    
    // 모든 도둑말들을 한번씩 움직입니다.
    public static void moveAll() {
        for(int i = 1; i <= n * n; i++)
            move(i);
    }
    
    // 현재 술래말의 위치가 (x, y), 
    // 바라보고 있는 방향이 d이고
    // 지금까지 얻은 점수가 score일때
    // 탐색을 계속 진행하는 함수입니다.
    public static void searchMaxScore(int x, int y, int d, int score) {
        // 더 이상 움직일 곳이 없다면
        // 답을 갱신하고 퇴각합니다.
        if(done(x, y, d))  {
            maxScore = Math.max(maxScore, score);
            return;
        }
        
        // 현재 턴에 움직일 수 있는 곳을 전부 탐색합니다.
        for(int dist = 1; dist <= n; dist++) {
            int nx = x + dx[d] * dist, ny = y + dy[d] * dist;
            // 술래가 이동 할 수 없는 위치라면, 패스합니다.
            if(!taggerCanGo(nx, ny))
                continue;
            
            // 더 탐색을 진행한 이후, 초기 상태로 다시 만들기 위해
            // temp 배열에 현재 board 상태를 저장해놓습니다.
            Pair[][] temp = new Pair[MAX_N][MAX_N];
            
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    temp[i][j] = board[i][j];
            
            // 해당 위치의 도둑말을 잡고
            int extraScore = board[nx][ny].first;
            int nextDir = board[nx][ny].second;
            board[nx][ny] = TAGGER;
            board[x][y] = BLANK;
            
            // 모든 도둑말을 움직입니다.
            moveAll();
            
            // 그 다음 탐색을 진행합니다. 
            searchMaxScore(nx, ny, nextDir, score + extraScore);
            
            // 퇴각시 다시 이전 board의 값을 넣어줍니다.
            for(int i = 0; i < n; i++)
                for(int j = 0; j < n; j++)
                    board[i][j] = temp[i][j];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++) {
                int p = sc.nextInt();
                int d = sc.nextInt();
                board[i][j] = new Pair(p, d - 1);
            }
        
        // 처음 (0, 0) 도둑말을 잡고, 모든 도둑말이 이동한 다음에 시작합니다.
        int initScore = board[0][0].first;
        int initDir = board[0][0].second; 
        board[0][0] = TAGGER;
        
        moveAll();
        
        searchMaxScore(0, 0, initDir, initScore);
        System.out.print(maxScore);
    }
}