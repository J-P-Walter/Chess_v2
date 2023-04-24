import org.junit.jupiter.api.*;

class RookTest {
    private static Board board = Board.getInstance();

    @BeforeEach
    void setUp(){
        board.resetBoard();
    }

    @AfterEach
    void print(){ board.printBoard(); }

    @Test
    @DisplayName("Center of board")
    void rook_center(){
        Rook rook = new Rook(4, 4, 0);
        board.getBoard()[4][4].putPiece(rook);
    }

}