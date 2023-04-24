import java.util.ArrayList;

public class Bishop extends Piece{

    private char name = 'B';
    public Bishop(int row, int col, int color) {
        super(row, col, color);
    }

    @Override
    public ArrayList<int[]> getMoves(){
        Board board = Board.getInstance();
        ArrayList<int[]> validMoves = new ArrayList<>();

        //Up-left
        int idx = 1;
        while (row-idx >= 0 && col-idx >= 0){
            if (board.getBoard()[row-idx][col-idx].isOccupied() == false){
                validMoves.add(new int[]{row, col, row-idx, col-idx});
            }
            else if (board.getBoard()[row-idx][col-idx].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-idx, col-idx});
                break;
            }
            else{
                break;
            }
            idx++;
        }

        //Up-right
        idx = 1;
        while (row-idx >= 0 && col+idx < board.getBoard().length){
            if (board.getBoard()[row-idx][col+idx].isOccupied() == false){
                validMoves.add(new int[]{row, col, row-idx, col+idx});
            }
            else if (board.getBoard()[row-idx][col+idx].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-idx, col+idx});
                break;
            }
            else{
                break;
            }
            idx++;
        }

        //Down-left
        idx = 1;
        while (row+idx < board.getBoard().length && col-idx >= 0){
            if (board.getBoard()[row+idx][col-idx].isOccupied() == false){
                validMoves.add(new int[]{row, col, row+idx, col-idx});
            }
            else if (board.getBoard()[row+idx][col-idx].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+idx, col-idx});
                break;
            }
            else{
                break;
            }
            idx++;
        }

        //Down-right
        idx = 1;
        while (row+idx < board.getBoard().length && col+idx < board.getBoard().length){
            if (board.getBoard()[row+idx][col+idx].isOccupied() == false){
                validMoves.add(new int[]{row, col, row+idx, col+idx});
            }
            else if (board.getBoard()[row+idx][col+idx].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+idx, col+idx});
                break;
            }
            else{
                break;
            }
            idx++;
        }
        return validMoves;
    }

    public char getName() {
        return name;
    }
}
