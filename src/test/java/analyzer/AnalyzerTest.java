package analyzer;

import org.junit.Test;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class AnalyzerTest {

    @Test
    public void test1() {
        String[] args = new String[] {"-a", "-s", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        List<File> testInputList = new ArrayList<>();

        Analyzer analyzer = new Analyzer();

        testInputList.add(new File("in1.txt"));
        testInputList.add(new File("in2.txt"));
        testInputList.add(new File("in3.txt"));

        analyzer.analyze(args);

        assertTrue(analyzer.isAscending());

        assertTrue(analyzer.isString());

        assertEquals(analyzer.getOutFile(), new File("out.txt"));
        assertEquals(analyzer.getInputFiles(), testInputList);
    }

    @Test
    public void test2() {
        String[] args = new String[] {"-s", "out.txt", "in1.txt", "in2.txt", "in3.txt"};
        List<File> testInputList = new ArrayList<>();

        testInputList.add(new File("in1.txt"));
        testInputList.add(new File("in2.txt"));
        testInputList.add(new File("in3.txt"));

        Analyzer analyzer = new Analyzer();

        analyzer.analyze(args);

        assertTrue(analyzer.isAscending());

        assertTrue(analyzer.isString());

        assertEquals(analyzer.getOutFile(), new File("out.txt"));
        assertEquals(analyzer.getInputFiles(), testInputList);
    }

    @Test
    public void test3() {
        String[] args = new String[] {"-d", "-i", "out.txt", "in1.txt"};
        List<File> testInputList = new ArrayList<>();

        testInputList.add(new File("in1.txt"));

        Analyzer analyzer = new Analyzer();

        analyzer.analyze(args);

        assertFalse(analyzer.isAscending());

        assertFalse(analyzer.isString());

        assertEquals(analyzer.getOutFile(), new File("out.txt"));
        assertEquals(analyzer.getInputFiles(), testInputList);
    }

    @Test
    public void test4() {
        String[] args = new String[] {"-i", "out.txt", "in1.txt"};
        List<File> testInputList = new ArrayList<>();

        testInputList.add(new File("in1.txt"));

        Analyzer analyzer = new Analyzer();

        analyzer.analyze(args);

        assertTrue(analyzer.isAscending());

        assertFalse(analyzer.isString());

        assertEquals(analyzer.getOutFile(), new File("out.txt"));
        assertEquals(analyzer.getInputFiles(), testInputList);
    }

    @Test
    public void test5() {
        String[] args = new String[] {"-i"};
        List<File> testInputList = new ArrayList<>();

        Analyzer analyzer = new Analyzer();

        assertFalse(analyzer.analyze(args));
    }

    @Test
    public void test6() {
        String[] args = new String[] {};
        List<File> testInputList = new ArrayList<>();

        Analyzer analyzer = new Analyzer();

        assertFalse(analyzer.analyze(args));
    }

    @Test
    public void test7() {
        String[] args = new String[] {"-i", "out.txt"};
        List<File> testInputList = new ArrayList<>();

        Analyzer analyzer = new Analyzer();

        assertFalse(analyzer.analyze(args));
    }

    @Test
    public void test8() {
        String[] args = new String[] {"-d", "-i", "out.txt"};
        List<File> testInputList = new ArrayList<>();

        Analyzer analyzer = new Analyzer();

        assertFalse(analyzer.analyze(args));
    }

    @Test
    public void test9() {
        String[] args = new String[] {"-d"};
        List<File> testInputList = new ArrayList<>();

        Analyzer analyzer = new Analyzer();

        assertFalse(analyzer.analyze(args));
    }

}