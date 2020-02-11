package operations;

import operations.Parser;
import operations.ParserString;
import org.junit.Test;

import static org.junit.Assert.*;

public class ParserStringTest {
    @Test
    public void test1() {
        Parser<String> parser = new ParserString();
        assertEquals(parser.parse("123"), "123");
        assertEquals(parser.parse("aBav"), "aBav");
    }
}