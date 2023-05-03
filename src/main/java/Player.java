import java.util.ArrayList;
import java.lang.Math;

public class Player {
    private final int color;
    private ArrayList<Piece> pieces = new ArrayList<>();
    King king;

    public Player(int color){
        this.color = color;
    }

    public void addPiece(Piece piece){
        pieces.add(piece);
    }
    public void placePieces(Square[][] board){
        for (Piece piece : pieces){
            board[piece.getRow()][piece.getCol()].setPiece(piece);
        }
    }

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
            //Sets all pawns to not be taken by en-passant
            //If they were able to be taken last turn, the window has closed and
            if (p.getClass() == Pawn.class){
                ((Pawn) p).setEnPassant(false);
            }
        }
        return validMoves;
    }

    /*
        These two methods check to ensure a move does not put yourself in check
        The piece is moved, then the king makes sure it is not in check because of the move, then the piece is moved back.
        If a piece was taken during the test move, it is recorded, and replaced one the moving piece is moved back
        to its original position.
     */
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

    /*
        Function that makes the "official" moves for pieces
        Records if pawns move/en-passant/promote and if kings/rooks move for castling
     */
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
            if (color == 0 && newRow == 0){
                movingPiece.setInPlay(false);
                Queen promotedQueen = new Queen(newRow, newCol, color);
                addPiece(promotedQueen);
                placePieces(board);
            }
            if (color == 1 && newRow == 7){
                movingPiece.setInPlay(false);
                Queen promotedQueen = new Queen(newRow, newCol, color);
                addPiece(promotedQueen);
                placePieces(board);
            }
        }
        if (movingPiece.getClass() == Rook.class){
            ((Rook) movingPiece).setMoved(true);
        }
        if (movingPiece.getClass() == King.class){
            ((King) movingPiece).setMoved(true);
        }
    }

    public ArrayList<Piece> getPieces(){
        return pieces;
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


