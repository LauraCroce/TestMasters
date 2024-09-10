package homework2;

import java.util.List;

public class PrimeIndex {

    public PrimeIndex() {

    }

    /**
     * Find the index of the first or last prime number in the list.
     * @param numbers the list of integer
     * @param findFirst true if we want to find the first prime number in the list,
     *                  false if we want to find the last prime number in the list
     * @return the index of the first or last prime number in the list
     *           -1 if no prime number is found.
     * @throws IllegalArgumentException if the list is null or empty, or if the list does not contain any positive number
     */
    public int findPrimeIndex(List<Integer> numbers, boolean findFirst) {
        if (numbers == null || numbers.isEmpty()) {
            throw new IllegalArgumentException("Invalid input"); // Invalid input
        }

        // Check if the list contains only non-positive numbers
        if (numbers.stream().allMatch(num -> num <= 0)) {
            throw new IllegalArgumentException("The list does not contain any positive numbers");
        }

        int index = -1;

        if (findFirst) {
            for (int i = 0; i < numbers.size(); i++) {
                int number = numbers.get(i);
                boolean isPrime = number > 1;
                for (int j = 2; isPrime && j <= Math.sqrt(number); j++) {
                    if (number % j == 0) {
                        isPrime = false;
                    }
                }
                if (isPrime) {
                    return i; // return the index immediately when we find the first prime number
                }
            }
        } else {
            for (int i = numbers.size() - 1; i >= 0; i--) {
                int number = numbers.get(i);
                boolean isPrime = number > 1;
                for (int j = 2; isPrime && j <= Math.sqrt(number); j++) {
                    if (number % j == 0) {
                        isPrime = false;
                    }
                }
                if (isPrime) {
                    return i; // return the index immediately when we find the last prime number
                }
            }
        }

        return index;
    }

}
