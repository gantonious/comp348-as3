package part1.state;

/**
 * Created by George on 2017-12-28.
 */
public class StateMachine<TState> {
    private TState state;

    public StateMachine(TState state) {
        this.state = state;
    }

    public TState getState() {
        return state;
    }

    public void setStateTo(IState<TState> nextState) {
        nextState.handle(this);
    }

    public void finish() {

    }
}
