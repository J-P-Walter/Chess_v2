import java.util.ArrayList;

public abstract class Piece {
    protected int row, col;
    protected int color;
    private char name;

    public Piece(int row, int col, int color){
        this.row = row;
        this.col = col;
        this.color = color;
    }
    public abstract ArrayList<int[]> getMoves();

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

    public char getName() {return name; }
}
