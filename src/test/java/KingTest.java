import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class KingTest {
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
    void king_center(){
        King king = new King(4, 4, 0);
        board.getBoard()[4][4].putPiece(king);

        Assertions.assertEquals(8, king.getMoves().size());
        Assertions.assertTrue(Arrays.equals(king.getMoves().get(0), new int[]{4, 4, 4, 3}));
        Assertions.assertTrue(Arrays.equals(king.getMoves().get(1), new int[]{4, 4, 4, 5}));
        Assertions.assertTrue(Arrays.equals(king.getMoves().get(2), new int[]{4, 4, 3, 4}));
        Assertions.assertTrue(Arrays.equals(king.getMoves().get(3), new int[]{4, 4, 5, 4}));

        Assertions.assertTrue(Arrays.equals(king.getMoves().get(4), new int[]{4, 4, 3, 3}));
        Assertions.assertTrue(Arrays.equals(king.getMoves().get(5), new int[]{4, 4, 3, 5}));
        Assertions.assertTrue(Arrays.equals(king.getMoves().get(6), new int[]{4, 4, 5, 3}));
        Assertions.assertTrue(Arrays.equals(king.getMoves().get(7), new int[]{4, 4, 5, 5}));
    }

    @Test
    @DisplayName("Edge tests")
    void king_edges(){
        King king1 = new King(0, 3, 0);
        King king2 = new King(3, 0, 0);
        King king3 = new King(7, 4, 0);
        King king4 = new King(4, 7, 0);

        board.getBoard()[king1.getRow()][king1.getCol()].putPiece(king1);
        board.getBoard()[king2.getRow()][king2.getCol()].putPiece(king2);
        board.getBoard()[king3.getRow()][king3.getCol()].putPiece(king3);
        board.getBoard()[king4.getRow()][king4.getCol()].putPiece(king4);

        Assertions.assertEquals(5, king1.getMoves().size());
        Assertions.assertEquals(5, king2.getMoves().size());
        Assertions.assertEquals(5, king3.getMoves().size());
        Assertions.assertEquals(5, king4.getMoves().size());
    }

    @Test
    @DisplayName("Edge corners")
    void king_corners(){
        King king1 = new King(0, 0, 0);
        King king2 = new King(0, 7, 0);
        King king3 = new King(7, 0, 0);
        King king4 = new King(7, 7, 0);

        board.getBoard()[king1.getRow()][king1.getCol()].putPiece(king1);
        board.getBoard()[king2.getRow()][king2.getCol()].putPiece(king2);
        board.getBoard()[king3.getRow()][king3.getCol()].putPiece(king3);
        board.getBoard()[king4.getRow()][king4.getCol()].putPiece(king4);

        Assertions.assertEquals(3, king1.getMoves().size());
        Assertions.assertEquals(3, king2.getMoves().size());
        Assertions.assertEquals(3, king3.getMoves().size());
        Assertions.assertEquals(3, king4.getMoves().size());
    }

    @Test
    @DisplayName("Blocked")
    void king_blocked() {
        King king1 = new King(4, 4, 0);
        King king2 = new King(4, 3, 0);
        King king3 = new King(3, 3, 0);
        King king4 = new King(3, 4, 0);

        board.getBoard()[king1.getRow()][king1.getCol()].putPiece(king1);
        board.getBoard()[king2.getRow()][king2.getCol()].putPiece(king2);
        board.getBoard()[king3.getRow()][king3.getCol()].putPiece(king3);
        board.getBoard()[king4.getRow()][king4.getCol()].putPiece(king4);

        Assertions.assertEquals(5, king1.getMoves().size());
    }

    @Test
    @DisplayName("Take")
    void king_take(){
        King king1 = new King(4, 4, 0);
        Rook rook1 = new Rook(4,3, 1);
        Rook rook2 = new Rook(3, 4, 1);

        board.getBoard()[king1.getRow()][king1.getCol()].putPiece(king1);
        board.getBoard()[rook1.getRow()][rook1.getCol()].putPiece(rook1);
        board.getBoard()[rook2.getRow()][rook2.getCol()].putPiece(rook2);

        Assertions.assertEquals(8, king1.getMoves().size());

    }

}