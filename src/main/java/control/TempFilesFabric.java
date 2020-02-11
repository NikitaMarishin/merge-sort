package control;

import java.io.File;
import java.util.Random;

public class TempFilesFabric{
    private static long tempNumber = 0;
    private File directory = new File(".");


    public void setTempDirectory(File directory) {
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
        this.directory = directory;
    }

    public File getNewTempFile() {
        tempNumber++;
        Random random = new Random();
        File result = new File(directory.getPath() + "/" + "temp" + tempNumber + "-" + random.nextInt(100000) + ".txt");
        result.deleteOnExit();
        return result;
    }
}
