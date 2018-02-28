package part2.client;

import part2.task.TaskExecutor;

import java.io.Console;
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * title: ComputePrimeNumberClient.java
 * description: The main entry for the prime calculator client.
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
public class ComputePrimeNumberClient {

    public static void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "RemoteTaskExecutor";
            Registry registry = LocateRegistry.getRegistry(args[0]);
            TaskExecutor remoteTaskExecutor = (TaskExecutor) registry.lookup(name);

            System.out.println("Connected to Remote Execution Server.");
            Console console = System.console();
            int lowerBound = Integer.parseInt(console.readLine("Lower bound: "));
            int upperBound = Integer.parseInt(console.readLine("Upper bound: "));

            BigInteger result = remoteTaskExecutor.executeTask(new PrimeCalculatorTask(lowerBound, upperBound));
            System.out.printf("Largest Prime in Range: %s\n", result.toString());

        } catch (Exception e) {
            System.err.println("Compute prime number exception:");
            e.printStackTrace();
        }
    }
}
