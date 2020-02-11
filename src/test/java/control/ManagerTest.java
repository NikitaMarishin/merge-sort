package control;

import fileOperations.FileListConverter;
import operations.ParserInt;
import operations.ParserString;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.Assert.*;

public class ManagerTest {
    @Test
    public void test1() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        Manager<Integer> manager = configurator.getManager();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        List<Integer> list1 = Arrays.asList(1, 2, 3, 4, 5);
        List<Integer> list2 = Arrays.asList(6, -100, -100);
        List<Integer> list3 = Arrays.asList();
        List<Integer> list4 = Arrays.asList(55, -100, 100, 100, -99);

        List<String> list5 = Arrays.asList("-8", "-9", "25", "-100");
        List<String> list6 = Arrays.asList("4", "5", "a");

        List<Integer> expected = new ArrayList<>();
        expected.addAll(list1);
        expected.addAll(list2);
        expected.addAll(list3);
        expected.addAll(list4);

        expected.add(-8);
        expected.add(-9);
        expected.add(25);
        expected.add(-100);

        Collections.sort(expected);

        File expectedFile = tempFilesFabric.getNewTempFile();

        File file1 = tempFilesFabric.getNewTempFile();
        File file2 = tempFilesFabric.getNewTempFile();
        File file3 = tempFilesFabric.getNewTempFile();
        File file4 = tempFilesFabric.getNewTempFile();

        File file5 = tempFilesFabric.getNewTempFile();
        File file6 = tempFilesFabric.getNewTempFile();

        File file7 = tempFilesFabric.getNewTempFile();

        FileListConverter<Integer> integerFileListConverter = new FileListConverter<>();
        integerFileListConverter.setParser(new ParserInt());

        integerFileListConverter.listToFile(list1, file1);
        integerFileListConverter.listToFile(list2, file2);
        integerFileListConverter.listToFile(list3, file3);
        integerFileListConverter.listToFile(list4, file4);

        integerFileListConverter.listToFile(expected, expectedFile);

        FileListConverter<String> stringFileListConverter = new FileListConverter<>();
        stringFileListConverter.setParser(new ParserString());

        stringFileListConverter.listToFile(list5, file5);
        stringFileListConverter.listToFile(list6, file6);

        List<File> inputFiles = new ArrayList<>();

        inputFiles.add(file1);
        inputFiles.add(file2);
        inputFiles.add(file3);
        inputFiles.add(file4);
        inputFiles.add(file5);
        inputFiles.add(file6);
        inputFiles.add(file7);

        manager.setInputFiles(inputFiles);

        File outFile = tempFilesFabric.getNewTempFile();

        manager.setOutFile(outFile);

        manager.start();

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(outFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}