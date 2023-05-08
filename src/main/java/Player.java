import java.util.ArrayList;
import java.lang.Math;
import java.util.Arrays;

public class Player {
    private final int color;
    private final ArrayList<Piece> pieces = new ArrayList<>();
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

    public ArrayList<int[]> getAllValidMoves(Square[][] board){
        ArrayList<int[]> validMoves = new ArrayList<>();

        Rook leftRook = null;
        Rook rightRook = null;

        for (Piece p : pieces){
            p.resetValidMoves();
            if (p.getMoves(board) == null){
                continue;
            }
            for (int[] move : p.getMoves(board)){
                testMove(move, board);
                if (!getKing().isChecked(board, color)){
                    validMoves.add(move);
                    p.addValidMove(move);
                }
                undoTestMove(move, board);
            }
            //Sets all pawns to not be taken by en-passant
            //If they were able to be taken last turn, the window has closed and
            if (p.getClass() == Pawn.class){
                ((Pawn) p).setEnPassant(false);
            }

            //"Saves" left and right rook for castle check
            if (p.getClass() == Rook.class){
                if (!((Rook) p).isMoved()){
                    if (p.getCol() < king.getCol()){
                        leftRook = (Rook) p;
                    }
                    else {
                        rightRook = (Rook) p;
                    }
                }
            }
        }

        //Checks for valid castle
        if (leftRook != null){
            int[] castle = validateCastle(board, king, leftRook);
            if (castle != null){
                validMoves.add(castle);
                king.addValidMove(castle);
            }

        }
       if (rightRook != null){
            int[] castle = validateCastle(board, king, rightRook);
            if (castle != null){
                validMoves.add(castle);
                king.addValidMove(castle);
            }

        }

        return validMoves;
    }

    /*
        There are several steps when validating the castle moves
        1. Check if the king has not moved
        2. Check if the rook in question has not moved
        3. Check if the squares between the king and the rook are unoccupied
        4. Check if the king is placed in check on either of the two squares in the direction of the castle
        If all of these conditions are false, a valid castle is able to be made and is added
        to the player and king's movelists
     */
    private int[] validateCastle(Square[][] board, King king, Rook rook) {
        int kingRow = king.getRow();
        int kingCol = king.getCol();

        int direction;
        if (kingCol < rook.getCol()){
            direction = 1;
        } else {
            direction = -1;
        }

        if (king.isMoved()){
            return null;
        }
        if (rook.isMoved()){
            return null;
        }
        if (board[kingRow][kingCol+direction].isOccupied() || board[kingRow][kingCol+direction+direction].isOccupied()){
            return null;
        }
        //Need to make sure 3 squares are unoccupied queen-side, just makes sure there is a rook king-side
        if (board[kingRow][kingCol+(direction*3)].isOccupied() && board[kingRow][kingCol+(direction*3)].getPiece().getClass() != Rook.class){
            return null;
        }

        //Testing first square for check
        testMove(new int[]{kingRow, kingCol, kingRow, kingCol+direction}, board);
        if (king.isChecked(board, color)){
            undoTestMove(new int[]{kingRow, kingCol, kingRow, kingCol+direction}, board);
            return null;
        }
        undoTestMove(new int[]{kingRow, kingCol, kingRow, kingCol+direction}, board);
        //Testing second square for check
        testMove(new int[]{kingRow, kingCol, kingRow, king.getCol()+direction+direction}, board);
        if (king.isChecked(board, color)){
            undoTestMove(new int[]{kingRow, kingCol, kingRow, king.getCol()}, board);
            return null;
        }
        undoTestMove(new int[]{kingRow, kingCol, kingRow, king.getCol()}, board);

        return new int[]{kingRow, kingCol, kingRow, kingCol+direction+direction, rook.getRow(), rook.getCol(), rook.getRow(), kingCol+direction};
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
            //Takes care of moving the rook if king initiates castle
            if (Math.abs(oldCol-newCol) != 1){
                int[] rookMove = Arrays.copyOfRange(move, 4, 8);
                movePiece(rookMove, board);
            }
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


