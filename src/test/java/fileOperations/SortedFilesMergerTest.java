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

import static org.junit.Assert.assertEquals;

public class SortedFilesMergerTest {

    @Test
    public void test1() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        SortedFilesMerger<Integer> sortedFilesMerger = configurator.getSortedFilesMerger();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());


        List<Integer> in1List = Arrays.asList(1, 2, 2, 3, 25, 100);
        List<Integer> in2List = Arrays.asList(-100, 0, 1, 2, 66, 250);
        List<Integer> in3List = Arrays.asList(-1000, -100);

        List<Integer> expectedList = Arrays.asList(-1000, -100, -100, 0, 1, 1, 2, 2, 2, 3, 25, 66, 100, 250);

        File in1 = tempFilesFabric.getNewTempFile();
        File in2 = tempFilesFabric.getNewTempFile();
        File in3 = tempFilesFabric.getNewTempFile();

        File expectedFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(in1List, in1);
        fileListConverter.listToFile(in2List, in2);
        fileListConverter.listToFile(in3List, in3);

        List<File> initialFiles = Arrays.asList(in1, in2, in3);

        fileListConverter.listToFile(expectedList, expectedFile);

        File resultFile = tempFilesFabric.getNewTempFile();

        sortedFilesMerger.merge(initialFiles, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test2() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        SortedFilesMerger<Integer> sortedFilesMerger = configurator.getSortedFilesMerger();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());


        List<Integer> in1List = Arrays.asList();
        List<Integer> in2List = Arrays.asList();
        List<Integer> in3List = Arrays.asList();

        List<Integer> expectedList = Arrays.asList();

        File in1 = tempFilesFabric.getNewTempFile();
        File in2 = tempFilesFabric.getNewTempFile();
        File in3 = tempFilesFabric.getNewTempFile();

        File expectedFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(in1List, in1);
        fileListConverter.listToFile(in2List, in2);
        fileListConverter.listToFile(in3List, in3);

        List<File> initialFiles = Arrays.asList(in1, in2, in3);

        fileListConverter.listToFile(expectedList, expectedFile);

        File resultFile = tempFilesFabric.getNewTempFile();

        sortedFilesMerger.merge(initialFiles, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test3() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        SortedFilesMerger<Integer> sortedFilesMerger = configurator.getSortedFilesMerger();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());


        List<Integer> in1List = Arrays.asList(1);
        List<Integer> in2List = Arrays.asList();
        List<Integer> in3List = Arrays.asList();

        List<Integer> expectedList = Arrays.asList(1);

        File in1 = tempFilesFabric.getNewTempFile();
        File in2 = tempFilesFabric.getNewTempFile();
        File in3 = tempFilesFabric.getNewTempFile();

        File expectedFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(in1List, in1);
        fileListConverter.listToFile(in2List, in2);
        fileListConverter.listToFile(in3List, in3);

        List<File> initialFiles = Arrays.asList(in1, in2, in3);

        fileListConverter.listToFile(expectedList, expectedFile);

        File resultFile = tempFilesFabric.getNewTempFile();

        sortedFilesMerger.merge(initialFiles, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test4() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        SortedFilesMerger<Integer> sortedFilesMerger = configurator.getSortedFilesMerger();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());


        List<Integer> in1List = Arrays.asList(1);
        List<Integer> in2List = Arrays.asList();
        List<Integer> in3List = Arrays.asList(1);

        List<Integer> expectedList = Arrays.asList(1, 1);

        File in1 = tempFilesFabric.getNewTempFile();
        File in2 = tempFilesFabric.getNewTempFile();
        File in3 = tempFilesFabric.getNewTempFile();

        File expectedFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(in1List, in1);
        fileListConverter.listToFile(in2List, in2);
        fileListConverter.listToFile(in3List, in3);

        List<File> initialFiles = Arrays.asList(in1, in2, in3);

        fileListConverter.listToFile(expectedList, expectedFile);

        File resultFile = tempFilesFabric.getNewTempFile();

        sortedFilesMerger.merge(initialFiles, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test5() {
        Comparator<Integer> comparator = Integer::compareTo;
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(comparator.reversed(), new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        SortedFilesMerger<Integer> sortedFilesMerger = configurator.getSortedFilesMerger();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());


        List<Integer> in1List = Arrays.asList(1, 2, 2, 3, 25, 100);
        Collections.reverse(in1List);
        List<Integer> in2List = Arrays.asList(-100, 0, 1, 2, 66, 250);
        Collections.reverse(in2List);
        List<Integer> in3List = Arrays.asList(-1000, -100);
        Collections.reverse(in3List);

        List<Integer> expectedList = Arrays.asList(-1000, -100, -100, 0, 1, 1, 2, 2, 2, 3, 25, 66, 100, 250);
        Collections.reverse(expectedList);

        File in1 = tempFilesFabric.getNewTempFile();
        File in2 = tempFilesFabric.getNewTempFile();
        File in3 = tempFilesFabric.getNewTempFile();

        File expectedFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(in1List, in1);
        fileListConverter.listToFile(in2List, in2);
        fileListConverter.listToFile(in3List, in3);

        List<File> initialFiles = Arrays.asList(in1, in2, in3);

        fileListConverter.listToFile(expectedList, expectedFile);

        File resultFile = tempFilesFabric.getNewTempFile();

        sortedFilesMerger.merge(initialFiles, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test6() {
        Configurator<String> configurator = new Configurator<>();
        configurator.config(String::compareTo, new ParserString());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        SortedFilesMerger<String> sortedFilesMerger = configurator.getSortedFilesMerger();
        FileListConverter<String> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserString());


        List<String> in1List = Arrays.asList("a", "ba", "ca");
        List<String> in2List = Arrays.asList("aa", "ab", "c", "z");
        List<String> in3List = Arrays.asList("av", "zz");


        List<String> expectedList = new ArrayList<>();
        expectedList.addAll(in1List);
        expectedList.addAll(in2List);
        expectedList.addAll(in3List);

        Collections.sort(expectedList);

        File in1 = tempFilesFabric.getNewTempFile();
        File in2 = tempFilesFabric.getNewTempFile();
        File in3 = tempFilesFabric.getNewTempFile();

        File expectedFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(in1List, in1);
        fileListConverter.listToFile(in2List, in2);
        fileListConverter.listToFile(in3List, in3);

        List<File> initialFiles = Arrays.asList(in1, in2, in3);

        fileListConverter.listToFile(expectedList, expectedFile);

        File resultFile = tempFilesFabric.getNewTempFile();

        sortedFilesMerger.merge(initialFiles, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Test
    public void test7() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        SortedFilesMerger<Integer> sortedFilesMerger = configurator.getSortedFilesMerger();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());


        List<Integer> in1List = Arrays.asList(1);
        List<Integer> in2List = Arrays.asList(2);
        List<Integer> in3List = Arrays.asList(3, 4);
        List<Integer> in4List = Arrays.asList(5);
        List<Integer> in5List = Arrays.asList(7, 8);
        List<Integer> in6List = Arrays.asList(-1, 9);
        List<Integer> in7List = Arrays.asList(-2, 10, 11);
        List<Integer> in8List = Arrays.asList(12);

        List<Integer> expectedList = Arrays.asList(-2, -1, 1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12);

        File in1 = tempFilesFabric.getNewTempFile();
        File in2 = tempFilesFabric.getNewTempFile();
        File in3 = tempFilesFabric.getNewTempFile();
        File in4 = tempFilesFabric.getNewTempFile();
        File in5 = tempFilesFabric.getNewTempFile();
        File in6 = tempFilesFabric.getNewTempFile();
        File in7 = tempFilesFabric.getNewTempFile();
        File in8 = tempFilesFabric.getNewTempFile();


        File expectedFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(in1List, in1);
        fileListConverter.listToFile(in2List, in2);
        fileListConverter.listToFile(in3List, in3);
        fileListConverter.listToFile(in4List, in4);
        fileListConverter.listToFile(in5List, in5);
        fileListConverter.listToFile(in6List, in6);
        fileListConverter.listToFile(in7List, in7);
        fileListConverter.listToFile(in8List, in8);

        List<File> initialFiles = Arrays.asList(in1, in2, in3, in4, in5, in6, in7, in8);

        fileListConverter.listToFile(expectedList, expectedFile);

        File resultFile = tempFilesFabric.getNewTempFile();

        sortedFilesMerger.merge(initialFiles, resultFile);

        try {
            assertEquals("The files differ!",
                    FileUtils.readFileToString(expectedFile, "utf-8"),
                    FileUtils.readFileToString(resultFile, "utf-8"));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}