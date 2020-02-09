package file_sorter;

import file_divider.FileDivider;
import filemerger.FileMerger;
import list_merge_sorter.ListMergeSorter;
import parser.Parser;
import temp_files_fabric.TempFilesFabric;

import java.io.File;
import java.util.Comparator;

public interface FileSorter<T> {
    void setSizeLimit(long sizeLimit);
    void setComparator(Comparator<T> comparator);
    void setParser(Parser<T> parser);
    void setTempFilesFabric(TempFilesFabric tempFilesFabric);
    void setFileDivider(FileDivider fileDivider);
    void setListMergeSorter(ListMergeSorter<T> listMergeSorter);
    void setFileMerger(FileMerger<T> fileMerger);

    void sort(File fileToSort, File sortedFile);
}
