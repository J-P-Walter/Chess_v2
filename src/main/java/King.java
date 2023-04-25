import java.util.ArrayList;

//TODO: implement castling
//TODO: implement isChecked method

public class King extends Piece{
    private char name = 'K';
    public King(int row, int col, int color) { super(row, col, color); }

    /*
        Kings can move both orthogonal and diagonal but only once, simply checks each of the eight squares
     */
    @Override
    public ArrayList<int[]> getMoves(){
        Board board = Board.getInstance();
        ArrayList<int[]> validMoves = new ArrayList<>();

        //Left
        if (col-1 >= 0 && !board.getBoard()[row][col-1].isOccupied()){
            validMoves.add(new int[]{row, col, row, col-1});
        }
        else if (col-1 >= 0 && board.getBoard()[row][col-1].getPiece().getColor() != color){
            validMoves.add(new int[]{row, col, row, col-1});
        }

        //Right
        if (col+1 < board.getBoard().length && !board.getBoard()[row][col+1].isOccupied()){
            validMoves.add(new int[]{row, col, row, col+1});
        }
        else if (col+1 < board.getBoard().length && board.getBoard()[row][col+1].getPiece().getColor() != color){
            validMoves.add(new int[]{row, col, row, col+1});
        }

        //Up
        if (row-1 >= 0 && !board.getBoard()[row-1][col].isOccupied()){
            validMoves.add(new int[]{row, col, row-1, col});
        }
        else if (row-1 >= 0 && board.getBoard()[row-1][col].getPiece().getColor() != color){
            validMoves.add(new int[]{row, col, row-1, col});
        }

        //Down
        if (row+1 < board.getBoard().length && !board.getBoard()[row + 1][col].isOccupied()){
            validMoves.add(new int[]{row, col, row+1, col});
        }
        else if (row+1 < board.getBoard().length && board.getBoard()[row+1][col].getPiece().getColor() != color){
            validMoves.add(new int[]{row, col, row+1, col});
        }

        //Up-left
        if (row-1 >= 0 && col-1 >= 0){
            if (!board.getBoard()[row - 1][col - 1].isOccupied()){
                validMoves.add(new int[]{row, col, row-1, col-1});
            }
            else if (board.getBoard()[row-1][col-1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-1, col-1});
            }
        }

        //Up-right
        if (row-1 >= 0 && col+1 < board.getBoard().length){
            if (!board.getBoard()[row - 1][col + 1].isOccupied()){
                validMoves.add(new int[]{row, col, row-1, col+1});
            }
            else if (board.getBoard()[row-1][col+1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-1, col+1});
            }
        }

        //Down-left
        if (row+1 < board.getBoard().length && col-1 >= 0){
            if (!board.getBoard()[row + 1][col - 1].isOccupied()){
                validMoves.add(new int[]{row, col, row+1, col-1});
            }
            else if (board.getBoard()[row+1][col-1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+1, col-1});
            }
        }

        //Down-right
        if (row+1 < board.getBoard().length && col+1 < board.getBoard().length){
            if (!board.getBoard()[row + 1][col + 1].isOccupied()){
                validMoves.add(new int[]{row, col, row+1, col+1});
            }
            else if (board.getBoard()[row+1][col+1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+1, col+1});
            }
        }
        return validMoves;
    }

    public char getName() {
        return name;
    }
}
