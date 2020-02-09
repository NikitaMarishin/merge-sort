package file_divider;

import temp_files_fabric.TempFilesFabric;

import java.io.File;
import java.util.List;

public interface FileDivider {
   void setMaxSize(long maxSize);
   void setTempFilesFabric(TempFilesFabric tempFilesFabric);

   void divide(File fileToDivide, List<File> dividedFiles);

}
