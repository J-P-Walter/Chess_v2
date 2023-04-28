import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(int row, int col, int color) {
        super(row, col, color);
    }

    /*
        Queens can move both orthogonal and diagonal, like a rook and bishop combined. Using this,
        we simply create a new rook and bishop at the queens location and add their moves to the
        queen's move list 
    */
    @Override
    public ArrayList<int[]> getMoves(Square[][] board) {
        if (!inPlay){ return null; }

        ArrayList<int[]> moves = new ArrayList<>();

        Rook rook = new Rook(row, col, color);
        moves.addAll(rook.getMoves(board));

        Bishop bishop = new Bishop(row, col, color);
        moves.addAll(bishop.getMoves(board));

        return moves;
    }

    public char getName() {
        return 'Q';
    }
}


