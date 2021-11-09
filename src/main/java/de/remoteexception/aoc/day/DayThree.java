package de.remoteexception.aoc.day;

import de.remoteexception.aoc.util.Tuple;

import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Stream;

import static de.remoteexception.aoc.util.ResourceUtil.lines;
import static java.util.logging.Level.INFO;

class DayThree {
    private static final Logger logger = Logger.getLogger(DayThree.class.getName());

    public static void main(String[] args) {
        logger.log(INFO, "Encountered {0} trees", getTrees(new Tuple<>(3, 1)));

        Long product = Stream.of(new Tuple<>(1, 1), new Tuple<>(3, 1), new Tuple<>(5, 1), new Tuple<>(7, 1), new Tuple<>(1, 2))
            .map(t -> getTrees(t).longValue())
            .reduce(1L, (a, b) -> a * b);

        logger.log(INFO, "Product is {0}", product);
    }

    private static Integer getTrees(Tuple<Integer> steps) {
        int x = 0;
        int y = steps.value2();
        int trees = 0;

        List<String> rows = lines("daythree.txt").skip(steps.value2()).toList();
        int length = rows.get(0).length();
        for (String line : rows) {
            if (y++ % steps.value2() != 0) {
                continue;
            }
            x = (x + steps.value1()) % length;
            char[] chars = line.toCharArray();
            if (chars[x] == '#') {
                trees++;
            }
        }
        return trees;
    }
}
