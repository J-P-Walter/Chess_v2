import org.junit.jupiter.api.*;

public class CheckTest {
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

}
