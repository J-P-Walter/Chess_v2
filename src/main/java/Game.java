public class Game {
    Board board;

    //TODO: implement game logic
    //TODO: validate moves - Player.java, implement isChecked() on all moves, need to make copy of board https://stackoverflow.com/questions/51168836/is-it-possible-to-shallow-copy-a-singleton-class-object
    //TODO: pawn promotion, en passant - Pawn.java or Board.java
    //TODO: castling - King.java, can probably just hard code, check left/right and use copy of board using isChecked() on squares king moves through
    public Game(){
        board = new Board(null);
    }

    public static void main(String[] args){
        new Game();
    }
}
