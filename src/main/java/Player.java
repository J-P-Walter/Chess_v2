import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private final int color;

    private ArrayList<Piece> pieces = new ArrayList<>();
    private ArrayList<int[]> validMoves = new ArrayList<>();

    public ArrayList<int[]> getValidMoves(Square[][] board){

        return validMoves;
    }

    King king;

    public Player(int color){
        this.color = color;
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
    }
    public void addPiece(Piece piece){
        pieces.add(piece);
    }

    public void placePieces(Square[][] board){
        for (Piece piece : pieces){
            board[piece.getRow()][piece.getCol()].putPiece(piece);
        }
    }
    public King getKing(){
        return king;
    }
    public void setKing(King king){
        this.king = king;
    }

    public int getColor() {
        return color;
    }


    public void movePiece(int[] move, Square[][] board){
        int oldRow = move[0], oldCol = move[1], newRow = move[2], newCol = move[3];
        Piece movingPiece = board[oldRow][oldCol].getPiece();
        board[newRow][newCol].putPiece(movingPiece);
        board[oldRow][oldCol].putPiece(null);
        movingPiece.setRow(newRow);
        movingPiece.setCol(newCol);
    }
}


