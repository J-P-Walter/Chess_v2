public class Square {
    int row;
    int col;
    private Piece piece;
    private Piece prevPiece;

    public Square(int row, int col){
        super();
        this.row = row;
        this.col = col;
    }

    public boolean isOccupied() {
        return (piece != null);
    }
    public void setPiece(Piece piece){
        if (this.piece != null){
            prevPiece = this.piece;
        }
        this.piece = piece;
    }
    public Piece getPrevPiece(){ return prevPiece; }
    public void setPrevPiece(){ this.prevPiece = piece; }
    public Piece getPiece() {
        return piece;
    }
}
