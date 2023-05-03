public class Game {
    Board board;

    //TODO: implement game logic
    //TODO: castling - King.java, can probably just hard code, check left/right and use copy of board using isChecked() on squares king moves through
    public Game(){
        board = new Board(null);
    }

    public static void main(String[] args){
        new Game();
    }
}
