import org.junit.jupiter.api.*;

class RookTest {
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
    void rook_center(){
        Rook rook = new Rook(4, 4, 0);
        board.getBoard()[4][4].setPiece(rook);

        Assertions.assertEquals(14, rook.getMoves(board.getBoard()).size());
        Assertions.assertArrayEquals(rook.getMoves(board.getBoard()).get(3), new int[]{4, 4, 4, 0});
        Assertions.assertArrayEquals(rook.getMoves(board.getBoard()).get(6), new int[]{4, 4, 4, 7});
        Assertions.assertArrayEquals(rook.getMoves(board.getBoard()).get(10), new int[]{4, 4, 0, 4});
        Assertions.assertArrayEquals(rook.getMoves(board.getBoard()).get(13), new int[]{4, 4, 7, 4});
    }

    @Test
    @DisplayName("Edges")
    void rook_edges(){
        Rook rook1 = new Rook(0, 3, 0);
        Rook rook2 = new Rook(3, 0, 0);
        Rook rook3 = new Rook(7, 4, 0);
        Rook rook4 = new Rook(4, 7, 0);

        board.getBoard()[rook1.getRow()][rook1.getCol()].setPiece(rook1);
        board.getBoard()[rook2.getRow()][rook2.getCol()].setPiece(rook2);
        board.getBoard()[rook3.getRow()][rook3.getCol()].setPiece(rook3);
        board.getBoard()[rook4.getRow()][rook4.getCol()].setPiece(rook4);

        Assertions.assertEquals(14, rook1.getMoves(board.getBoard()).size());
        Assertions.assertEquals(14, rook2.getMoves(board.getBoard()).size());
        Assertions.assertEquals(14, rook3.getMoves(board.getBoard()).size());
        Assertions.assertEquals(14, rook4.getMoves(board.getBoard()).size());
    }

    @Test
    @DisplayName("Corners")
    void rook_corners(){
        Rook rook1 = new Rook(0, 0, 0);
        Rook rook2 = new Rook(0, 7, 0);
        Rook rook3 = new Rook(7, 0, 0);
        Rook rook4 = new Rook(7, 7, 0);

        board.getBoard()[rook1.getRow()][rook1.getCol()].setPiece(rook1);
        board.getBoard()[rook2.getRow()][rook2.getCol()].setPiece(rook2);
        board.getBoard()[rook3.getRow()][rook3.getCol()].setPiece(rook3);
        board.getBoard()[rook4.getRow()][rook4.getCol()].setPiece(rook4);

        Assertions.assertEquals(12, rook1.getMoves(board.getBoard()).size());
        Assertions.assertEquals(12, rook2.getMoves(board.getBoard()).size());
        Assertions.assertEquals(12, rook3.getMoves(board.getBoard()).size());
        Assertions.assertEquals(12, rook4.getMoves(board.getBoard()).size());
    }

    @Test
    @DisplayName("Blocked")
    void rook_blocked(){
        Rook rook1 = new Rook(4, 4, 0);
        Rook rook2 = new Rook(4, 3, 0);
        Rook rook3 = new Rook(2, 4, 0);
        Rook rook4 = new Rook(4, 7, 0);

        board.getBoard()[rook1.getRow()][rook1.getCol()].setPiece(rook1);
        board.getBoard()[rook2.getRow()][rook2.getCol()].setPiece(rook2);
        board.getBoard()[rook3.getRow()][rook3.getCol()].setPiece(rook3);
        board.getBoard()[rook4.getRow()][rook4.getCol()].setPiece(rook4);

        Assertions.assertEquals(6, rook1.getMoves(board.getBoard()).size());
    }

    @Test
    @DisplayName("Take")
    void rook_take(){
        Rook rook1 = new Rook(4, 4, 0);
        Rook rook2 = new Rook(4, 3, 1);
        Rook rook3 = new Rook(2, 4, 1);
        Rook rook4 = new Rook(4, 7, 1);

        board.getBoard()[rook1.getRow()][rook1.getCol()].setPiece(rook1);
        board.getBoard()[rook2.getRow()][rook2.getCol()].setPiece(rook2);
        board.getBoard()[rook3.getRow()][rook3.getCol()].setPiece(rook3);
        board.getBoard()[rook4.getRow()][rook4.getCol()].setPiece(rook4);

        Assertions.assertEquals(9, rook1.getMoves(board.getBoard()).size());
    }
}