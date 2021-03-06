package part2.client;

import part2.task.Task;

import java.io.Serializable;
import java.math.BigInteger;

/**
 * Created by George on 2018-02-19.
 */
public class PrimeCalculatorTask implements Task<BigInteger>, Serializable {
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
        BigInteger maxPrime = BigInteger.ZERO;

        for (BigInteger i = lowerBound; i.compareTo(upperBound) <= 0; i = i.add(BigInteger.ONE)) {
            if (isPrimeWithinBounds(i)) {
                maxPrime = i;
            }
        }

        if (maxPrime.equals(BigInteger.ZERO)) {
            throw new NoPrimeFoundException(lowerBound, upperBound);
        }

        return maxPrime;
    }

    private boolean isPrimeWithinBounds(BigInteger number) {
        if (isTwo(number)) {
            return true;
        } else if (isLessThanTwo(number) || isEven(number)) {
            return false;
        }

        return isPrimeWithinBoundsHelper(number);
    }

    private boolean isEven(BigInteger number) {
        return number.mod(BigInteger.valueOf(2)).equals(BigInteger.ZERO);
    }

    private boolean isLessThanTwo(BigInteger number) {
        return number.compareTo(BigInteger.valueOf(2)) < 0;
    }

    private boolean isTwo(BigInteger number) {
        return number.equals(BigInteger.valueOf(2));
    }

    private boolean isDivisibleBy(BigInteger number, BigInteger divisor) {
        return number.mod(divisor).equals(BigInteger.ZERO);
    }

    private boolean isPrimeWithinBoundsHelper(BigInteger number) {
        BigInteger halfOfNumber = number.divide(BigInteger.valueOf(2));

        for (BigInteger i = BigInteger.valueOf(3); i.compareTo(halfOfNumber) < 0; i = i.add(BigInteger.valueOf(2))) {
            if (isDivisibleBy(number, i)) {
                return false;
            }
        }

        return true;
    }

    private static class NoPrimeFoundException extends RuntimeException {
        public NoPrimeFoundException(BigInteger lowerBound, BigInteger upperBound) {
            super(String.format("Could not find a prime number within %s and %s.", lowerBound, upperBound));
        }
    }
}
