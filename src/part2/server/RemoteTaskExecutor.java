package part2.server;

import part2.task.Task;
import part2.task.TaskExecutor;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * title: RemoteTaskExecutor.java
 * description: The main entry remote method execution server.
 * date: February 28, 2018
 * @author George Antonious
 * @version 1.0
 * @copyright 2018 George Antonious
 *
 * I declare that this assignment is my own work and that all material
 * previously written or published in any source by any other person
 * has been duly acknowledged in the assignment. I have not submitted
 * this work, or a significant part thereof, previously as part of any
 * academic program. In submitting this assignment I give permission to
 * copy it for assessment purposes only.
 *
 * The usage, design, and test plan for this part can be found in the
 * README.md file in the root of this project. It is recommended to view
 * it in a markdown reader.
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
            System.out.println("RemoteTaskExecutor ready.");
        } catch (Exception e) {
            System.err.println("RemoteTaskExecutor initialization failed.");
            e.printStackTrace();
        }
    }

    @Override
    public <T> T executeTask(Task<T> task) {
        return task.execute();
    }
}
