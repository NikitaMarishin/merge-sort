package temp_files_fabric;

import java.io.File;

public interface TempFilesFabric {
    void setTempDirectory(File directory);
    File getNewTempFile();
}
