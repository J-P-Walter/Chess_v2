import org.junit.jupiter.api.*;

class BishopTest {
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
    void bishop_center(){
        Bishop bishop = new Bishop(4, 4, 0);
        board.getBoard()[4][4].setPiece(bishop);

        Assertions.assertEquals(13, bishop.getMoves(board.getBoard()).size());
        Assertions.assertArrayEquals(bishop.getMoves(board.getBoard()).get(3), new int[]{4, 4, 0, 0});
        Assertions.assertArrayEquals(bishop.getMoves(board.getBoard()).get(6), new int[]{4, 4, 1, 7});
        Assertions.assertArrayEquals(bishop.getMoves(board.getBoard()).get(9), new int[]{4, 4, 7, 1});
        Assertions.assertArrayEquals(bishop.getMoves(board.getBoard()).get(12), new int[]{4, 4, 7, 7});
    }

    @Test
    @DisplayName("Edges")
    void bishop_edges(){
        Bishop bishop1 = new Bishop(0, 3, 0);
        Bishop bishop2 = new Bishop(4, 0, 0);
        Bishop bishop3 = new Bishop(7, 4, 0);
        Bishop bishop4 = new Bishop(3, 7, 0);

        board.getBoard()[bishop1.getRow()][bishop1.getCol()].setPiece(bishop1);
        board.getBoard()[bishop2.getRow()][bishop2.getCol()].setPiece(bishop2);
        board.getBoard()[bishop3.getRow()][bishop3.getCol()].setPiece(bishop3);
        board.getBoard()[bishop4.getRow()][bishop4.getCol()].setPiece(bishop4);

        Assertions.assertEquals(7, bishop1.getMoves(board.getBoard()).size());
        Assertions.assertEquals(7, bishop2.getMoves(board.getBoard()).size());
        Assertions.assertEquals(7, bishop3.getMoves(board.getBoard()).size());
        Assertions.assertEquals(7, bishop4.getMoves(board.getBoard()).size());
    }

    @Test
    @DisplayName("Corners")
    void bishop_corners(){
        Bishop bishop1 = new Bishop(0, 0, 0);
        Bishop bishop2 = new Bishop(0, 7, 0);
        Bishop bishop3 = new Bishop(7, 0, 0);
        Bishop bishop4 = new Bishop(7, 7, 0);

        board.getBoard()[bishop1.getRow()][bishop1.getCol()].setPiece(bishop1);
        board.getBoard()[bishop2.getRow()][bishop2.getCol()].setPiece(bishop2);
        board.getBoard()[bishop3.getRow()][bishop3.getCol()].setPiece(bishop3);
        board.getBoard()[bishop4.getRow()][bishop4.getCol()].setPiece(bishop4);

        Assertions.assertEquals(6, bishop1.getMoves(board.getBoard()).size());
        Assertions.assertEquals(6, bishop2.getMoves(board.getBoard()).size());
        Assertions.assertEquals(6, bishop3.getMoves(board.getBoard()).size());
        Assertions.assertEquals(6, bishop4.getMoves(board.getBoard()).size());
    }

    @Test
    @DisplayName("Blocked")
    void bishop_blocked(){
        Bishop bishop1 = new Bishop(4, 4, 0);
        Bishop bishop2 = new Bishop(3, 3, 0);
        Bishop bishop3 = new Bishop(2, 6, 0);
        Bishop bishop4 = new Bishop(7, 7, 0);

        board.getBoard()[bishop1.getRow()][bishop1.getCol()].setPiece(bishop1);
        board.getBoard()[bishop2.getRow()][bishop2.getCol()].setPiece(bishop2);
        board.getBoard()[bishop3.getRow()][bishop3.getCol()].setPiece(bishop3);
        board.getBoard()[bishop4.getRow()][bishop4.getCol()].setPiece(bishop4);

        Assertions.assertEquals(6, bishop1.getMoves(board.getBoard()).size());
    }

    @Test
    @DisplayName("Take")
    void bishop_take(){
        Bishop bishop1 = new Bishop(4, 4, 0);
        Bishop bishop2 = new Bishop(3, 3, 1);
        Bishop bishop3 = new Bishop(2, 6, 1);
        Bishop bishop4 = new Bishop(7, 7, 1);

        board.getBoard()[bishop1.getRow()][bishop1.getCol()].setPiece(bishop1);
        board.getBoard()[bishop2.getRow()][bishop2.getCol()].setPiece(bishop2);
        board.getBoard()[bishop3.getRow()][bishop3.getCol()].setPiece(bishop3);
        board.getBoard()[bishop4.getRow()][bishop4.getCol()].setPiece(bishop4);

        Assertions.assertEquals(9, bishop1.getMoves(board.getBoard()).size());
    }
}