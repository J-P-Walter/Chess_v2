import java.util.ArrayList;

public class Knight extends Piece{
    public Knight(int row, int col, int color) {
        super(row, col, color);
    }

    /*
        Knight moves in "L" shape, simply checks the 8 possible squares for valid moves
     */
    @Override
    public ArrayList<int[]> getMoves(Square[][] board) {
        if (!inPlay){ return null; }
        ArrayList<int[]> moves = new ArrayList<>();

        //Up-up-left
        if (row-2 >= 0 && col-1 >= 0){
            if (!board[row - 2][col - 1].isOccupied()){
                moves.add(new int[]{row, col, row-2, col-1});
            }
            else if (board[row-2][col-1].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row-2, col-1});
            }
        }

        //Up-up-right
        if (row-2 >= 0 && col+1 < board.length){
            if (!board[row - 2][col + 1].isOccupied()){
                moves.add(new int[]{row, col, row-2, col+1});
            }
            else if (board[row-2][col+1].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row-2, col+1});
            }
        }

        //Down-down-left
        if (row+2 < board.length && col-1 >= 0){
            if (!board[row + 2][col - 1].isOccupied()){
                moves.add(new int[]{row, col, row+2, col-1});
            }
            else if (board[row+2][col-1].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row+2, col-1});
            }
        }

        //Down-down-right
        if (row+2 < board.length && col+1 < board.length){
            if (!board[row + 2][col + 1].isOccupied()){
                moves.add(new int[]{row, col, row+2, col+1});
            }
            else if (board[row+2][col+1].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row+2, col+1});
            }
        }

        //Up-left-left
        if (row-1 >= 0 && col-2 >= 0){
            if (!board[row - 1][col - 2].isOccupied()){
                moves.add(new int[]{row, col, row-1, col-2});
            }
            else if (board[row-1][col-2].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row-1, col-2});
            }
        }

        //Up-right-right
        if (row-1 >= 0 && col+2 < board.length){
            if (!board[row - 1][col + 2].isOccupied()){
                moves.add(new int[]{row, col, row-1, col+2});
            }
            else if (board[row-1][col+2].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row-1, col+2});
            }
        }

        //Down-left-left
        if (row+1 < board.length && col-2 >= 0){
            if (!board[row + 1][col - 2].isOccupied()){
                moves.add(new int[]{row, col, row+1, col-2});
            }
            else if (board[row+1][col-2].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row+1, col-2});
            }
        }

        //Down-right-right
        if (row+1 < board.length && col+2 < board.length){
            if (!board[row + 1][col + 2].isOccupied()){
                moves.add(new int[]{row, col, row+1, col+2});
            }
            else if (board[row+1][col+2].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row+1, col+2});
            }
        }
        return moves;
    }

    public char getName() {
        return 'N';
    }
}
