package de.remoteexception.aoc.day;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.NoSuchElementException;
import java.util.logging.Level;
import java.util.logging.Logger;

import static de.remoteexception.aoc.util.ResourceUtil.lines;

public class DayOne {
    private static final Logger log = Logger.getLogger(DayOne.class.getName());

    public static void main(String[] args) throws URISyntaxException, IOException {
        Integer[] numbers = lines("dayone.txt")
            .map(Integer::valueOf).toArray(Integer[]::new);

        log.log(Level.INFO, "Product of two numbers: {0}", getProductOfTwo(numbers));
        log.log(Level.INFO, "Product of three numbers: {0}", getProductOfThree(numbers));
    }

    private static int getProductOfTwo(Integer[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length && i != j; j++) {
                if (numbers[i] + numbers[j] == 2020) {
                    return numbers[i] * numbers[j];
                }
            }
        }
        throw new NoSuchElementException("Didn't find two numbers that sum up to 2020.");
    }

    private static int getProductOfThree(Integer[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = 0; j < numbers.length && i != j; j++) {
                for (int k = 0; k < numbers.length && i != k && j != k; k++) {
                    if (numbers[i] + numbers[j] + numbers[k] == 2020) {
                        return numbers[i] * numbers[j] * numbers[k];
                    }
                }
            }
        }
        throw new NoSuchElementException("Didn't find three numbers that sum up to 2020.");
    }
}
