package control;

import java.io.File;

public class TempFilesFabric{
    private long tempNumber = 0;
    private File directory = new File(".");


    public void setTempDirectory(File directory) {
        if (!directory.isDirectory()) {
            directory.mkdir();
        }
        this.directory = directory;
    }

    public File getNewTempFile() {
        tempNumber++;
        File result = new File(directory.getPath() + "/" + "temp" + tempNumber + ".txt");
        result.deleteOnExit();
        return result;
    }
}
