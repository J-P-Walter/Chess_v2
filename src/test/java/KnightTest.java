import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KnightTest {
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
    void knight_center(){
        Knight knight = new Knight(4, 4, 0);
        board.getBoard()[4][4].putPiece(knight);

        Assertions.assertEquals(8, knight.getMoves().size());
        Assertions.assertTrue(Arrays.equals(knight.getMoves().get(0), new int[]{4, 4, 2, 3}));
        Assertions.assertTrue(Arrays.equals(knight.getMoves().get(3), new int[]{4, 4, 6, 5}));
    }

    @Test
    @DisplayName("Edge tests")
    void knight_edges(){
        Knight knight1 = new Knight(0, 3, 0);
        Knight knight2 = new Knight(3, 0, 0);
        Knight knight3 = new Knight(7, 4, 0);
        Knight knight4 = new Knight(4, 7, 0);

        board.getBoard()[knight1.getRow()][knight1.getCol()].putPiece(knight1);
        board.getBoard()[knight2.getRow()][knight2.getCol()].putPiece(knight2);
        board.getBoard()[knight3.getRow()][knight3.getCol()].putPiece(knight3);
        board.getBoard()[knight4.getRow()][knight4.getCol()].putPiece(knight4);

        Assertions.assertEquals(4, knight1.getMoves().size());
        Assertions.assertEquals(4, knight2.getMoves().size());
        Assertions.assertEquals(4, knight3.getMoves().size());
        Assertions.assertEquals(4, knight4.getMoves().size());
    }

    @Test
    @DisplayName("Corners")
    void knight_corners(){
        Knight knight1 = new Knight(0, 0, 0);
        Knight knight2 = new Knight(0, 7, 0);
        Knight knight3 = new Knight(7, 0, 0);
        Knight knight4 = new Knight(7, 7, 0);

        board.getBoard()[knight1.getRow()][knight1.getCol()].putPiece(knight1);
        board.getBoard()[knight2.getRow()][knight2.getCol()].putPiece(knight2);
        board.getBoard()[knight3.getRow()][knight3.getCol()].putPiece(knight3);
        board.getBoard()[knight4.getRow()][knight4.getCol()].putPiece(knight4);

        Assertions.assertEquals(2, knight1.getMoves().size());
        Assertions.assertEquals(2, knight2.getMoves().size());
        Assertions.assertEquals(2, knight3.getMoves().size());
        Assertions.assertEquals(2, knight4.getMoves().size());
    }

    @Test
    @DisplayName("Blocked")
    void knight_blocked(){
        Knight knight1 = new Knight(4, 4, 0);
        Knight knight2 = new Knight(2, 3, 0);
        Knight knight3 = new Knight(6, 5, 0);
        Knight knight4 = new Knight(5, 2, 0);

        board.getBoard()[knight1.getRow()][knight1.getCol()].putPiece(knight1);
        board.getBoard()[knight2.getRow()][knight2.getCol()].putPiece(knight2);
        board.getBoard()[knight3.getRow()][knight3.getCol()].putPiece(knight3);
        board.getBoard()[knight4.getRow()][knight4.getCol()].putPiece(knight4);

        Assertions.assertEquals(5, knight1.getMoves().size());
    }

    @Test
    @DisplayName("Take")
    void rook_take(){
        Knight knight1 = new Knight(4, 4, 0);
        Knight knight2 = new Knight(2, 3, 1);
        Knight knight3 = new Knight(6, 5, 1);
        Knight knight4 = new Knight(5, 2, 1);

        board.getBoard()[knight1.getRow()][knight1.getCol()].putPiece(knight1);
        board.getBoard()[knight2.getRow()][knight2.getCol()].putPiece(knight2);
        board.getBoard()[knight3.getRow()][knight3.getCol()].putPiece(knight3);
        board.getBoard()[knight4.getRow()][knight4.getCol()].putPiece(knight4);

        Assertions.assertEquals(8, knight1.getMoves().size());
    }
}