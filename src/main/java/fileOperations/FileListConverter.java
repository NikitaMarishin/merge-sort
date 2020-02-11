package fileOperations;

import operations.Parser;

import java.io.*;
import java.util.List;

public class FileListConverter<T> {
    private Parser<T> parser;

    public void fileToList(File fileToConvert, List<T> result) {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileToConvert))) {
            String tempLine;
            while ((tempLine = reader.readLine()) != null) {
                result.add(parser.parse(tempLine));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void listToFile(List<T> listToConvert, File resultFile) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(resultFile))) {
            for(T data: listToConvert) {
                writer.write(data.toString());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }
}
