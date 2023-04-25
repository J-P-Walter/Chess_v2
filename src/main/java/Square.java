public class Square {
    int row;
    int col;
    private Piece piece;

    public Square(int row, int col){
        super();
        this.row = row;
        this.col = col;
        piece = null;
    }

    public boolean isOccupied() {
        return (piece != null);
    }

    public void putPiece(Piece piece){
        this.piece = piece;
    }

    public Piece getPiece() {
        return piece;
    }
}
