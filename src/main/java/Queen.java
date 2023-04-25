import java.util.ArrayList;

public class Queen extends Piece{
    private char name = 'Q';
    public Queen(int row, int col, int color) {
        super(row, col, color);
    }

    /*
        Queens can move both orthogonal and diagonal, like a rook and bishop combined. Using this,
        we simply create a new rook and bishop at the queens location and add their moves to the
        queen's move list 
    */
    @Override
    public ArrayList<int[]> getMoves() {
        ArrayList<int[]> validMoves = new ArrayList<>();

        Rook rook = new Rook(row, col, color);
        validMoves.addAll(rook.getMoves());

        Bishop bishop = new Bishop(row, col, color);
        validMoves.addAll(bishop.getMoves());

        return validMoves;
    }

    public char getName() {
        return name;
    }
}


