import org.junit.jupiter.api.*;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

class QueenTest {
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
    void queen_center(){
        Queen queen = new Queen(4, 4, 0);
        board.getBoard()[4][4].putPiece(queen);

        Assertions.assertEquals(27, queen.getMoves().size());
    }

    @Test
    @DisplayName("Edges")
    void queen_edges(){
        Queen queen1 = new Queen(0, 3, 0);
        Queen queen2 = new Queen(4, 0, 0);
        Queen queen3 = new Queen(7, 4, 0);
        Queen queen4 = new Queen(3, 7, 0);

        board.getBoard()[queen1.getRow()][queen1.getCol()].putPiece(queen1);
        board.getBoard()[queen2.getRow()][queen2.getCol()].putPiece(queen2);
        board.getBoard()[queen3.getRow()][queen3.getCol()].putPiece(queen3);
        board.getBoard()[queen4.getRow()][queen4.getCol()].putPiece(queen4);

        Assertions.assertEquals(21, queen1.getMoves().size());
        Assertions.assertEquals(21, queen2.getMoves().size());
        Assertions.assertEquals(21, queen3.getMoves().size());
        Assertions.assertEquals(21, queen4.getMoves().size());
    }

    @Test
    @DisplayName("Corners")
    void queen_corners(){
        Queen queen1 = new Queen(0, 0, 0);
        Queen queen2 = new Queen(0, 7, 0);
        Queen queen3 = new Queen(7, 0, 0);
        Queen queen4 = new Queen(7, 7, 0);

        board.getBoard()[queen1.getRow()][queen1.getCol()].putPiece(queen1);
        board.getBoard()[queen2.getRow()][queen2.getCol()].putPiece(queen2);
        board.getBoard()[queen3.getRow()][queen3.getCol()].putPiece(queen3);
        board.getBoard()[queen4.getRow()][queen4.getCol()].putPiece(queen4);

        Assertions.assertEquals(18, queen1.getMoves().size());
        Assertions.assertEquals(18, queen2.getMoves().size());
        Assertions.assertEquals(18, queen3.getMoves().size());
        Assertions.assertEquals(18, queen4.getMoves().size());
    }

    @Test
    @DisplayName("Blocked")
    void queen_blocked(){
        Queen queen1 = new Queen(4, 4, 0);
        Queen queen2 = new Queen(2, 4, 0);
        Queen queen3 = new Queen(2, 6, 0);
        Queen queen4 = new Queen(3, 3, 0);
        Queen queen5 = new Queen(4, 7, 0);
        Queen queen6 = new Queen(7, 7, 0);


        board.getBoard()[queen1.getRow()][queen1.getCol()].putPiece(queen1);
        board.getBoard()[queen2.getRow()][queen2.getCol()].putPiece(queen2);
        board.getBoard()[queen3.getRow()][queen3.getCol()].putPiece(queen3);
        board.getBoard()[queen4.getRow()][queen4.getCol()].putPiece(queen4);
        board.getBoard()[queen5.getRow()][queen5.getCol()].putPiece(queen5);
        board.getBoard()[queen6.getRow()][queen6.getCol()].putPiece(queen6);

        Assertions.assertEquals(16, queen1.getMoves().size());
    }

    @Test
    @DisplayName("Take")
    void queen_take(){
        Queen queen1 = new Queen(4, 4, 0);
        Queen queen2 = new Queen(2, 4, 1);
        Queen queen3 = new Queen(2, 6, 1);
        Queen queen4 = new Queen(3, 3, 1);
        Queen queen5 = new Queen(4, 7, 1);
        Queen queen6 = new Queen(7, 7, 1);


        board.getBoard()[queen1.getRow()][queen1.getCol()].putPiece(queen1);
        board.getBoard()[queen2.getRow()][queen2.getCol()].putPiece(queen2);
        board.getBoard()[queen3.getRow()][queen3.getCol()].putPiece(queen3);
        board.getBoard()[queen4.getRow()][queen4.getCol()].putPiece(queen4);
        board.getBoard()[queen5.getRow()][queen5.getCol()].putPiece(queen5);
        board.getBoard()[queen6.getRow()][queen6.getCol()].putPiece(queen6);

        Assertions.assertEquals(21, queen1.getMoves().size());
    }
}