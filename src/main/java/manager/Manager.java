package manager;

import checker.Checker;
import file_sorter.FileSorter;
import filemerger.FileMerger;
import reverser.Reverser;
import temp_files_fabric.TempFilesFabric;

import java.io.File;
import java.util.List;

public interface Manager<T> {
    void setFileSorter(FileSorter<T> fileSorter);

    void setFileMerger(FileMerger<T> fileMerger);

    void setChecker(Checker<T> checker);

    void start();

    void setInputFiles(List<File> inputFiles);

    void setOutFile(File outFile);

    void setTempFilesFabric(TempFilesFabric tempFilesFabric);

    void setReverser(Reverser reverser);
}
