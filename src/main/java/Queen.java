import java.util.ArrayList;

public class Queen extends Piece{

    private char name = 'Q';
    public Queen(int row, int col, int color) {
        super(row, col, color);
    }

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


