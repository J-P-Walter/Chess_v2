import java.util.ArrayList;

public class Rook extends Piece{
    private char name = 'R';
    public Rook(int row, int col, int color) {
        super(row, col, color);
    }

    /*
        Rooks move on orthogonals, so we check them by using 4 loops that add moves to the list and
        end when a board edge is reached or when another piece occupies the square.
     */
    @Override
    public ArrayList<int[]> getMoves(){
        Board board = Board.getInstance();
        ArrayList<int[]> validMoves = new ArrayList<>();

        //Left
        int left = 1;
        while (col-left >= 0){
            if (!board.getBoard()[row][col - left].isOccupied()){
                validMoves.add(new int[]{row, col, row, col-left});
            }
            else if (board.getBoard()[row][col-left].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row, col-left});
                break;
            }
            else{
                break;
            }
            left++;
        }

        //Right
        int right = 1;
        while (col+right < board.getBoard().length){
            if (!board.getBoard()[row][col + right].isOccupied()){
                validMoves.add(new int[]{row, col, row, col+right});
            }
            else if (board.getBoard()[row][col+right].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row, col+right});
                break;
            }
            else {
                break;
            }
            right++;
        }

        //Up
        int up = 1;
        while (row-up >= 0){
            if (!board.getBoard()[row - up][col].isOccupied()){
                validMoves.add(new int[]{row, col, row-up, col});
            }
            else if (board.getBoard()[row-up][col].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-up, col});
                break;
            }
            else {
                break;
            }
            up++;
        }

        //Down
        int down = 1;
        while (row+down < board.getBoard().length){
            if (!board.getBoard()[row + down][col].isOccupied()){
                validMoves.add(new int[]{row, col, row+down, col});
            }
            else if (board.getBoard()[row+down][col].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+down, col});
                break;
            }
            else {
                break;
            }
            down++;
        }
        return validMoves;
    }

    public char getName() {
        return name;
    }
}
