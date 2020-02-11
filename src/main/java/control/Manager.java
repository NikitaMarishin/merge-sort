package control;

import fileOperations.FileChecker;
import fileOperations.FileSorter;
import fileOperations.SortedFilesMerger;
import fileOperations.FileReverser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Manager<T> {

    private FileChecker<T> fileChecker;
    private FileSorter<T> fileSorter;
    private SortedFilesMerger<T> sortedFilesMerger;
    private List<File> inputFiles;
    private File outFile;
    private TempFilesFabric tempFilesFabric;
    private FileReverser fileReverser;

    public void start() {

        inputFiles = filesPreMerge();

        sortedFilesMerger.merge(inputFiles, outFile);
    }

    private List<File> filesPreMerge() {
        List<File> resultList = new ArrayList<>();
        for (File file: inputFiles) {
            fileChecker.check(file);
            if (!fileChecker.isOpened() || !fileChecker.isNotEmpty() || !fileChecker.isTyped()) {
                continue;
            }
            if (fileChecker.isSorted() && !fileChecker.isOrdered()) {
                File temp = tempFilesFabric.getNewTempFile();
                fileReverser.reverse(file, temp);
                resultList.add(temp);
            }
            if (!fileChecker.isSorted()) {
                File temp = tempFilesFabric.getNewTempFile();
                fileSorter.sort(file, temp);
                resultList.add(temp);
            } else {
                resultList.add(file);
            }
        }
        return resultList;
    }

    public void setFileSorter(FileSorter<T> fileSorter) {
        this.fileSorter = fileSorter;
    }

    public void setSortedFilesMerger(SortedFilesMerger<T> sortedFilesMerger) {
        this.sortedFilesMerger = sortedFilesMerger;
    }

    public void setFileChecker(FileChecker<T> fileChecker) {
        this.fileChecker = fileChecker;
    }

    public void setInputFiles(List<File> inputFiles) {
        this.inputFiles = inputFiles;
    }

    public void setOutFile(File outFile) {
        this.outFile = outFile;
    }

    public void setTempFilesFabric(TempFilesFabric tempFilesFabric) {
        this.tempFilesFabric = tempFilesFabric;
    }

    public void setFileReverser(FileReverser fileReverser) {
        this.fileReverser = fileReverser;
    }
}
