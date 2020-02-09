package temp_files_fabric;

import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

public class TempFilesFabricImpTest {
    @Test
    public void test1() {
        TempFilesFabric tempFilesFabric = new TempFilesFabricImp();
        tempFilesFabric.setTempDirectory(new File("./tmp/"));
        assertEquals(tempFilesFabric.getNewTempFile(), new File("./tmp/temp1.txt"));
    }

    @Test
    public void test2() {
        TempFilesFabric tempFilesFabric = new TempFilesFabricImp();
        tempFilesFabric.setTempDirectory(new File("./tmp/"));
        assertEquals(tempFilesFabric.getNewTempFile(), new File("./tmp/temp2.txt"));
    }

    @Test
    public void test3() {
        TempFilesFabric tempFilesFabric = new TempFilesFabricImp();
        assertEquals(tempFilesFabric.getNewTempFile(), new File("./temp3.txt"));

    }
}