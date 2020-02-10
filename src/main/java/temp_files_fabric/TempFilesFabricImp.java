package temp_files_fabric;

import java.io.File;

public class TempFilesFabricImp implements TempFilesFabric{
    private static long tempNumber = 0;
    private File directory = new File(".");


    @Override
    public void setTempDirectory(File directory) {
        if (directory.isDirectory()) {
            this.directory = directory;
        }
    }

    @Override
    public File getNewTempFile() {
        tempNumber++;
        File result = new File(directory.getPath() + "/" + "temp" + tempNumber + ".txt");
        result.deleteOnExit();
        return result;
    }
}
