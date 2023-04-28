import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(int row, int col, int color) {
        super(row, col, color);
    }

    /*
        Bishop moves on diagonals, so we check them by using 4 loops that add moves to the list and
        end when a board edge is reached or when another piece occupies the square.
     */
    @Override
    public ArrayList<int[]> getMoves(Square[][]board){
        if (!inPlay){ return null; }
        ArrayList<int[]> moves = new ArrayList<>();

        //Up-left
        int idx = 1;
        while (row-idx >= 0 && col-idx >= 0){
            Square currSquare = board[row-idx][col-idx];
            if (!currSquare.isOccupied()){
                moves.add(new int[]{row, col, row-idx, col-idx});
            }
            else if (currSquare.getPiece().getColor() != color){
                moves.add(new int[]{row, col, row-idx, col-idx});
                break;
            }
            else{
                break;
            }
            idx++;
        }

        //Up-right
        idx = 1;
        while (row-idx >= 0 && col+idx < board.length){
            Square currSquare = board[row - idx][col + idx];
            if (!currSquare.isOccupied()){
                moves.add(new int[]{row, col, row-idx, col+idx});
            }
            else if (currSquare.getPiece().getColor() != color){
                moves.add(new int[]{row, col, row-idx, col+idx});
                break;
            }
            else{
                break;
            }
            idx++;
        }

        //Down-left
        idx = 1;
        while (row+idx < board.length && col-idx >= 0){
            Square currSquare = board[row + idx][col - idx];
            if (!currSquare.isOccupied()){
                moves.add(new int[]{row, col, row+idx, col-idx});
            }
            else if (currSquare.getPiece().getColor() != color){
                moves.add(new int[]{row, col, row+idx, col-idx});
                break;
            }
            else{
                break;
            }
            idx++;
        }

        //Down-right
        idx = 1;
        while (row+idx < board.length && col+idx < board.length){
            Square currSquare = board[row + idx][col + idx];
            if (!currSquare.isOccupied()){
                moves.add(new int[]{row, col, row+idx, col+idx});
            }
            else if (currSquare.getPiece().getColor() != color){
                moves.add(new int[]{row, col, row+idx, col+idx});
                break;
            }
            else{
                break;
            }
            idx++;
        }
        return moves;
    }

    public char getName() {
        return 'B';
    }
}
