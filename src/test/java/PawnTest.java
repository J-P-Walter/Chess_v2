import org.junit.jupiter.api.*;

class PawnTest {
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
    @DisplayName("Center of board")
    void pawn_center(){
        Pawn whitePawn = new Pawn(4, 5, 0);
        Pawn blackPawn = new Pawn(4, 3, 1);

        whitePawn.setMoved(true);
        blackPawn.setMoved(true);

        board.getBoard()[whitePawn.getRow()][whitePawn.getCol()].putPiece(whitePawn);
        board.getBoard()[blackPawn.getRow()][blackPawn.getCol()].putPiece(blackPawn);

        Assertions.assertEquals(1, whitePawn.getMoves(board.getBoard()).size());
        Assertions.assertArrayEquals(whitePawn.getMoves(board.getBoard()).get(0), new int[]{4, 5, 3, 5});

        Assertions.assertEquals(1, blackPawn.getMoves(board.getBoard()).size());
        Assertions.assertArrayEquals(blackPawn.getMoves(board.getBoard()).get(0), new int[]{4, 3, 5, 3});
    }

    @Test
    @DisplayName("Pawn first move")
    void pawn_first_move(){
        Pawn whitePawn = new Pawn(6, 5, 0);
        Pawn blackPawn = new Pawn(1, 3, 1);

        board.getBoard()[whitePawn.getRow()][whitePawn.getCol()].putPiece(whitePawn);
        board.getBoard()[blackPawn.getRow()][blackPawn.getCol()].putPiece(blackPawn);

        Assertions.assertEquals(2, whitePawn.getMoves(board.getBoard()).size());
        Assertions.assertArrayEquals(whitePawn.getMoves(board.getBoard()).get(0), new int[]{6, 5, 4, 5});
        Assertions.assertArrayEquals(whitePawn.getMoves(board.getBoard()).get(1), new int[]{6, 5, 5, 5});


        Assertions.assertEquals(2, blackPawn.getMoves(board.getBoard()).size());
        Assertions.assertArrayEquals(blackPawn.getMoves(board.getBoard()).get(0), new int[]{1, 3, 3, 3});
        Assertions.assertArrayEquals(blackPawn.getMoves(board.getBoard()).get(1), new int[]{1, 3, 2, 3});
    }

    @Test
    @DisplayName("Pawn attacking")
    void pawn_attacking(){
        Pawn whitePawn = new Pawn(4, 5, 0);
        Pawn blackPawn = new Pawn(3, 4, 1);

        whitePawn.setMoved(true);
        blackPawn.setMoved(true);

        board.getBoard()[whitePawn.getRow()][whitePawn.getCol()].putPiece(whitePawn);
        board.getBoard()[blackPawn.getRow()][blackPawn.getCol()].putPiece(blackPawn);

        Assertions.assertEquals(2, whitePawn.getMoves(board.getBoard()).size());
        Assertions.assertArrayEquals(whitePawn.getMoves(board.getBoard()).get(0), new int[]{4, 5, 3, 5});
        Assertions.assertArrayEquals(whitePawn.getMoves(board.getBoard()).get(1), new int[]{4, 5, 3, 4});


        Assertions.assertEquals(2, blackPawn.getMoves(board.getBoard()).size());
        Assertions.assertArrayEquals(blackPawn.getMoves(board.getBoard()).get(0), new int[]{3, 4, 4, 4});
        Assertions.assertArrayEquals(blackPawn.getMoves(board.getBoard()).get(1), new int[]{3, 4, 4, 5});
    }
}