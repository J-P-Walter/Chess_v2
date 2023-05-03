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

    @Test
    void check_rook_up() {
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Rook blackRook = new Rook(0, 4, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackRook);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(6, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void check_rook_down() {
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Rook blackRook = new Rook(7, 4, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackRook);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(6, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void check_rook_left() {
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Rook blackRook = new Rook(4, 0, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackRook);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(6, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void check_rook_right() {
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Rook blackRook = new Rook(4, 7, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackRook);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(6, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void check_bishop_up_left() {
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Bishop blackBishop = new Bishop(1, 1, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(6, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void check_bishop_up_right() {
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Bishop blackBishop = new Bishop(1, 7, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(6, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void check_bishop_down_left() {
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Bishop blackBishop = new Bishop(7, 1, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(6, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void check_bishop_down_right() {
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Bishop blackBishop = new Bishop(7, 7, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(6, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void self_check(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(2, 4, 1);
        King whiteKing = new King(4, 4, 0);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(5, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void check_knight(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Knight blackKnight = new Knight(2, 5, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackKnight);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(7, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void check_pawn(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(0, 0, 1);
        King whiteKing = new King(4, 4, 0);

        Pawn blackPawn1 = new Pawn(3, 3, 1);
        Pawn blackPawn2 = new Pawn(2, 2, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackPawn1);
        blackPlayer.addPiece(blackPawn2);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(7, whitePlayer.getValidMoves(board.getBoard()).size());
    }

    @Test
    void checkmate(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(5, 4, 1);
        King whiteKing = new King(7, 4, 0);

        Queen blackQueen = new Queen(6, 4, 1);

        whitePlayer.addPiece(whiteKing);
        blackPlayer.addPiece(blackQueen);
        blackPlayer.addPiece(blackKing);

        whitePlayer.setKing(whiteKing);
        blackPlayer.setKing(blackKing);

        whitePlayer.placePieces(board.getBoard());
        blackPlayer.placePieces(board.getBoard());

        Assertions.assertEquals(0, whitePlayer.getValidMoves(board.getBoard()).size());
    }
}
