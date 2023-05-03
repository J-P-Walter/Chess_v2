import java.util.ArrayList;
import java.lang.Math;

public class Player {
    private final int color;

    private ArrayList<Piece> pieces = new ArrayList<>();
    King king;

    public ArrayList<int[]> getValidMoves(Square[][] board){
        ArrayList<int[]> validMoves = new ArrayList<>();

        for (Piece p : pieces){
            if (p.getMoves(board) == null){
                continue;
            }
            for (int[] move : p.getMoves(board)){
                testMove(move, board);
                if (!getKing().isChecked(board, color)){
                    validMoves.add(move);
                }
                undoTestMove(move, board);
            }
            if (p.getClass() == Pawn.class){
                ((Pawn) p).setEnPassant(false);
            }
        }

        return validMoves;
    }

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
            board[piece.getRow()][piece.getCol()].setPiece(piece);
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

    public void testMove(int[] move, Square[][] board){
        int oldRow = move[0], oldCol = move[1], newRow = move[2], newCol = move[3];

        Square startSquare = board[oldRow][oldCol];
        Square endSquare = board[newRow][newCol];

        Piece movingPiece = startSquare.getPiece();

        startSquare.setPiece(null);
        endSquare.setPrevPiece();
        endSquare.setPiece(movingPiece);

        movingPiece.setRow(newRow);
        movingPiece.setCol(newCol);
    }

    public void undoTestMove(int[] move, Square[][] board){
        int oldRow = move[2], oldCol = move[3], newRow = move[0], newCol = move[1];

        Square startSquare = board[oldRow][oldCol];
        Square endSquare = board[newRow][newCol];

        Piece movingPiece = startSquare.getPiece();

        startSquare.setPiece(startSquare.getPrevPiece());
        endSquare.setPiece(movingPiece);

        movingPiece.setRow(newRow);
        movingPiece.setCol(newCol);
        startSquare.setPrevPiece();
    }

    public void movePiece(int[] move, Square[][] board){
        int oldRow = move[0], oldCol = move[1], newRow = move[2], newCol = move[3];

        Square startSquare = board[oldRow][oldCol];
        Square endSquare = board[newRow][newCol];

        Piece movingPiece = startSquare.getPiece();
        Piece oppPiece = null;
        if (endSquare.getPiece() != null){
            oppPiece = endSquare.getPiece();
            oppPiece.setInPlay(false);
        }
        startSquare.setPiece(null);
        endSquare.setPiece(movingPiece);
        movingPiece.setRow(newRow);
        movingPiece.setCol(newCol);

        
        if (movingPiece.getClass() == Pawn.class){
            ((Pawn) movingPiece).setMoved(true);
            if (Math.abs(oldRow-newRow) == 2){
                ((Pawn) movingPiece).setEnPassant(true);
            }
            if (oldCol != newCol && oppPiece == null){
                board[oldRow][newCol].getPiece().setInPlay(false);
                board[oldRow][newCol].setPiece(null);
            }
        }

        if (movingPiece.getClass() == Rook.class){
            ((Rook) movingPiece).setMoved(true);
        }
        if (movingPiece.getClass() == King.class){
            ((King) movingPiece).setMoved(true);
        }
    }
}


