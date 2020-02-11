package control;

import fileOperations.FileChecker;
import fileOperations.FileDivider;
import fileOperations.FileSorter;
import fileOperations.SortedFilesMerger;
import operations.ListMergeSorter;
import operations.Parser;
import fileOperations.FileReverser;

import java.io.File;
import java.util.Comparator;

public class Configurator<T> {
    private Comparator<T> comparator;
    private TempFilesFabric tempFilesFabric = new TempFilesFabric();
    private SortedFilesMerger<T> sortedFilesMerger = new SortedFilesMerger<>();
    private FileDivider fileDivider = new FileDivider();
    private Parser<T> parser;
    private FileSorter<T> fileSorter = new FileSorter<>();
    private ListMergeSorter<T> listMergeSorter = new ListMergeSorter<>();
    private FileChecker<T> fileChecker = new FileChecker<>();
    private Manager<T> manager = new Manager<>();
    private FileReverser fileReverser = new FileReverser();

    public void config(Comparator<T> comparator, Parser<T> parser) {
        this.comparator = comparator;
        this.parser = parser;

        tempFilesFabric.setTempDirectory(new File("./tmp/"));

        sortedFilesMerger.setComparator(comparator);
        sortedFilesMerger.setParser(parser);
        sortedFilesMerger.setTempFilesFabric(tempFilesFabric);

        fileDivider.setTempFilesFabric(tempFilesFabric);
        fileDivider.setMaxSize(1_000_000);

        listMergeSorter.setComparator(comparator);

        fileChecker.setComparator(comparator);
        fileChecker.setParser(parser);

        fileSorter.setComparator(comparator);
        fileSorter.setFileDivider(fileDivider);
        fileSorter.setParser(parser);
        fileSorter.setTempFilesFabric(tempFilesFabric);
        fileSorter.setListMergeSorter(listMergeSorter);
        fileSorter.setSortedFilesMerger(sortedFilesMerger);
        fileSorter.setSizeLimit(100_000_000 * 8);

        fileReverser.setFileDivider(fileDivider);
        fileReverser.setTempFilesFabric(tempFilesFabric);
        fileReverser.setSizeLimit(100_000_00 * 8);

        manager.setSortedFilesMerger(sortedFilesMerger);
        manager.setFileSorter(fileSorter);
        manager.setFileChecker(fileChecker);
        manager.setTempFilesFabric(tempFilesFabric);
        manager.setFileReverser(fileReverser);

    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public TempFilesFabric getTempFilesFabric() {
        return tempFilesFabric;
    }

    public SortedFilesMerger<T> getSortedFilesMerger() {
        return sortedFilesMerger;
    }

    public FileDivider getFileDivider() {
        return fileDivider;
    }

    public Parser<T> getParser() {
        return parser;
    }

    public FileSorter<T> getFileSorter() {
        return fileSorter;
    }

    public ListMergeSorter<T> getListMergeSorter() {
        return listMergeSorter;
    }

    public FileChecker<T> getFileChecker() {
        return fileChecker;
    }

    public Manager<T> getManager() {
        return manager;
    }

    public FileReverser getFileReverser() {
        return fileReverser;
    }
}
