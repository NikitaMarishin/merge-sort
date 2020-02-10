package filemerger;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import parser.Parser;
import parser.ParserInt;
import parser.ParserString;

import java.util.Comparator;

import static org.junit.Assert.*;

public class FileMergerImpTest {
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