import java.util.ArrayList;

//TODO: implement promotion, en passant

public class Pawn extends Piece {
    private boolean moved = false;

    public Pawn(int row, int col, int color) {
        super(row, col, color);
    }

    public void setMoved(boolean moved) {
        this.moved = moved;
    }

    /*
        Pawns can only move forward in the same column, unless attacking, in which case it can only do so diagonally
        forward. Pawn also contains a variable 'moved', which allows it to move forward twice if false and unimpeded
     */
    @Override
    public ArrayList<int[]> getMoves(Square[][] board) {
        if (!inPlay){ return null; }
        ArrayList<int[]> moves = new ArrayList<>();
        int direction;

        //White moves "bottom to top" so its row is decreasing
        //Black is the opposite
        if (color == 0){
            direction = -1;
        } else {
            direction = 1;
        }

        if (!moved){
            if (!board[row+direction][col].isOccupied() &&
                    !board[row+direction+direction][col].isOccupied()){
                moves.add(new int[]{row, col, row+direction+direction, col});
            }
        }

        if (!board[row+direction][col].isOccupied()){
            moves.add(new int[]{row, col, row+direction, col});
        }
        if (col-1 >=0 && board[row+direction][col-1].isOccupied()){
            if (board[row+direction][col-1].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row+direction, col-1});
            }
        }
        if (col+1 < board.length && board[row+direction][col+1].isOccupied()){
            if (board[row+direction][col+1].getPiece().getColor() != color){
                moves.add(new int[]{row, col, row+direction, col+1});
            }
        }
        return moves;
    }

    public char getName() {
        return 'P';
    }
}
