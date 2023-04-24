import org.junit.jupiter.api.*;

import java.util.Arrays;

class RookTest {
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
    @DisplayName("Center of board")
    void rook_center(){
        Rook rook = new Rook(4, 4, 0);
        board.getBoard()[4][4].putPiece(rook);

        Assertions.assertEquals(14, rook.getMoves().size());
        Assertions.assertTrue(Arrays.equals(rook.getMoves().get(3), new int[]{4, 4, 4, 0}));
        Assertions.assertTrue(Arrays.equals(rook.getMoves().get(6), new int[]{4, 4, 4, 7}));
        Assertions.assertTrue(Arrays.equals(rook.getMoves().get(10), new int[]{4, 4, 0, 4}));
        Assertions.assertTrue(Arrays.equals(rook.getMoves().get(13), new int[]{4, 4, 7, 4}));
    }

    @Test
    @DisplayName("Edges")
    void rook_edges(){
        Rook rook1 = new Rook(0, 3, 0);
        Rook rook2 = new Rook(3, 0, 0);
        Rook rook3 = new Rook(7, 4, 0);
        Rook rook4 = new Rook(4, 7, 0);

        board.getBoard()[rook1.getRow()][rook1.getCol()].putPiece(rook1);
        board.getBoard()[rook2.getRow()][rook2.getCol()].putPiece(rook2);
        board.getBoard()[rook3.getRow()][rook3.getCol()].putPiece(rook3);
        board.getBoard()[rook4.getRow()][rook4.getCol()].putPiece(rook4);

        Assertions.assertEquals(14, rook1.getMoves().size());
        Assertions.assertEquals(14, rook2.getMoves().size());
        Assertions.assertEquals(14, rook3.getMoves().size());
        Assertions.assertEquals(14, rook4.getMoves().size());
    }

    @Test
    @DisplayName("Corners")
    void rook_corners(){
        Rook rook1 = new Rook(0, 0, 0);
        Rook rook2 = new Rook(0, 7, 0);
        Rook rook3 = new Rook(7, 0, 0);
        Rook rook4 = new Rook(7, 7, 0);

        board.getBoard()[rook1.getRow()][rook1.getCol()].putPiece(rook1);
        board.getBoard()[rook2.getRow()][rook2.getCol()].putPiece(rook2);
        board.getBoard()[rook3.getRow()][rook3.getCol()].putPiece(rook3);
        board.getBoard()[rook4.getRow()][rook4.getCol()].putPiece(rook4);

        Assertions.assertEquals(12, rook1.getMoves().size());
        Assertions.assertEquals(12, rook2.getMoves().size());
        Assertions.assertEquals(12, rook3.getMoves().size());
        Assertions.assertEquals(12, rook4.getMoves().size());
    }

    @Test
    @DisplayName("Blocked")
    void rook_blocked(){
        Rook rook1 = new Rook(4, 4, 0);
        Rook rook2 = new Rook(4, 3, 0);
        Rook rook3 = new Rook(2, 4, 0);
        Rook rook4 = new Rook(4, 7, 0);

        board.getBoard()[rook1.getRow()][rook1.getCol()].putPiece(rook1);
        board.getBoard()[rook2.getRow()][rook2.getCol()].putPiece(rook2);
        board.getBoard()[rook3.getRow()][rook3.getCol()].putPiece(rook3);
        board.getBoard()[rook4.getRow()][rook4.getCol()].putPiece(rook4);

        Assertions.assertEquals(6, rook1.getMoves().size());
    }

    @Test
    @DisplayName("Take")
    void rook_take(){
        Rook rook1 = new Rook(4, 4, 0);
        Rook rook2 = new Rook(4, 3, 1);
        Rook rook3 = new Rook(2, 4, 1);
        Rook rook4 = new Rook(4, 7, 1);

        board.getBoard()[rook1.getRow()][rook1.getCol()].putPiece(rook1);
        board.getBoard()[rook2.getRow()][rook2.getCol()].putPiece(rook2);
        board.getBoard()[rook3.getRow()][rook3.getCol()].putPiece(rook3);
        board.getBoard()[rook4.getRow()][rook4.getCol()].putPiece(rook4);

        Assertions.assertEquals(9, rook1.getMoves().size());
    }
}