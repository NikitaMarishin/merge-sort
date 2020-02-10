package parser;

import org.junit.Test;

import static org.junit.Assert.*;

public class ParserIntTest {
    @Test
    public void test1 () {
        Parser<Integer> parser= new ParserInt();
        assertEquals(parser.parse("189"), Integer.valueOf(189));
        assertEquals(parser.parse("-8931"), Integer.valueOf(-8931));
    }
}