import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

    public void setupBoard(String filename, Player whitePlayer, Player blackPlayer) {
        File file = new File(filename);
        try {
            Scanner sc = new Scanner(file);

            while (sc.hasNextLine()) {
                String line = sc.nextLine();
                String[] piece = line.split(", ");
                Player currPlayer;
                if (Integer.parseInt(piece[1]) == 0){
                    currPlayer = whitePlayer;
                } else {
                    currPlayer = blackPlayer;
                }
                switch (piece[0]) {
                    case "P":
                        Pawn pawn = new Pawn(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(pawn);
                        break;
                    case "R":
                        Rook rook = new Rook(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(rook);
                        break;
                    case "N":
                        Knight knight = new Knight(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(knight);
                        break;
                    case "B":
                        Bishop bishop = new Bishop(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(bishop);
                        break;
                    case "Q":
                        Queen queen = new Queen(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(queen);
                        break;
                    case "K":
                        King king = new King(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(king);
                        currPlayer.setKing(king);
                        break;
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        whitePlayer.placePieces();
        blackPlayer.placePieces();
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

    public void movePiece(int[] move){
        int oldRow = move[0], oldCol = move[1], newRow = move[2], newCol = move[3];
        Piece movingPiece = board[oldRow][oldCol].getPiece();
        board[newRow][newCol].setPiece(movingPiece);
        board[oldRow][oldCol].setPiece(null);
        movingPiece.setRow(newRow);
        movingPiece.setCol(newCol);
    }

}
