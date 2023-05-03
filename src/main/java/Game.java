import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.lang.Math;

public class Game {
    //TODO: implement game logic
    //TODO: castling - King.java, can probably just hard code, check left/right and use copy of board using isChecked() on squares king moves through


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

            ArrayList<int[]> validMoves = currPlayer.getValidMoves(board.getBoard());
            if (validMoves.size() == 0){
                System.out.println("Checkmate");
                break;
            }
            int[] move = new int[4];
            boolean moveChosen = false;
            while (true){
                System.out.println("Enter the square of the piece you want to move and its destination ex. 5 4 4 4");
                Scanner myObj = new Scanner(System.in);
                String[] input = myObj.nextLine().split(" ");
                move[0] = Integer.parseInt(input[0]);
                move[1] = Integer.parseInt(input[1]);
                move[2] = Integer.parseInt(input[2]);
                move[3] = Integer.parseInt(input[3]);

                for (int[] possMove : validMoves){
                    if (Arrays.equals(possMove, move)){
                        moveChosen=true;
                        break;
                    }
                }
                if (moveChosen){
                    break;
                }
                else {
                    System.out.println("Invalid move");
                }
            }
            currPlayer.movePiece(move, board.getBoard());
            turn = Math.abs(turn - 1);
        }
    }
}
