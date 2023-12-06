package org.beast4ever.aoc.aoc2k23;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@Component
public class FileUtilityParser {
    public String readFirstLine(String fileName) throws IOException {
        File inputFile = getFile(fileName);
        List<String> lines = Files.readAllLines(inputFile.toPath());
        return lines.get(0);
    }

    public List<String> readFileSplitByLines(String fileName) throws IOException {
        File inputFile = getFile(fileName);
        List<String> lines = Files.readAllLines(inputFile.toPath());
        return lines;
    }

    public String readAllContentFile(URI filePath) throws IOException {
        String content = Files.readString(Path.of(filePath));
        return content;
    }

    private File getFile(String fileName) throws IOException
    {
        ClassLoader classLoader = getClass().getClassLoader();
        URL resource = classLoader.getResource(fileName);

        if (resource == null) {
            throw new IllegalArgumentException("file is not found!");
        } else {
            return new File(resource.getFile());
        }
    }

}
