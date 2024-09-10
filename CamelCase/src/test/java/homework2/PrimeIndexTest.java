package homework2;

import net.jqwik.api.*;
import net.jqwik.api.constraints.IntRange;
import net.jqwik.api.constraints.Size;
import net.jqwik.api.constraints.UniqueElements;
import net.jqwik.api.statistics.Histogram;
import net.jqwik.api.statistics.Statistics;
import net.jqwik.api.statistics.StatisticsReport;

import java.util.Collections;
import java.util.List;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;

class PrimeIndexTest {
    PrimeIndex primeIndex = new PrimeIndex();

    @Property(generation = GenerationMode.RANDOMIZED)
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void testInvalid(@ForAll @Size(max = 100) List<@IntRange(min = -200, max = -100) Integer> numbers,
                     @ForAll boolean findFirst) {

        assertThrows(IllegalArgumentException.class, () -> primeIndex.findPrimeIndex(numbers, findFirst));
    }

    @Property(generation = GenerationMode.RANDOMIZED)
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void testFail(@ForAll @Size(min = 1, max = 100) List<@IntRange(min = 4604, max = 4620) Integer> numbers,
                  @ForAll boolean findFirst) {

        int expected, actual;

        expected = -1;
        actual = primeIndex.findPrimeIndex(numbers, findFirst);

        assertEquals(expected, actual);
    }

    @Property(generation = GenerationMode.RANDOMIZED)
    @Report(Reporting.GENERATED)
    @StatisticsReport(format = Histogram.class)
    void testPass(@ForAll @Size(min = 20, max = 50) List<@IntRange(min = 4604, max = 4620) Integer> numbers,
                  @ForAll boolean findFirst,
                  @ForAll @Size(value = 3) @UniqueElements List<@IntRange(max = 19) Integer> indexesToAddPrimes,
                  @ForAll("primes") @Size(value = 3) List<Integer> primeToAdd) {

        int expected, actual;

        Collections.sort(indexesToAddPrimes); // Sort the list of indexes to add primes in ascending order

        int i, j;
        for (i = 0; i < indexesToAddPrimes.size(); i++) {
            numbers.add(indexesToAddPrimes.get(i), primeToAdd.get(i)); // Add primes to the list at the specified indexes
        }

        if (findFirst) {
            // If we want to find the first prime number, the expected index is the first index in the list
            expected = indexesToAddPrimes.get(0);
        } else {
            // If we want to find the last prime number, the expected index is the last index in the list
            expected = indexesToAddPrimes.get(indexesToAddPrimes.size() - 1);
        }

        actual = primeIndex.findPrimeIndex(numbers, findFirst);

        // Collecting statistics for the number of divisors of each number in the list and the divisors of each number
        int divisorCount;
        int number;

        for (i = 0; i < numbers.size(); i++) {
            divisorCount = 2; // Every number is divisible by 1 and itself, so the divisor count is at least 2
            number = numbers.get(i);
            for (j = 2; j <= Math.sqrt(number); j++) {
                // Check if j is a divisor of the given number
                if (number % j == 0) {
                    Statistics.label("Divisor").collect(j);
                    divisorCount = divisorCount + 1;
                }
            }

            Statistics.label("Number of divisors").collect(divisorCount);
        }

        assertEquals(expected, actual);
    }

    @Provide
    Arbitrary<List<Integer>> primes() {
        return Arbitraries.randomValue(this::generatePrime).list();
    }


    private Integer generatePrime(Random random) {
        int candidate;
        do {
            candidate = random.nextInt(10000) + 2;
            Statistics.collect(isPrime(candidate) ? "prime" : "nonPrime");
        } while (!isPrime(candidate));
        Statistics.label("Prime number").collect(candidate);
        return candidate;
    }

    private boolean isPrime(int number) {
        if (number <= 1) {
            return false;
        }
        for (int j = 2; j <= Math.sqrt(number); j++) {
            if (number % j == 0) {
                return false;
            }
        }
        return true;
    }
}