package DSA;

public class Backtracking {

    public static void nQueens(int[][] board,int row,String ans){

        if(row==board.length){
            System.out.println(ans+" ");
            return;
        }

        for(int col=0;col<board.length;col++){
            if(isQueenSafe(board,row,col) == true){
                board[row][col]=1;
                nQueens(board,row+1,ans+row+"-"+col+",");
                board[row][col]=0;
            }
        }
    }

    public static boolean isQueenSafe(int[][] board,int row,int col){
        for(int i=row-1;i>=0;i--){
            if(board[i][col] == 1)
                return false;
        }
        for(int i=row-1,j=col+1;i>=0 && j < board.length;i--,j++){
            if(board[i][j] == 1)
                return false;
        }

        for(int i=row-1,j=col-1;i>=0 && j>=0;i--,j--){
            if(board[i][j] == 1)
                return false;
        }
        return true;
    }

    public static void knightsTour(int[][] chess,int row,int col,int move){
        if(row<0 || col<0 || row >=chess.length || col >=chess.length || chess[row][col] > 0)
            return;
        else if(move==chess.length*chess.length){
            chess[row][col]=move;
            display(chess);
            chess[row][col]=0;
            return;
        }

        chess[row][col]=move;
        knightsTour(chess,row-2,col+1,move+1);
        knightsTour(chess,row-1,col+2,move+1);
        knightsTour(chess,row+1,col+2,move+1);
        knightsTour(chess,row+2,col+1,move+1);
        knightsTour(chess,row+2,col-1,move+1);
        knightsTour(chess,row+1,col-2,move+1);
        knightsTour(chess,row-1,col-2,move+1);
        knightsTour(chess,row-2,col-1,move+1);
        chess[row][col]=0;
    }

    public static void display(int[][] chess){
        for(int i=0;i<chess.length;i++){
            for(int j=0;j<chess.length;j++){
                System.out.print(chess[i][j]+" ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        int[][] board=new int[4][4];
        nQueens(board,0,"");
        System.out.println();

        knightsTour(board,2,2,1);
        System.out.println();
    }
}
