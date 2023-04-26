import org.junit.jupiter.api.*;

import java.util.ArrayList;

class BoardTest {
    private static Board board = Board.getInstance();

    @BeforeEach
    void setUp(){
        board.resetBoard();
    }

    @AfterEach
    void print(){
        board.printBoard();
        System.out.println();
    }

    @Test
    @DisplayName("Testing Board Dimensions")
    void test_dimensions(){
        Assertions.assertEquals(8, board.getBoard().length);
        Assertions.assertEquals(8, board.getBoard()[0].length);
    }

    @Test
    @DisplayName("Testing Pawn move")
    void pawn_move_test(){
        Pawn pawn = new Pawn(6, 2, 0);

        board.getBoard()[pawn.getRow()][pawn.getCol()].putPiece(pawn);

        System.out.println("Before");
        board.printBoard();
        System.out.println();

        ArrayList<int[]> moves = pawn.getMoves();
        board.movePiece(moves.get(0));

        System.out.println("After");

        Assertions.assertNull(board.getBoard()[6][2].getPiece());
        Assertions.assertNotNull(board.getBoard()[4][2].getPiece());
        Assertions.assertEquals('P', board.getBoard()[4][2].getPiece().getName());
    }

    @Test
    @DisplayName("Testing board setup")
    void board_setup_test(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);
        board.setupBoard("./data/ChessSetup.txt", whitePlayer, blackPlayer);

        Assertions.assertTrue(board.getBoard()[0][0].isOccupied());
        Assertions.assertTrue(board.getBoard()[0][7].isOccupied());
        Assertions.assertTrue(board.getBoard()[7][0].isOccupied());
        Assertions.assertTrue(board.getBoard()[7][7].isOccupied());

        Assertions.assertEquals(16, whitePlayer.getPieces().size());
        Assertions.assertEquals(16, blackPlayer.getPieces().size());
    }

    @Test
    @DisplayName("Total starting moves")
    void number_starting_moves(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);
        board.setupBoard("./data/ChessSetup.txt", whitePlayer, blackPlayer);

        ArrayList<int[]> totalWhiteMoves = new ArrayList<>();
        for (Piece piece : whitePlayer.getPieces()){
            totalWhiteMoves.addAll(piece.getMoves());
        }
        ArrayList<int[]> totalBlackMoves = new ArrayList<>();
        for (Piece piece : blackPlayer.getPieces()){
            totalBlackMoves.addAll(piece.getMoves());
        }
        Assertions.assertEquals(20, totalWhiteMoves.size());
        Assertions.assertEquals(20, totalBlackMoves.size());
    }


}