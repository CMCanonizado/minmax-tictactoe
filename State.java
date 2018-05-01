import java.util.ArrayList;

public class State {
    public ArrayList<String> board;
    public int value;

    public State (ArrayList<String> board, int value) {
        this.board = board;
        this.value = value;
    }
}