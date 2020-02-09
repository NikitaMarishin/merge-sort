package configurator;

import checker.Checker;
import file_divider.FileDivider;
import file_sorter.FileSorter;
import filemerger.FileMerger;
import list_merge_sorter.ListMergeSorter;
import manager.Manager;
import parser.Parser;
import temp_files_fabric.TempFilesFabric;

import java.util.Comparator;

public interface Configurator<T> {
    void config(Comparator<T> comparator, Parser<T> parser);

    Comparator<T> getComparator();

    TempFilesFabric getTempFilesFabric();

    FileMerger<T> getFileMerger();

    FileDivider getFileDivider();

    Parser<T> getParser();

    FileSorter<T> getFileSorter();

    ListMergeSorter<T> getListMergeSorter();

    Checker<T> getChecker();

    Manager<T> getManager();
}
