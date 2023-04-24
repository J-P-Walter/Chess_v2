public class Game {
    static Board board;

    public Game(){
        board = Board.getInstance();
    }

    public static void main(String[] args){
        new Game();
    }
}
