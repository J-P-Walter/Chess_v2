import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class Game {
    //TODO: implement game logic
    //TODO: castling - King.java, can probably just hard code, check left/right and use copy of board using isChecked() on squares king moves through
    //TODO: unicode characters aren't working, try to fix

    public static void main(String[] args){
        Board board = new Board(null);
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);
        board.setupBoard("./data/ChessSetup.txt", whitePlayer, blackPlayer);

        int turn = 0;

        while (true){
            Player currPlayer;

            if (turn == 0){
                currPlayer = whitePlayer;
            } else {
                currPlayer = blackPlayer;
            }

            System.out.println("Player " + currPlayer.getColor() + "'s turn");
            board.printBoard();

            ArrayList<int[]> validMoves = currPlayer.getAllValidMoves(board.getBoard());
            if (validMoves.size() == 0){
                System.out.println("Checkmate");
                break;
            }
            int[] move;
            boolean moveChosen = false;
            do {
                System.out.println("Enter the square of the piece you want to move and its destination ex. 5 4 4 4");
                Scanner myObj = new Scanner(System.in);
                String[] input = myObj.nextLine().split(" ");
                move = new int[input.length];
                for (int i = 0; i < input.length; i++){
                    move[i] = Integer.parseInt(input[i]);
                }

                for (int[] possMove : validMoves) {
                    if (Arrays.equals(possMove, move)) {
                        moveChosen = true;
                        break;
                    }
                }
                System.out.println("Invalid move");
            } while (!moveChosen);
            currPlayer.movePiece(move, board.getBoard());
            turn = Math.abs(turn - 1);
        }
    }
}
