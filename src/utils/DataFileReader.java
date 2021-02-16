package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DataFileReader {

    public static List<String> getDataFromFile(String path) {
        List<String> dataListFromFile = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            Stream<String> lines = reader.lines().skip(1);
            dataListFromFile = lines.collect(Collectors.toList());

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
        return dataListFromFile;
    }
}