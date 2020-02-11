package fileOperations;

import operations.Parser;
import control.TempFilesFabric;

import java.io.*;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class SortedFilesMerger<T> {
    private TempFilesFabric tempFilesFabric = new TempFilesFabric();
    private Comparator<T> comparator;
    private Parser<T> parser;

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

        try (BufferedReader reader1 = new BufferedReader(new FileReader(file1));
             BufferedReader reader2 = new BufferedReader(new FileReader(file2));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {

            String tempLine1;
            String tempLine2;

            T buffer1;
            T buffer2;

            tempLine1 = reader1.readLine();
            tempLine2 = reader2.readLine();

            while (tempLine1 != null && tempLine2 != null) {
                buffer1 = parser.parse(tempLine1);
                buffer2 = parser.parse(tempLine2);

                if (comparator.compare(buffer1, buffer2) <= 0) {
                    writer.write(tempLine1);
                    writer.newLine();

                    if  ((tempLine1 = reader1.readLine()) == null){
                        break;
                    }
                } else {
                    writer.write(tempLine2);
                    writer.newLine();

                    if ((tempLine2 = reader2.readLine()) == null) {
                        break;
                    }
                }
            }

            if (tempLine1 != null) {
                writer.write(tempLine1);
                writer.newLine();
            }

            if (tempLine2 != null) {
                writer.write(tempLine2);
                writer.newLine();
            }

            while ((tempLine1 = reader1.readLine()) != null) {
                writer.write(tempLine1);
                writer.newLine();
            }

            while ((tempLine2 = reader2.readLine()) != null) {
                writer.write((tempLine2));
                writer.newLine();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }

    public void setTempFilesFabric(TempFilesFabric tempFilesFabric) {
        this.tempFilesFabric = tempFilesFabric;
    }

}
