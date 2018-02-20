package part2.server;

import part2.task.Task;
import part2.task.TaskExecutor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Created by George on 2018-02-19.
 */
public class RemoteTaskExecutor implements TaskExecutor {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "RemoteTaskExecutor";
            RemoteTaskExecutor taskExecutor = new RemoteTaskExecutor();
            TaskExecutor stub = (TaskExecutor) UnicastRemoteObject.exportObject(taskExecutor, 0);
            Registry registry = LocateRegistry.getRegistry();
            registry.rebind(name, stub);
            System.out.println("RemoteTaskExecutor bound");
        } catch (Exception e) {
            System.err.println("RemoteTaskExecutor exception:");
            e.printStackTrace();
        }
    }

    @Override
    public <T> T executeTask(Task<T> task) {
        return task.execute();
    }
}
