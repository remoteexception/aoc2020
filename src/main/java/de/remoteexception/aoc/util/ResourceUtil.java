package de.remoteexception.aoc.util;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ResourceUtil {

    private ResourceUtil() {
        // Util class
    }

    public static Stream<String> lines(String name) throws URISyntaxException, IOException {
        URI uri = ClassLoader.getSystemResource(name).toURI();
        return Files.readAllLines(Paths.get(uri)).stream();
    }
}
