class Solution {
    public int solution(int[][] board, int[][] skill) {
        int answer = 0;
        int[][] sumBoard = new int[board.length +2][board[0].length+2];
        for(int i =0; i<skill.length; i++){
            int type = skill[i][0];
            int r1 = skill[i][1]+1;
            int c1 = skill[i][2]+1;
            int r2 = skill[i][3]+1;
            int c2 = skill[i][4]+1;
            int degree = skill[i][5];
            if(type == 1 ) degree = -degree;
            
            sumBoard[r1][c1] += degree;
            sumBoard[r1][c2+1] += -degree;
            sumBoard[r2+1][c1] += -degree;
            sumBoard[r2+1][c2+1] += degree;
        }
        
        for(int i=1; i<sumBoard.length; i++){
            for(int j =1; j<sumBoard[i].length; j++){
                sumBoard[i][j] += sumBoard[i][j-1] + sumBoard[i-1][j] - sumBoard[i-1][j-1]; 
            }
        }
        
        for(int i=1; i<sumBoard.length-1; i++){
            for(int j =1; j<sumBoard[i].length-1; j++){
                if(sumBoard[i][j] + board[i-1][j-1] > 0){
                    answer++;
                }
            }
        }
        
        return answer;
    }
}