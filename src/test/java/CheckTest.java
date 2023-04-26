import org.junit.jupiter.api.*;

public class CheckTest {
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
    @DisplayName("Testing pin")
    void pin_test() {
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);
        board.setupBoard("./data/check_test_1.txt", whitePlayer, blackPlayer);

        Assertions.assertEquals(7, whitePlayer.getValidMoves().size());

    }

}
