package fileOperations;

import control.TempFilesFabric;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FileReverser {
    private FileDivider fileDivider;
    private TempFilesFabric tempFilesFabric;
    private long sizeLimit;

    public void reverse(File fileToReverse, File outFile) {

        if(fileToReverse.length() < sizeLimit) {
            reverseInRam(fileToReverse, outFile);
        } else {
            reverseWithDivide(fileToReverse, outFile);
        }
    }

    private void reverseInRam (File fileToReverse,  File outFile) {
        List<String> dataInRam = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToReverse))) {
            String tempLine;
            while ((tempLine = reader.readLine()) != null) {
                dataInRam.add(tempLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Collections.reverse(dataInRam);

        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            for (String data: dataInRam) {
                writer.write(data);
                writer.newLine();
            }
        } catch (IOException e) {
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
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(outFile))) {
            for(File file: dividedFileList) {
                try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
                    String tempLine;
                    while ((tempLine = reader.readLine()) != null) {
                        writer.write(tempLine);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void setFileDivider(FileDivider fileDivider) {
        this.fileDivider = fileDivider;
    }

    public void setTempFilesFabric(TempFilesFabric tempFilesFabric) {
        this.tempFilesFabric = tempFilesFabric;
    }

    public void setSizeLimit(long sizeLimit) {
        this.sizeLimit = sizeLimit;
    }
}
