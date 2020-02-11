package temp_files_fabric;

import control.TempFilesFabric;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class TempFilesFabricTest {
    private static List<File> tempFilesList = new ArrayList<>();
    private static TempFilesFabric tempFilesFabric = new TempFilesFabric();

    @BeforeClass
    public static void initTempDirectory() {
        File tempDir = new File("./tmp/");
        if (!tempDir.isDirectory()) {
            tempDir.mkdir();
        }
    }

    @Test
    public void test1() {
        tempFilesFabric.setTempDirectory(new File("./tmp/"));

        File tempFile = tempFilesFabric.getNewTempFile();
        tempFilesList.add(tempFile);
        assertEquals(tempFile, new File("./tmp/temp1.txt"));
    }

    @Test
    public void test2() {
        tempFilesFabric.setTempDirectory(new File("./tmp/"));

        File tempFile = tempFilesFabric.getNewTempFile();
        tempFilesList.add(tempFile);
        assertEquals(tempFile, new File("./tmp/temp2.txt"));
    }

    @Test
    public void test3() {
        tempFilesFabric.setTempDirectory(new File("./"));
        File tempFile = tempFilesFabric.getNewTempFile();
        tempFilesList.add(tempFile);

        assertEquals(tempFile, new File("./temp3.txt"));
    }

    @AfterClass
    public static void clearTempFiles() {
        tempFilesList.forEach(File::delete);
    }
}