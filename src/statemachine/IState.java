package statemachine;

/**
 * Created by George on 2017-12-28.
 */
public interface IState<TState> {
    void handle(StateMachine<TState> stateMachine);
}
