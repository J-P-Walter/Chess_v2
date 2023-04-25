public class Board {
    private Square[][] board;
    private static Board instance = null;

    //TODO: take in file for setup for easier testing
    public Board(){
        resetBoard();
    }

    public static Board getInstance(){
        if (instance == null) {
            instance = new Board();
        }
        return instance;
    }

    public void resetBoard(){
        board = new Square[8][8];
        for (int i = 0; i < 8; i++){
            for (int j = 0; j < 8; j++){
                board[i][j] = new Square(i, j);
            }
        }
    }

    public Square[][] getBoard(){ return board; }

    public void printBoard(){
        int idx = 0;
        for (Square[] row : board){
            System.out.print(idx + " ");
            for (Square p : row){
                if (!p.isOccupied()){
                    System.out.print("_ ");
                }
                else {
                    System.out.print(p.getPiece().getName() + " ");
                }
            }
            System.out.println();
            idx++;
        }
        System.out.println("  0 1 2 3 4 5 6 7");
    }

}
