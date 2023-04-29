import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Board {
    private Square[][] board;

    public Board(Square[][] board){
        if (board != null){
            this.board = board;
        } else {
            resetBoard();
        }
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
                    case "P" -> {
                        Pawn pawn = new Pawn(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(pawn);
                    }
                    case "R" -> {
                        Rook rook = new Rook(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(rook);
                    }
                    case "N" -> {
                        Knight knight = new Knight(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(knight);
                    }
                    case "B" -> {
                        Bishop bishop = new Bishop(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(bishop);
                    }
                    case "Q" -> {
                        Queen queen = new Queen(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(queen);
                    }
                    case "K" -> {
                        King king = new King(
                                Integer.parseInt(piece[2]),
                                Integer.parseInt(piece[3]),
                                Integer.parseInt(piece[1])
                        );
                        currPlayer.addPiece(king);
                        currPlayer.setKing(king);
                    }
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        whitePlayer.placePieces(board);
        blackPlayer.placePieces(board);
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
        System.out.println("  -------------------------");
        for (Square[] row : board){
            System.out.print(idx + " |");
            for (Square p : row){
                if (!p.isOccupied()){
                    System.out.print("  |");
                }
                else {
                    String color = p.getPiece().getColor() == 0 ? "W" : "B";
                    System.out.print(color + p.getPiece().getName() + "|");
                }
            }
            System.out.println();
            System.out.println("  -------------------------");

            idx++;
        }
        System.out.println("   0  1  2  3  4  5  6  7");
    }
}
