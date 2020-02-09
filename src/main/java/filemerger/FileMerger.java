package filemerger;

import parser.Parser;
import temp_files_fabric.TempFilesFabric;

import java.io.File;
import java.util.Comparator;
import java.util.List;

public interface FileMerger<T> {
    void setComparator(Comparator<T> comparator);
    void setParser(Parser<T> parser);
    void setTempFilesFabric(TempFilesFabric tempFilesFabric);

    void merge(List<File> listFilesToSort, File outFile);
}
