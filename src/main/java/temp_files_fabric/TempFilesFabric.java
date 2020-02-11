package temp_files_fabric;

import java.io.File;

public class TempFilesFabric{
    private static long tempNumber = 0;
    private File directory = new File(".");


    public void setTempDirectory(File directory) {
        if (directory.isDirectory()) {
            this.directory = directory;
        }
    }

    public File getNewTempFile() {
        tempNumber++;
        File result = new File(directory.getPath() + "/" + "temp" + tempNumber + ".txt");
        result.deleteOnExit();
        return result;
    }
}
