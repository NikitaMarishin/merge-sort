package filemerger;

import org.junit.Before;
import org.junit.Test;
import operations.Parser;
import operations.ParserInt;
import operations.ParserString;

import java.util.Comparator;

public class SortedFilesMergerTest {
    Comparator<Integer> comparatorInt = Integer::compareTo;
    Comparator<String> comparatorString = String::compareTo;
    Parser<Integer> parserInt = new ParserInt();
    Parser<String> parserString = new ParserString();

    @Before
    public void initOfFileMergers() {

    }


    @Test
    public void test1() {

    }
}