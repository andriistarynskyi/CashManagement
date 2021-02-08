package utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class ReadDataFromFile {

    public static List<String> get(String path) {
        List<String> dataListFromFile = new ArrayList<>();
        try (BufferedReader reader = Files.newBufferedReader(Paths.get(path))) {
            int iteration = 0;
            String line;
            while ((line = reader.readLine()) != null) {
                if (iteration == 0) {
                    iteration++;
                    continue;
                } else {
                    dataListFromFile.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return dataListFromFile;
    }
}
