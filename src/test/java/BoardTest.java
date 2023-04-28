import org.junit.jupiter.api.*;

import java.util.ArrayList;

class BoardTest {
    Board board = new Board(null);

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
        Assertions.assertEquals(20, whitePlayer.getValidMoves(board.getBoard()).size());
        Assertions.assertEquals(20, blackPlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    @DisplayName("Total starting moves")
    void number_starting_moves(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);
        board.setupBoard("./data/ChessSetup.txt", whitePlayer, blackPlayer);

        ArrayList<int[]> totalWhiteMoves = new ArrayList<>();
        for (Piece piece : whitePlayer.getPieces()){
            totalWhiteMoves.addAll(piece.getMoves(board.getBoard()));
        }
        ArrayList<int[]> totalBlackMoves = new ArrayList<>();
        for (Piece piece : blackPlayer.getPieces()){
            totalBlackMoves.addAll(piece.getMoves(board.getBoard()));
        }
        Assertions.assertEquals(20, totalWhiteMoves.size());
        Assertions.assertEquals(20, totalBlackMoves.size());
    }

//    @Test
//    @DisplayName("Take test")
//    void take_test(){
//        Player whitePlayer = new Player(0);
//        Player blackPlayer = new Player(1);
//
//        Pawn whitePawn = new Pawn(4, 5, 0);
//        Pawn blackPawn = new Pawn(3, 4, 1);
//
//        King whiteKing = new King(7,4, 0);
//        King blackKing = new King(0,3, 0);
//
//        whitePlayer.addPiece(whiteKing);
//        whitePlayer.setKing(whiteKing);
//
//        blackPlayer.addPiece(blackKing);
//        blackPlayer.setKing(blackKing);
//
//        whitePawn.setMoved(true);
//        blackPawn.setMoved(true);
//
//        whitePlayer.addPiece(whitePawn);
//        blackPlayer.addPiece(blackPawn);
//
//        whitePlayer.placePieces();
//        blackPlayer.placePieces();
//
//        Assertions.assertEquals(2, whitePlayer.getValidMoves());
//        Assertions.assertEquals(2, blackPlayer.getValidMoves());
//
//        whitePlayer.movePiece(new int[]{4, 5, 3, 4}, board.getBoard());
//
//        Assertions.assertEquals(1, whitePlayer.getValidMoves());
//        Assertions.assertEquals(0, blackPlayer.getValidMoves());
//    }

}