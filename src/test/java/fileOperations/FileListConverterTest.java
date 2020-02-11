package fileOperations;

import control.Configurator;
import control.TempFilesFabric;
import operations.ParserInt;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class FileListConverterTest {

    @Test
    public void test1() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());

        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());

        List<Integer> listToConvert = Arrays.asList(5, 8, 9, 4, -83, 1000);
        File convertedFile = tempFilesFabric.getNewTempFile();

        File expected = tempFilesFabric.getNewTempFile();
        try (PrintWriter printWriter = new PrintWriter(expected)) {
            listToConvert.forEach(printWriter::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileListConverter.listToFile(listToConvert, convertedFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(convertedFile, "utf-8"),
                    FileUtils.readFileToString(expected, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        expected.delete();
        convertedFile.delete();
    }

    @Test
    public void test2() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());

        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());

        List<Integer> expected = Arrays.asList(-80, 0, 100, 9, -6);

        File fileToConvert = tempFilesFabric.getNewTempFile();
        List<Integer> convertedList = new ArrayList<>();

        try (PrintWriter printWriter = new PrintWriter(fileToConvert)) {
            expected.forEach(printWriter::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        fileListConverter.fileToList(fileToConvert, convertedList);

        assertEquals(expected, convertedList);

        fileToConvert.delete();

    }
}