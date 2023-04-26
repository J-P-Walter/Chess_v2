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

    @Test
    @DisplayName("Testing king check left")
    void king_check_left(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);
        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Rook blackRook = new Rook(4, 1, 1);
        blackPlayer.addPiece(blackRook);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check left is blocked by own piece")
    void king_check_left_blocked(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);
        Rook whiteRook = new Rook(4, 2, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.addPiece(whiteRook);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Rook blackRook = new Rook(4, 1, 1);
        blackPlayer.addPiece(blackRook);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check left where opponent piece can't threaten")
    void king_check_left_wrong_piece(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(4, 1, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing king check right")
    void king_check_right(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(3,3, 0);
        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Rook blackRook = new Rook(3, 6, 1);
        blackPlayer.addPiece(blackRook);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check right is blocked by own piece")
    void king_check_right_blocked(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(3,3, 0);
        Rook whiteRook = new Rook(3, 5, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.addPiece(whiteRook);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Rook blackRook = new Rook(3, 6, 1);
        blackPlayer.addPiece(blackRook);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check right where opponent piece can't threaten")
    void king_check_right_wrong_piece(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(3,3, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(3, 6, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing king check up")
    void king_check_up(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);
        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Rook blackRook = new Rook(1, 4, 1);
        blackPlayer.addPiece(blackRook);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check up is blocked by own piece")
    void king_check_up_blocked(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);
        Rook whiteRook = new Rook(3, 4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.addPiece(whiteRook);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Rook blackRook = new Rook(1, 4, 1);
        blackPlayer.addPiece(blackRook);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check up where opponent piece can't threaten")
    void king_check_up_wrong_piece(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(1, 4, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing king check down")
    void king_check_down(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(3,3, 0);
        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Rook blackRook = new Rook(6, 3, 1);
        blackPlayer.addPiece(blackRook);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check down is blocked by own piece")
    void king_check_down_blocked(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(3,3, 0);
        Rook whiteRook = new Rook(4, 3, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.addPiece(whiteRook);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Rook blackRook = new Rook(6, 3, 1);
        blackPlayer.addPiece(blackRook);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check down where opponent piece can't threaten")
    void king_check_down_wrong_piece(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(3,3, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(6, 3, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check up left")
    void king_check_up_left(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(6,6, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(2, 2, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check up left is blocked by own piece")
    void king_check_up_left_blocked(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(6,6, 0);
        Pawn whitePawn = new Pawn(4, 4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.addPiece(whitePawn);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(2, 2, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check up left where opponent piece can't threaten")
    void king_check_up_left_wrong_piece(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(6,6, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Pawn blackPawn = new Pawn(2, 2, 1);
        blackPlayer.addPiece(blackPawn);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check up right")
    void king_check_up_right(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(6,1, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(2, 5, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check up right is blocked by own piece")
    void king_check_up_right_blocked(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(6,1, 0);
        Pawn whitePawn = new Pawn(4, 3, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.addPiece(whitePawn);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(2, 5, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check up right where opponent piece can't threaten")
    void king_check_up_right_wrong_piece(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(6,1, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Pawn blackPawn = new Pawn(2, 5, 1);
        blackPlayer.addPiece(blackPawn);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check down left")
    void king_check_down_left(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(2,5, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(6, 1, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check down left is blocked by own piece")
    void king_check_down_left_blocked(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(2,5, 0);
        Pawn whitePawn = new Pawn(4, 3, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.addPiece(whitePawn);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(6, 1, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check down left where opponent piece can't threaten")
    void king_check_down_left_wrong_piece(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(2,5, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Pawn blackPawn = new Pawn(6, 1, 1);
        blackPlayer.addPiece(blackPawn);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check down right")
    void king_check_down_right(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(1,1, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(6, 6, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check down right is blocked by own piece")
    void king_check_down_right_blocked(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(1,1, 0);
        Pawn whitePawn = new Pawn(4, 4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.addPiece(whitePawn);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Bishop blackBishop = new Bishop(6, 6, 1);
        blackPlayer.addPiece(blackBishop);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing check down right where opponent piece can't threaten")
    void king_check_down_right_wrong_piece(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(1,1, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Pawn blackPawn = new Pawn(6, 6, 1);
        blackPlayer.addPiece(blackPawn);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    //KNIGHT MOVES
    @Test
    @DisplayName("Testing up up left knight move")
    void king_check_up_up_left_knight(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Knight blackKnight = new Knight(2, 3, 1);
        blackPlayer.addPiece(blackKnight);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing up up right knight move")
    void king_check_up_up_right_knight(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Knight blackKnight = new Knight(2, 5, 1);
        blackPlayer.addPiece(blackKnight);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing down down left knight move")
    void king_check_down_down_left_knight(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Knight blackKnight = new Knight(6, 3, 1);
        blackPlayer.addPiece(blackKnight);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing down down right knight move")
    void king_check_down_down_right_knight(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Knight blackKnight = new Knight(6, 5, 1);
        blackPlayer.addPiece(blackKnight);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing up left left knight move")
    void king_check_up_left_left_knight(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Knight blackKnight = new Knight(3, 2, 1);
        blackPlayer.addPiece(blackKnight);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing up right right knight move")
    void king_check_up_right_right_knight(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Knight blackKnight = new Knight(3, 6, 1);
        blackPlayer.addPiece(blackKnight);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing down left left knight move")
    void king_check_down_left_left_knight(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Knight blackKnight = new Knight(5, 2, 1);
        blackPlayer.addPiece(blackKnight);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing down right right knight move")
    void king_check_down_right_right_knight(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Knight blackKnight = new Knight(5, 6, 1);
        blackPlayer.addPiece(blackKnight);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing valid black pawn check")
    void king_check_from_black_pawn(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Pawn blackPawn = new Pawn(3, 3, 1);
        blackPlayer.addPiece(blackPawn);
        blackPlayer.placePieces();

        Assertions.assertTrue(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing non-pawn check")
    void king_non_check_from_black_pawn(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King whiteKing = new King(4,4, 0);

        whitePlayer.addPiece(whiteKing);
        whitePlayer.placePieces();
        whitePlayer.setKing(whiteKing);

        Pawn blackPawn = new Pawn(4, 3, 1);
        blackPlayer.addPiece(blackPawn);
        blackPlayer.placePieces();

        Assertions.assertFalse(whitePlayer.getKing().isChecked(board, whitePlayer.getColor()));
    }

    @Test
    @DisplayName("Testing valid white pawn check")
    void king_check_from_white_pawn(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(4,4, 1);

        blackPlayer.addPiece(blackKing);
        blackPlayer.placePieces();
        blackPlayer.setKing(blackKing);

        Pawn whitePawn = new Pawn(5, 3, 0);
        whitePlayer.addPiece(whitePawn);
        whitePlayer.placePieces();

        Assertions.assertTrue(blackPlayer.getKing().isChecked(board, blackPlayer.getColor()));
    }

    @Test
    @DisplayName("Testing non-pawn check")
    void king_non_check_from_white_pawn(){
        Player whitePlayer = new Player(0);
        Player blackPlayer = new Player(1);

        King blackKing = new King(4,4, 1);

        blackPlayer.addPiece(blackKing);
        blackPlayer.placePieces();
        blackPlayer.setKing(blackKing);

        Pawn whitePawn = new Pawn(4, 5, 0);
        whitePlayer.addPiece(whitePawn);
        whitePlayer.placePieces();

        Assertions.assertFalse(blackPlayer.getKing().isChecked(board, blackPlayer.getColor()));
    }
}