public class Game {
    static Board board;

    //TODO: implement game logic
    public Game(){
        board = Board.getInstance();
    }

    public static void main(String[] args){
        new Game();
    }
}
