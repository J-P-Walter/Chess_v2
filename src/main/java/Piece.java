import java.util.ArrayList;

/*
    Abstract class for all Pieces
 */

public abstract class Piece {
    protected int row, col;
    protected int color;
    protected boolean inPlay = true;

    public Piece(int row, int col, int color){
        this.row = row;
        this.col = col;
        this.color = color;
    }
    public abstract ArrayList<int[]> getMoves(Square[][] board);

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public int getColor() {
        return color;
    }

    public boolean isInPlay(){
        return inPlay;
    }

    public void setInPlay(boolean inPlay){this.inPlay = inPlay;}

    public abstract char getName();
}
