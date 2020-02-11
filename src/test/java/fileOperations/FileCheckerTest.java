package fileOperations;

import control.Configurator;
import control.TempFilesFabric;
import operations.ParserInt;
import operations.ParserString;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import static org.junit.Assert.*;

public class FileCheckerTest {
    @Test
    public void test1() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        FileChecker<Integer> fileChecker = configurator.getFileChecker();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        File testFile = tempFilesFabric.getNewTempFile();

        fileChecker.check(testFile);

        assertFalse(fileChecker.isOpened());
        assertFalse(fileChecker.isNotEmpty());
    }

    @Test
    public void test2() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        FileChecker<Integer> fileChecker = configurator.getFileChecker();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());

        List<Integer> testList = new ArrayList<>();
        File testFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(testList, testFile);

        fileChecker.check(testFile);

        assertTrue(fileChecker.isOpened());
        assertFalse(fileChecker.isNotEmpty());
    }

    @Test
    public void test3() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        FileChecker<Integer> fileChecker = configurator.getFileChecker();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        FileListConverter<String> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserString());

        List<String> testList = Arrays.asList("1", "2", "3");
        File testFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(testList, testFile);

        fileChecker.check(testFile);

        assertTrue(fileChecker.isOpened());
        assertTrue(fileChecker.isNotEmpty());
        assertTrue(fileChecker.isTyped());
    }

    @Test
    public void test4() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        FileChecker<Integer> fileChecker = configurator.getFileChecker();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        FileListConverter<String> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserString());

        List<String> testList = Arrays.asList("1", "a", "3");
        File testFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(testList, testFile);

        fileChecker.check(testFile);

        assertTrue(fileChecker.isOpened());
        assertTrue(fileChecker.isNotEmpty());
        assertFalse(fileChecker.isTyped());
    }

    @Test
    public void test5() {
        Configurator<String> configurator = new Configurator<>();
        configurator.config(String::compareTo, new ParserString());
        FileChecker<String> fileChecker = configurator.getFileChecker();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        FileListConverter<String> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserString());

        List<String> testList = Arrays.asList("1", "a", "3");
        File testFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(testList, testFile);

        fileChecker.check(testFile);

        assertTrue(fileChecker.isOpened());
        assertTrue(fileChecker.isNotEmpty());
        assertTrue(fileChecker.isTyped());
    }

    @Test
    public void test6() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        FileChecker<Integer> fileChecker = configurator.getFileChecker();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());

        List<Integer> testList = Arrays.asList(-100, 1, 2, 100, 200, 8999);
        File testFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(testList, testFile);

        fileChecker.check(testFile);

        assertTrue(fileChecker.isOpened());
        assertTrue(fileChecker.isNotEmpty());
        assertTrue(fileChecker.isTyped());
        assertTrue(fileChecker.isSorted());
        assertTrue(fileChecker.isOrdered());
    }

    @Test
    public void test7() {
        Comparator<Integer> comparator = Integer::compareTo;
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(comparator.reversed(), new ParserInt());
        FileChecker<Integer> fileChecker = configurator.getFileChecker();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());

        List<Integer> testList = Arrays.asList(-100, 1, 2, 100, 200, 8999);
        File testFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(testList, testFile);

        fileChecker.check(testFile);

        assertTrue(fileChecker.isOpened());
        assertTrue(fileChecker.isNotEmpty());
        assertTrue(fileChecker.isTyped());
        assertTrue(fileChecker.isSorted());
        assertFalse(fileChecker.isOrdered());
    }


    @Test
    public void test8() {
        Comparator<Integer> comparator = Integer::compareTo;
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(comparator.reversed(), new ParserInt());
        FileChecker<Integer> fileChecker = configurator.getFileChecker();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());

        List<Integer> testList = Arrays.asList(-100, 1, -1, 100, 200, 8999);
        File testFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(testList, testFile);

        fileChecker.check(testFile);

        assertTrue(fileChecker.isOpened());
        assertTrue(fileChecker.isNotEmpty());
        assertTrue(fileChecker.isTyped());
        assertFalse(fileChecker.isSorted());
        assertFalse(fileChecker.isOrdered());
    }

    @Test
    public void test9() {
        Comparator<String> comparator = String::compareTo;
        Configurator<String> configurator = new Configurator<>();
        configurator.config(comparator, new ParserString());
        FileChecker<String> fileChecker = configurator.getFileChecker();
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();

        FileListConverter<String> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserString());

        List<String> testList = Arrays.asList("zz", "vvvv", "baba", "aaa");
        File testFile = tempFilesFabric.getNewTempFile();

        fileListConverter.listToFile(testList, testFile);

        fileChecker.check(testFile);

        assertTrue(fileChecker.isOpened());
        assertTrue(fileChecker.isNotEmpty());
        assertTrue(fileChecker.isTyped());
        assertTrue(fileChecker.isSorted());
        assertFalse(fileChecker.isOrdered());
    }
}