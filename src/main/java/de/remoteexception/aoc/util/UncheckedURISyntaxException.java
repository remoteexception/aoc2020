package de.remoteexception.aoc.util;

import java.net.URISyntaxException;

public class UncheckedURISyntaxException extends RuntimeException {
    public UncheckedURISyntaxException(URISyntaxException e) {
        super(e);
    }
}
