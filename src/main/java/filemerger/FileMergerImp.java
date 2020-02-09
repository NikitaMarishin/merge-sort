package filemerger;

import parser.Parser;
import temp_files_fabric.TempFilesFabric;
import temp_files_fabric.TempFilesFabricImp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class FileMergerImp<T> implements FileMerger<T> {
    private TempFilesFabric tempFilesFabric = new TempFilesFabricImp();
    private Comparator<T> comparator;
    private Parser<T> parser;

    @Override
    public void merge(List<File> listFilesToSort, File outFile) {
        mergeAllFiles(listFilesToSort, outFile);
    }

    private void mergeAllFiles(List<File> listFilesToSort, File outFile) {
        if(listFilesToSort.size() == 1) {
            return;
        }

        List<File> tempList = new ArrayList<>();

        for(int i = 0; i < listFilesToSort.size() / 2; i++) {
            File temp;
            if (listFilesToSort.size() == 2) {
                temp = outFile;
            } else {
                temp = tempFilesFabric.getNewTempFile();
            }
            mergeTwoFiles(listFilesToSort.get(2 * i), listFilesToSort.get(2 * i + 1), temp);
            tempList.add(temp);
        }

        if (listFilesToSort.size() % 2 != 0) {
            tempList.add(listFilesToSort.get(listFilesToSort.size() - 1));
        }

        listFilesToSort = tempList;
        mergeAllFiles(listFilesToSort, outFile);
    }

    private void mergeTwoFiles(File file1, File file2, File outFile) {

        try (Scanner scanner1 = new Scanner(file1); Scanner scanner2 = new Scanner(file2); PrintWriter printWriter = new PrintWriter(outFile)) {

            T buffer1 = parser.parse(scanner1.nextLine());
            T buffer2 = parser.parse(scanner2.nextLine());

            while (true) {
                if (comparator.compare(buffer1, buffer2) <= 0) {
                    printWriter.println(buffer1);
                    if (scanner1.hasNextLine()) {
                        buffer1 = parser.parse(scanner1.nextLine());
                    } else {
                        printWriter.println(buffer2);
                        break;
                    }
                } else {
                    printWriter.println(buffer2);
                    if (scanner2.hasNextLine()) {
                        buffer2 = parser.parse(scanner2.nextLine());
                    } else {
                        printWriter.println(buffer1);
                        break;
                    }
                }
            }

            while (scanner1.hasNextLine()) {
                printWriter.println(scanner1.nextLine());
            }

            while (scanner2.hasNextLine()) {
                printWriter.println(scanner2.nextLine());
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
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

}
