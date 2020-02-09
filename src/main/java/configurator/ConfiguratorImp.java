package configurator;

import checker.Checker;
import checker.CheckerImp;
import file_divider.FileDivider;
import file_divider.FileDividerImp;
import file_sorter.FileSorter;
import file_sorter.FileSorterImp;
import filemerger.FileMerger;
import filemerger.FileMergerImp;
import list_merge_sorter.ListMergeSorter;
import list_merge_sorter.ListMergeSorterImp;
import manager.Manager;
import manager.ManagerImp;
import parser.Parser;
import temp_files_fabric.TempFilesFabric;
import temp_files_fabric.TempFilesFabricImp;

import java.io.File;
import java.util.Comparator;

public class ConfiguratorImp<T> implements Configurator<T>{
    private Comparator<T> comparator;
    private TempFilesFabric tempFilesFabric = new TempFilesFabricImp();
    private FileMerger<T> fileMerger = new FileMergerImp<>();
    private FileDivider fileDivider = new FileDividerImp();
    private Parser<T> parser;
    private FileSorter<T> fileSorter = new FileSorterImp<>();
    private ListMergeSorter<T> listMergeSorter = new ListMergeSorterImp<>();
    private Checker<T> checker = new CheckerImp<>();
    private Manager<T> manager = new ManagerImp<>();

    @Override
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

        manager.setFileMerger(fileMerger);
        manager.setFileSorter(fileSorter);
        manager.setChecker(checker);
        manager.setTempFilesFabric(tempFilesFabric);
    }

    @Override
    public Comparator<T> getComparator() {
        return comparator;
    }

    @Override
    public TempFilesFabric getTempFilesFabric() {
        return tempFilesFabric;
    }

    @Override
    public FileMerger<T> getFileMerger() {
        return fileMerger;
    }

    @Override
    public FileDivider getFileDivider() {
        return fileDivider;
    }

    @Override
    public Parser<T> getParser() {
        return parser;
    }

    @Override
    public FileSorter<T> getFileSorter() {
        return fileSorter;
    }

    @Override
    public ListMergeSorter<T> getListMergeSorter() {
        return listMergeSorter;
    }

    @Override
    public Checker<T> getChecker() {
        return checker;
    }

    @Override
    public Manager<T> getManager() {
        return manager;
    }
}
