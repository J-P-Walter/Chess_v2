import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private int color;

    private ArrayList<Piece> pieces = new ArrayList<>();

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
    public void placePieces(){
        Board board = Board.getInstance();
        for (Piece piece : pieces){
            board.getBoard()[piece.getRow()][piece.getCol()].setPiece(piece);
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
}


