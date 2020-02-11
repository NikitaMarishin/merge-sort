package fileOperations;

import control.Configurator;
import control.TempFilesFabric;
import operations.ParserInt;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class FileReverserTest {
    @Test
    public void test1() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        FileListConverter<Integer> listConverter = new FileListConverter<>();
        listConverter.setParser(new ParserInt());

        FileReverser fileReverser = configurator.getFileReverser();

        List<Integer> listToReverse = Arrays.asList(1, 2, 3, 4, 5);

        List<Integer> expectedList = new ArrayList<>(listToReverse);
        Collections.reverse(expectedList);

        File fileToReverse = tempFilesFabric.getNewTempFile();
        File reverseResult = tempFilesFabric.getNewTempFile();

        File expectedFile = tempFilesFabric.getNewTempFile();

        listConverter.listToFile(listToReverse, fileToReverse);
        listConverter.listToFile(expectedList, expectedFile);

        fileReverser.reverse(fileToReverse, reverseResult);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(reverseResult, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void test2() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        FileListConverter<Integer> listConverter = new FileListConverter<>();
        listConverter.setParser(new ParserInt());

        FileReverser fileReverser = configurator.getFileReverser();

        List<Integer> listToReverse = Arrays.asList(8, 2, 5, -100, 1000);

        List<Integer> expectedList = new ArrayList<>(listToReverse);
        Collections.reverse(expectedList);

        File fileToReverse = tempFilesFabric.getNewTempFile();
        File reverseResult = tempFilesFabric.getNewTempFile();

        File expectedFile = tempFilesFabric.getNewTempFile();

        listConverter.listToFile(listToReverse, fileToReverse);
        listConverter.listToFile(expectedList, expectedFile);

        fileReverser.reverse(fileToReverse, reverseResult);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(reverseResult, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}