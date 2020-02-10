package manager;

import checker.Checker;
import file_sorter.FileSorter;
import filemerger.FileMerger;
import reverser.Reverser;
import temp_files_fabric.TempFilesFabric;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ManagerImp<T> implements Manager<T>{

    private Checker<T> checker;
    private FileSorter<T> fileSorter;
    private FileMerger<T> fileMerger;
    private List<File> inputFiles;
    private File outFile;
    private TempFilesFabric tempFilesFabric;
    private Reverser reverser;

    public void start() {

        inputFiles = filesPreMerge();

        fileMerger.merge(inputFiles, outFile);
    }

    private List<File> filesPreMerge() {
        List<File> resultList = new ArrayList<>();
        for (File file: inputFiles) {
            checker.check(file);
            if (!checker.isOpened() || !checker.isNotEmpty() || !checker.isTyped()) {
                continue;
            }
            if (checker.isSorted() && !checker.isOrdered()) {
                File temp = tempFilesFabric.getNewTempFile();
                reverser.reverse(file, temp);
                resultList.add(temp);
            }
            if (!checker.isSorted()) {
                File temp = tempFilesFabric.getNewTempFile();
                fileSorter.sort(file, temp);
                resultList.add(temp);
            } else {
                resultList.add(file);
            }
        }
        return resultList;
    }

    @Override
    public void setFileSorter(FileSorter<T> fileSorter) {
        this.fileSorter = fileSorter;
    }

    @Override
    public void setFileMerger(FileMerger<T> fileMerger) {
        this.fileMerger = fileMerger;
    }

    @Override
    public void setChecker(Checker<T> checker) {
        this.checker = checker;
    }

    @Override
    public void setInputFiles(List<File> inputFiles) {
        this.inputFiles = inputFiles;
    }

    @Override
    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    @Override
    public void setTempFilesFabric(TempFilesFabric tempFilesFabric) {
        this.tempFilesFabric = tempFilesFabric;
    }

    @Override
    public void setReverser(Reverser reverser) {
        this.reverser = reverser;
    }
}
