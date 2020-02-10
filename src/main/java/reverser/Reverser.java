package reverser;

import file_divider.FileDivider;
import temp_files_fabric.TempFilesFabric;

import java.io.File;

public interface Reverser {
    void reverse(File fileToReverse, File outFile);

    void setFileDivider(FileDivider fileDivider);

    void setTempFilesFabric(TempFilesFabric tempFilesFabric);

    void setSizeLimit(long sizeLimit);
}
