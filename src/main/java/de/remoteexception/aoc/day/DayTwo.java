package de.remoteexception.aoc.day;

import de.remoteexception.aoc.util.Tuple;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static de.remoteexception.aoc.util.ResourceUtil.lines;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.filtering;
import static java.util.stream.Collectors.teeing;

class DayTwo {
    private static final Logger log = Logger.getLogger(DayTwo.class.getName());
    private static final Pattern PATTERN = Pattern.compile("(\\d+)-(\\d+)\\s(\\w):\\s(.+)");

    public static void main(String[] args) throws URISyntaxException, IOException {
        Tuple<Long> result = lines("daytwo.txt")
            .map(PolicyPassword::readPolicy).collect(teeing(
                filtering(PolicyPassword::isValidByOccurrence, counting()),
                filtering(PolicyPassword::isValidByNonEquality, counting()),
                Tuple::new));

        log.log(Level.INFO, "{0} valid occurrence passwords.", result.value1());
        log.log(Level.INFO, "{0} valid non equal passwords.", result.value2());
    }

    record PolicyPassword(Tuple<Integer> tuple, char letter, String password) {
        static PolicyPassword readPolicy(String line) {
            Matcher matcher = PATTERN.matcher(line);
            if (!matcher.find()) {
                throw new IllegalArgumentException("Pattern not matching for line '" + line + "'.");
            }
            int number1 = Integer.parseInt(matcher.group(1));
            int number2 = Integer.parseInt(matcher.group(2));
            char letter = matcher.group(3).charAt(0);
            String password = matcher.group(4);
            return new PolicyPassword(new Tuple<>(number1, number2), letter, password);
        }

        boolean isValidByOccurrence() {
            long count = password.chars().filter(c -> c == letter).count();
            return count >= tuple.value1() && count <= tuple.value2();
        }

        boolean isValidByNonEquality() {
            char[] chars = password.toCharArray();
            return chars[tuple.value1() - 1] == letter ^ chars[tuple.value2() - 1] == letter;
        }
    }
}
