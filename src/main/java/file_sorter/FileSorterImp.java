package file_sorter;

import file_divider.FileDivider;
import filemerger.FileMerger;
import list_merge_sorter.ListMergeSorter;
import parser.Parser;
import temp_files_fabric.TempFilesFabric;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.*;

public class FileSorterImp<T> implements FileSorter<T>{

    private long sizeLimit = 100_000_000;

    private Comparator<T> comparator;
    private Parser<T> parser;
    private TempFilesFabric tempFilesFabric;
    private FileDivider fileDivider;
    private ListMergeSorter<T> listMergeSorter;
    private FileMerger<T> fileMerger;

    @Override
    public void sort(File fileToSort, File sortedFile) {
        if (fileToSort.length() > sizeLimit) {

            List<File> dividedFilesList = new ArrayList<>();
           
            fileDivider.divide(fileToSort, dividedFilesList);

            List<File> listDividedSortedFiles = new ArrayList<>();

            for(File file: dividedFilesList) {
                File dividedSortedFile = tempFilesFabric.getNewTempFile();

                sortFileInMemory(file, dividedSortedFile);

                listDividedSortedFiles.add(dividedSortedFile);
            }

            fileMerger.merge(listDividedSortedFiles, sortedFile);

        } else {
            sortFileInMemory(fileToSort, sortedFile);
        }
    }

    private void sortFileInMemory(File fileToSort, File sortedFile) {
        List<T> dataInRam = new ArrayList<>();
        try (Scanner scanner = new Scanner(fileToSort)) {
            while (scanner.hasNextLine()) {
                dataInRam.add(parser.parse(scanner.nextLine()));
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        listMergeSorter.sort(dataInRam);

        try (PrintWriter printWriter = new PrintWriter(sortedFile)) {
            dataInRam.forEach(printWriter::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setSizeLimit(long sizeLimit) {
        this.sizeLimit = sizeLimit;
    }

    @Override
    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }


    @Override
    public void setTempFilesFabric(TempFilesFabric tempFilesFabric) {
        this.tempFilesFabric = tempFilesFabric;
    }

    @Override
    public void setFileDivider(FileDivider fileDivider) {
        this.fileDivider = fileDivider;
    }

    @Override
    public void setListMergeSorter(ListMergeSorter<T> listMergeSorter) {
        this.listMergeSorter = listMergeSorter;
    }

    @Override
    public void setFileMerger(FileMerger<T> fileMerger) {
        this.fileMerger = fileMerger;
    }
}
