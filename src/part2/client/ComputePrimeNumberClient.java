package part2.client;

import part2.server.RemoteTaskExecutor;

import java.io.Console;
import java.math.BigInteger;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Created by George on 2018-02-19.
 */
public class ComputePrimeNumberClient {

    public void main(String[] args) {
        if (System.getSecurityManager() == null) {
            System.setSecurityManager(new SecurityManager());
        }
        try {
            String name = "RemoteTaskExecutor";
            Registry registry = LocateRegistry.getRegistry();
            RemoteTaskExecutor remoteTaskExecutor = (RemoteTaskExecutor) registry.lookup(name);

            Console console = System.console();
            int lowerBound = Integer.parseInt(console.readLine("Lower bound: "));
            int upperBound = Integer.parseInt(console.readLine("Upper bound: "));

            BigInteger result = remoteTaskExecutor.executeTask(new PrimeCalculatorTask(lowerBound, upperBound));
            System.out.printf("Largest Prime in Range: %s\n", result.toString());

        } catch (Exception e) {
            System.err.println("ComputePi exception:");
            e.printStackTrace();
        }
    }
}
