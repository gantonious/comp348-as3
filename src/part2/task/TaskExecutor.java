package part2.task;

import java.rmi.Remote;

/**
 * Created by George on 2018-02-19.
 */
public interface TaskExecutor extends Remote {
    <T> T executeTask(Task<T> task);
}
