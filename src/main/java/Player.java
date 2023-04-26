import java.lang.reflect.Array;
import java.util.ArrayList;

public class Player {
    private int color;

    private ArrayList<Piece> pieces = new ArrayList<>();
    private ArrayList<int[]> validMoves = new ArrayList<>();

    public ArrayList<int[]> getValidMoves(){
        for (Piece piece : pieces){
            validMoves.addAll(piece.getMoves());
        }

        for (int i = validMoves.size()-1; i >= 0; i--){
            Board.getInstance().movePiece(validMoves.get(i));
            if (king.isChecked(Board.getInstance(), color)){
                Board.getInstance().movePieceBack(validMoves.get(i));
                validMoves.remove(i);
            } else {
                Board.getInstance().movePieceBack(validMoves.get(i));
            };

        }

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


