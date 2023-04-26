public class Game {
    static Board board;
    public Player whitePlayer = new Player(0);
    public Player blackPlayer = new Player(1);

    //TODO: implement game logic
    public Game(){
        board = Board.getInstance();

    }

    public static void main(String[] args){
        new Game();
    }
}
