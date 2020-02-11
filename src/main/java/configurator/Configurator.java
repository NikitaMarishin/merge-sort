package configurator;

import checker.Checker;
import file_divider.FileDivider;
import file_sorter.FileSorter;
import filemerger.FileMerger;
import list_merge_sorter.ListMergeSorter;
import manager.Manager;
import parser.Parser;
import reverser.Reverser;
import temp_files_fabric.TempFilesFabric;

import java.io.File;
import java.util.Comparator;

public class Configurator<T> {
    private Comparator<T> comparator;
    private TempFilesFabric tempFilesFabric = new TempFilesFabric();
    private FileMerger<T> fileMerger = new FileMerger<>();
    private FileDivider fileDivider = new FileDivider();
    private Parser<T> parser;
    private FileSorter<T> fileSorter = new FileSorter<>();
    private ListMergeSorter<T> listMergeSorter = new ListMergeSorter<>();
    private Checker<T> checker = new Checker<>();
    private Manager<T> manager = new Manager<>();
    private Reverser reverser = new Reverser();

    public void config(Comparator<T> comparator, Parser<T> parser) {
        this.comparator = comparator;
        this.parser = parser;

        tempFilesFabric.setTempDirectory(new File("./tmp/"));

        fileMerger.setComparator(comparator);
        fileMerger.setParser(parser);
        fileMerger.setTempFilesFabric(tempFilesFabric);

        fileDivider.setTempFilesFabric(tempFilesFabric);
        fileDivider.setMaxSize(10_000_000);

        listMergeSorter.setComparator(comparator);

        checker.setComparator(comparator);
        checker.setParser(parser);

        fileSorter.setComparator(comparator);
        fileSorter.setFileDivider(fileDivider);
        fileSorter.setParser(parser);
        fileSorter.setTempFilesFabric(tempFilesFabric);
        fileSorter.setListMergeSorter(listMergeSorter);
        fileSorter.setFileMerger(fileMerger);
        fileSorter.setSizeLimit(100_000_000);

        reverser.setFileDivider(fileDivider);
        reverser.setTempFilesFabric(tempFilesFabric);
        reverser.setSizeLimit(100_000_00);

        manager.setFileMerger(fileMerger);
        manager.setFileSorter(fileSorter);
        manager.setChecker(checker);
        manager.setTempFilesFabric(tempFilesFabric);
        manager.setReverser(reverser);

    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public TempFilesFabric getTempFilesFabric() {
        return tempFilesFabric;
    }

    public FileMerger<T> getFileMerger() {
        return fileMerger;
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

    public Checker<T> getChecker() {
        return checker;
    }

    public Manager<T> getManager() {
        return manager;
    }

    public Reverser getReverser() {
        return reverser;
    }
}
