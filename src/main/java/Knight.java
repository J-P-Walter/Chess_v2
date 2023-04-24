import java.util.ArrayList;

public class Knight extends Piece{

    private char name = 'N';
    public Knight(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public ArrayList<int[]> getMoves() {
        Board board = Board.getInstance();
        ArrayList<int[]> validMoves = new ArrayList<>();

        //Up-up-left
        if (row-2 >= 0 && col-1 >= 0){
            if (board.getBoard()[row-2][col-1].isOccupied() == false){
                validMoves.add(new int[]{row, col, row-2, col-1});
            }
            else if (board.getBoard()[row-2][col-1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-2, col-1});
            }
        }

        //Up-up-right
        if (row-2 >= 0 && col+1 < board.getBoard().length){
            if (board.getBoard()[row-2][col+1].isOccupied() == false){
                validMoves.add(new int[]{row, col, row-2, col+1});
            }
            else if (board.getBoard()[row-2][col+1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-2, col+1});
            }
        }

        //Down-down-left
        if (row+2 < board.getBoard().length && col-1 >= 0){
            if (board.getBoard()[row+2][col-1].isOccupied() == false){
                validMoves.add(new int[]{row, col, row+2, col-1});
            }
            else if (board.getBoard()[row+2][col-1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+2, col-1});
            }
        }

        //Down-down-right
        if (row+2 < board.getBoard().length && col+1 < board.getBoard().length){
            if (board.getBoard()[row+2][col+1].isOccupied() == false){
                validMoves.add(new int[]{row, col, row+2, col+1});
            }
            else if (board.getBoard()[row+2][col+1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+2, col+1});
            }
        }

        //Up-left-left
        if (row-1 >= 0 && col-2 >= 0){
            if (board.getBoard()[row-1][col-2].isOccupied() == false){
                validMoves.add(new int[]{row, col, row-1, col-2});
            }
            else if (board.getBoard()[row-1][col-2].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-1, col-2});
            }
        }

        //Up-right-right
        if (row-1 >= 0 && col+2 < board.getBoard().length){
            if (board.getBoard()[row-1][col+2].isOccupied() == false){
                validMoves.add(new int[]{row, col, row-1, col+2});
            }
            else if (board.getBoard()[row-1][col+2].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-1, col+2});
            }
        }

        //Down-left-left
        if (row+1 < board.getBoard().length && col-2 >= 0){
            if (board.getBoard()[row+1][col-2].isOccupied() == false){
                validMoves.add(new int[]{row, col, row+1, col-2});
            }
            else if (board.getBoard()[row+1][col-2].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+1, col-2});
            }
        }

        //Down-right-right
        if (row+1 < board.getBoard().length && col+2 < board.getBoard().length){
            if (board.getBoard()[row+1][col+2].isOccupied() == false){
                validMoves.add(new int[]{row, col, row+1, col+2});
            }
            else if (board.getBoard()[row+1][col+2].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+1, col+2});
            }
        }


        return validMoves;
    }

    public char getName() {
        return name;
    }
}
