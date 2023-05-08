import org.junit.jupiter.api.*;

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
        Assertions.assertEquals(20, whitePlayer.getAllValidMoves(board.getBoard()).size());
        Assertions.assertEquals(20, blackPlayer.getAllValidMoves(board.getBoard()).size());
    }

    @Test
    @DisplayName("Take test")
    void take_test(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        Pawn whitePawn = new Pawn(4, 5, 0);
        Pawn blackPawn = new Pawn(3, 4, 1);

        King whiteKing = new King(7,4, 0);
        King blackKing = new King(0,3, 1);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.setKing(whiteKing);

        blackPlayer.addPiece(blackKing);
        blackPlayer.setKing(blackKing);

        whitePawn.setMoved(true);
        blackPawn.setMoved(true);

        whitePlayer.addPiece(whitePawn);
        blackPlayer.addPiece(blackPawn);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(7, whitePlayer.getAllValidMoves(board.getBoard()).size());
        Assertions.assertEquals(7, blackPlayer.getAllValidMoves(board.getBoard()).size());

        whitePlayer.movePiece(new int[]{4, 5, 3, 4}, board.getBoard());
        System.out.println();
        System.out.println("AFTER MOVE");

        Assertions.assertEquals(6, whitePlayer.getAllValidMoves(board.getBoard()).size());
        Assertions.assertEquals(5, blackPlayer.getAllValidMoves(board.getBoard()).size());
    }

    @Test
    @DisplayName("Left castle move test")
    void left_castle_move_test(){
        Player whitePlayer = new Player(0);

        King king = new King(7,4, 0);
        Rook rook = new Rook(7, 0, 0);

        whitePlayer.addPiece(king);
        whitePlayer.addPiece(rook);
        whitePlayer.setKing(king);

        whitePlayer.placePieces(board.getBoard());

        whitePlayer.getAllValidMoves(board.getBoard());

        whitePlayer.movePiece(new int[] {7, 4, 7, 2, 7, 0, 7, 3}, board.getBoard());

        Assertions.assertEquals('K', board.getBoard()[7][2].getPiece().getName());
        Assertions.assertEquals('R', board.getBoard()[7][3].getPiece().getName());
    }

    @Test
    @DisplayName("Right castle move test")
    void right_castle_move_test(){
        Player whitePlayer = new Player(0);

        King king = new King(7,4, 0);
        Rook rook = new Rook(7, 7, 0);

        whitePlayer.addPiece(king);
        whitePlayer.addPiece(rook);
        whitePlayer.setKing(king);

        whitePlayer.placePieces(board.getBoard());

        whitePlayer.getAllValidMoves(board.getBoard());

        whitePlayer.movePiece(new int[] {7, 4, 7, 6, 7, 7, 7, 5}, board.getBoard());

        Assertions.assertEquals('K', board.getBoard()[7][6].getPiece().getName());
        Assertions.assertEquals('R', board.getBoard()[7][5].getPiece().getName());
    }
}