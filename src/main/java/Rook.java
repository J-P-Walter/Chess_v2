public class Rook extends Piece{
    private char name = 'R';
    public Rook(int row, int col, int color) {
        super(row, col, color);
    }

    public char getName() {
        return name;
    }
}
