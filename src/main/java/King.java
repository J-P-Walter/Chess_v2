import java.util.ArrayList;

//TODO: implement castling
//TODO: implement isChecked method

public class King extends Piece{
    private char name = 'K';
    public King(int row, int col, int color) { super(row, col, color); }

    /*
        Kings can move both orthogonal and diagonal but only once, simply checks each of the eight squares
     */
    @Override
    public ArrayList<int[]> getMoves(){
        Board board = Board.getInstance();
        ArrayList<int[]> validMoves = new ArrayList<>();

        //Left
        if (col-1 >= 0 && !board.getBoard()[row][col-1].isOccupied()){
            validMoves.add(new int[]{row, col, row, col-1});
        }
        else if (col-1 >= 0 && board.getBoard()[row][col-1].getPiece().getColor() != color){
            validMoves.add(new int[]{row, col, row, col-1});
        }

        //Right
        if (col+1 < board.getBoard().length && !board.getBoard()[row][col+1].isOccupied()){
            validMoves.add(new int[]{row, col, row, col+1});
        }
        else if (col+1 < board.getBoard().length && board.getBoard()[row][col+1].getPiece().getColor() != color){
            validMoves.add(new int[]{row, col, row, col+1});
        }

        //Up
        if (row-1 >= 0 && !board.getBoard()[row-1][col].isOccupied()){
            validMoves.add(new int[]{row, col, row-1, col});
        }
        else if (row-1 >= 0 && board.getBoard()[row-1][col].getPiece().getColor() != color){
            validMoves.add(new int[]{row, col, row-1, col});
        }

        //Down
        if (row+1 < board.getBoard().length && !board.getBoard()[row + 1][col].isOccupied()){
            validMoves.add(new int[]{row, col, row+1, col});
        }
        else if (row+1 < board.getBoard().length && board.getBoard()[row+1][col].getPiece().getColor() != color){
            validMoves.add(new int[]{row, col, row+1, col});
        }

        //Up-left
        if (row-1 >= 0 && col-1 >= 0){
            if (!board.getBoard()[row - 1][col - 1].isOccupied()){
                validMoves.add(new int[]{row, col, row-1, col-1});
            }
            else if (board.getBoard()[row-1][col-1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-1, col-1});
            }
        }

        //Up-right
        if (row-1 >= 0 && col+1 < board.getBoard().length){
            if (!board.getBoard()[row - 1][col + 1].isOccupied()){
                validMoves.add(new int[]{row, col, row-1, col+1});
            }
            else if (board.getBoard()[row-1][col+1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row-1, col+1});
            }
        }

        //Down-left
        if (row+1 < board.getBoard().length && col-1 >= 0){
            if (!board.getBoard()[row + 1][col - 1].isOccupied()){
                validMoves.add(new int[]{row, col, row+1, col-1});
            }
            else if (board.getBoard()[row+1][col-1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+1, col-1});
            }
        }

        //Down-right
        if (row+1 < board.getBoard().length && col+1 < board.getBoard().length){
            if (!board.getBoard()[row + 1][col + 1].isOccupied()){
                validMoves.add(new int[]{row, col, row+1, col+1});
            }
            else if (board.getBoard()[row+1][col+1].getPiece().getColor() != color){
                validMoves.add(new int[]{row, col, row+1, col+1});
            }
        }
        return validMoves;
    }

    /*
    Checks every direction the king could be attacked from
    If an attacker is found, checks the type to make sure it can actually check the king
        ex. a rook on a diagonal cannot, but a bishop can
    To be used to determine if a player is in check and if moves are allowed
        ex. you cannot put yourself in check
*/
    public boolean isChecked(Board checkBoard, int color){
        //Left
        int left = 1;
        while (col-left >= 0){
            Square currSquare = checkBoard.getBoard()[row][col - left];
            if (currSquare.isOccupied()){
                if (currSquare.getPiece().getColor() != color){
                    if (    currSquare.getPiece().getName() == 'R'
                            || currSquare.getPiece().getName() == 'Q'
                            || currSquare.getPiece().getName() == 'K'){
                        return true;
                    }
                    else { break; }
                }
                else { break; }
            }
            left++;
        }

        //Right
        int right = 1;
        while (col+right < checkBoard.getBoard().length){
            Square currSquare = checkBoard.getBoard()[row][col + right];
            if (currSquare.isOccupied()){
                if (currSquare.getPiece().getColor() != color){
                    if (    currSquare.getPiece().getName() == 'R'
                            || currSquare.getPiece().getName() == 'Q'
                            || currSquare.getPiece().getName() == 'K'){
                        return true;
                    } else { break; }
                } else { break; }
            }
            right++;
        }

        //Up
        int up = 1;
        while (row-up >= 0){
            Square currSquare = checkBoard.getBoard()[row - up][col];
            if (currSquare.isOccupied()){
                if (currSquare.getPiece().getColor() != color){
                    if (    currSquare.getPiece().getName() == 'R'
                            || currSquare.getPiece().getName() == 'Q'
                            || currSquare.getPiece().getName() == 'K'){
                        return true;
                    } else { break; }
                } else { break; }
            }
            up++;
        }

        //Down
        int down = 1;
        while (row+down < checkBoard.getBoard().length){
            Square currSquare = checkBoard.getBoard()[row + down][col];
            if (currSquare.isOccupied()){
                if (currSquare.getPiece().getColor() != color){
                    if (    currSquare.getPiece().getName() == 'R'
                            || currSquare.getPiece().getName() == 'Q'
                            || currSquare.getPiece().getName() == 'K'){
                        return true;
                    } else { break; }
                } else { break; }
            }
            down++;
        }

        //Up-left
        int idx = 1;
        while (row-idx >= 0 && col-idx >= 0){
            Square currSquare = checkBoard.getBoard()[row-idx][col-idx];
            if (currSquare.isOccupied()){
                if (currSquare.getPiece().getColor() != color){
                    if (    currSquare.getPiece().getName() == 'B'
                            || currSquare.getPiece().getName() == 'Q'
                            || currSquare.getPiece().getName() == 'K'){
                        return true;
                    } else { break; }
                } else { break; }
            }
            idx++;
        }

        //Up-right
        idx = 1;
        while (row-idx >= 0 && col+idx < checkBoard.getBoard().length){
            Square currSquare = checkBoard.getBoard()[row - idx][col + idx];
            if (currSquare.isOccupied()){
                if (currSquare.getPiece().getColor() != color){
                    if (    currSquare.getPiece().getName() == 'B'
                            || currSquare.getPiece().getName() == 'Q'
                            || currSquare.getPiece().getName() == 'K'){
                        return true;
                    } else { break; }
                } else { break; }
            }
            idx++;
        }

        //Down-left
        idx = 1;
        while (row+idx < checkBoard.getBoard().length && col-idx >= 0) {
            Square currSquare = checkBoard.getBoard()[row + idx][col - idx];
            if (currSquare.isOccupied()){
                if (currSquare.getPiece().getColor() != color){
                    if (    currSquare.getPiece().getName() == 'B'
                            || currSquare.getPiece().getName() == 'Q'
                            || currSquare.getPiece().getName() == 'K'){
                        return true;
                    } else { break; }
                } else { break; }
            }
            idx++;
        }

        //Down-right
        idx = 1;
        while (row+idx < checkBoard.getBoard().length && col+idx < checkBoard.getBoard().length){
            Square currSquare = checkBoard.getBoard()[row + idx][col + idx];
            if (currSquare.isOccupied()){
                if (currSquare.getPiece().getColor() != color){
                    if (    currSquare.getPiece().getName() == 'B'
                            || currSquare.getPiece().getName() == 'Q'
                            || currSquare.getPiece().getName() == 'K'){
                        return true;
                    } else { break; }
                } else { break; }
            }
            idx++;
        }

        //Knight moves
        //up-up-left
        if (row-2 >= 0 && col-1 >= 0) {
            Square currSquare = checkBoard.getBoard()[row - 2][col - 1];
            if (currSquare.isOccupied()) {
                if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'N') {
                        return true;
                }
            }
        }

        //up-up-right
        if (row-2 >= 0 && col+1 < checkBoard.getBoard().length){
            Square currSquare = checkBoard.getBoard()[row - 2][col + 1];
            if (currSquare.isOccupied()) {
                if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'N') {
                    return true;
                }
            }
        }

        //down-down-left
        if (row+2 < checkBoard.getBoard().length && col-1 >= 0){
            Square currSquare = checkBoard.getBoard()[row + 2][col - 1];
            if (currSquare.isOccupied()) {
                if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'N') {
                    return true;
                }
            }
        }

        //down-down-right
        if (row+2 < checkBoard.getBoard().length && col+1 < checkBoard.getBoard().length){
            Square currSquare = checkBoard.getBoard()[row + 2][col + 1];
            if (currSquare.isOccupied()) {
                if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'N') {
                    return true;
                }
            }
        }

        //up-left-left
        if (row-1 >= 0 && col-2 >= 0){
            Square currSquare = checkBoard.getBoard()[row - 1][col - 2];
            if (currSquare.isOccupied()) {
                if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'N') {
                    return true;
                }
            }
        }

        //up-right-right
        if (row-1 >= 0 && col+2 < checkBoard.getBoard().length){
            Square currSquare = checkBoard.getBoard()[row - 1][col + 2];
            if (currSquare.isOccupied()) {
                if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'N') {
                    return true;
                }
            }
        }

        //down-left-left
        if (row+1 < checkBoard.getBoard().length && col-2 >= 0){
            Square currSquare = checkBoard.getBoard()[row + 1][col - 2];
            if (currSquare.isOccupied()) {
                if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'N') {
                    return true;
                }
            }
        }

        //down-right-right
        if (row+1 < checkBoard.getBoard().length && col+2 < checkBoard.getBoard().length){
            Square currSquare = checkBoard.getBoard()[row + 1][col + 2];
            if (currSquare.isOccupied()) {
                if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'N') {
                    return true;
                }
            }
        }

        //Pawn moves
        if (color == 0){
            //Up-left
            if (row-1 >= 0 && col-1 >= 0) {
                Square currSquare = checkBoard.getBoard()[row - 1][col - 1];
                if (currSquare.isOccupied()) {
                    if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'P') {
                        return true;
                    }
                }
            }
            //Up-right
            if (row-1 >= 0 && col+1 < checkBoard.getBoard().length){
                Square currSquare = checkBoard.getBoard()[row - 1][col + 1];
                if (currSquare.isOccupied()) {
                    if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'P') {
                        return true;
                    }
                }
            }
        }
        if (color == 1){
            //Down-left
            if (row+1 < checkBoard.getBoard().length && col-1 >= 0) {
                Square currSquare = checkBoard.getBoard()[row + 1][col - 1];
                if (currSquare.isOccupied()) {
                    if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'P') {
                        return true;
                    }
                }
            }
            //Down-right
            if (row+1 < checkBoard.getBoard().length && col+1 < checkBoard.getBoard().length){
                Square currSquare = checkBoard.getBoard()[row + 1][col + 1];
                if (currSquare.isOccupied()) {
                    if (currSquare.getPiece().getColor() != color && currSquare.getPiece().getName() == 'P') {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public char getName() {
        return name;
    }
}



