public class State {

    // Action used to get to this state
    public String action;
    
    public int value;

    public State (String action, int value) {
        this.action = action;
        this.value = value;
    }
}