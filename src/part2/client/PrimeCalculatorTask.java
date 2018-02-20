package part2.client;

import part2.task.Task;

import java.math.BigInteger;

/**
 * Created by George on 2018-02-19.
 */
public class PrimeCalculatorTask implements Task<BigInteger> {
    private BigInteger lowerBound;
    private BigInteger upperBound;

    public PrimeCalculatorTask(int lowerBound, int upperBound) {
        this(BigInteger.valueOf(lowerBound), BigInteger.valueOf(upperBound));
    }

    public PrimeCalculatorTask(BigInteger lowerBound, BigInteger upperBound) {
        this.lowerBound = lowerBound;
        this.upperBound = upperBound;
    }

    @Override
    public BigInteger execute() {
        return BigInteger.ZERO;
    }
}
