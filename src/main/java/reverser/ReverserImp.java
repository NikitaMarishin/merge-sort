package reverser;

import file_divider.FileDivider;
import temp_files_fabric.TempFilesFabric;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class ReverserImp implements Reverser{
    private FileDivider fileDivider;
    private TempFilesFabric tempFilesFabric;
    private long sizeLimit;

    @Override
    public void reverse(File fileToReverse, File outFile) {

        if(fileToReverse.length() < sizeLimit) {
            reverseInRam(fileToReverse, outFile);
        } else {
            reverseWithDivide(fileToReverse, outFile);
        }
    }

    private void reverseInRam (File fileToReverse,  File outFile) {
        List<String> dataInRam = new ArrayList<>();

        try (Scanner scanner = new Scanner(fileToReverse)) {
            while (scanner.hasNextLine()) {
                dataInRam.add(scanner.nextLine());
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        Collections.reverse(dataInRam);

        try(PrintWriter printWriter = new PrintWriter(outFile)) {
            dataInRam.forEach(printWriter::println);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void reverseWithDivide(File fileToReverse,  File outFile) {
        List<File> dividedFileList = new ArrayList<>();

        fileDivider.divide(fileToReverse, dividedFileList);

        for(int i = 0; i < dividedFileList.size(); i++) {
            File sortedPart = tempFilesFabric.getNewTempFile();

            reverseInRam(dividedFileList.get(i), sortedPart);

            sortedPart.delete();

            dividedFileList.get(i).delete();

            dividedFileList.set(i, sortedPart);

        }
        mergeFiles(dividedFileList, outFile);
        dividedFileList.forEach(File::delete);
    }

    private void mergeFiles(List<File> dividedFileList, File outFile) {
        try(PrintWriter printWriter = new PrintWriter(outFile)) {
            for(File file: dividedFileList) {
                try(Scanner scanner = new Scanner(outFile)) {
                    while (scanner.hasNextLine()) {
                        printWriter.println(scanner.nextLine());
                    }
                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setFileDivider(FileDivider fileDivider) {
        this.fileDivider = fileDivider;
    }

    @Override
    public void setTempFilesFabric(TempFilesFabric tempFilesFabric) {
        this.tempFilesFabric = tempFilesFabric;
    }

    @Override
    public void setSizeLimit(long sizeLimit) {
        this.sizeLimit = sizeLimit;
    }
}
