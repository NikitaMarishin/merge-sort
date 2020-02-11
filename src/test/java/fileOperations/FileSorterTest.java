package fileOperations;

import control.Configurator;
import control.TempFilesFabric;
import operations.ParserInt;
import operations.ParserString;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;

public class FileSorterTest {
    @Test
    public void test1() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());

        List<Integer> unsortedList = Arrays.asList(5, 8, -100, 25, 9, 156, 8, 0, -23);
        File unsortedFile = tempFilesFabric.getNewTempFile();
        fileListConverter.listToFile(unsortedList, unsortedFile);

        List<Integer> sortedList = new ArrayList<>(unsortedList);
        Collections.sort(sortedList);
        File expected = tempFilesFabric.getNewTempFile();
        fileListConverter.listToFile(sortedList, expected);

        File resultFile = tempFilesFabric.getNewTempFile();

        FileSorter<Integer> fileSorter = configurator.getFileSorter();
        fileSorter.sort(unsortedFile, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expected, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Comparator<Integer> comparator = Integer::compareTo;

        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(comparator.reversed(), new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());

        List<Integer> unsortedList = Arrays.asList(5, 8, -100, 25, 9, 156, 8, 0, -23);
        File unsortedFile = tempFilesFabric.getNewTempFile();
        fileListConverter.listToFile(unsortedList, unsortedFile);

        List<Integer> sortedList = new ArrayList<>(unsortedList);
        Collections.sort(sortedList, comparator.reversed());
        File expected = tempFilesFabric.getNewTempFile();
        fileListConverter.listToFile(sortedList, expected);

        File resultFile = tempFilesFabric.getNewTempFile();

        FileSorter<Integer> fileSorter = configurator.getFileSorter();
        fileSorter.sort(unsortedFile, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expected, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void test3() {
        Configurator<String> configurator = new Configurator<>();
        configurator.config(String::compareTo, new ParserString());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        FileListConverter<String> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserString());

        List<String> unsortedList = Arrays.asList("aca", "aab", "fly", "rabbit", "zzz", "dictionary", "love");
        File unsortedFile = tempFilesFabric.getNewTempFile();
        fileListConverter.listToFile(unsortedList, unsortedFile);

        List<String> sortedList = new ArrayList<>(unsortedList);
        Collections.sort(sortedList);
        File expected = tempFilesFabric.getNewTempFile();
        fileListConverter.listToFile(sortedList, expected);

        File resultFile = tempFilesFabric.getNewTempFile();

        FileSorter<String> fileSorter = configurator.getFileSorter();
        fileSorter.sort(unsortedFile, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expected, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}