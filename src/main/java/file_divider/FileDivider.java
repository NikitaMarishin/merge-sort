package file_divider;

import temp_files_fabric.TempFilesFabric;

import java.io.*;
import java.util.List;

public class FileDivider{
    private long maxSize = 1_000_000;
    private TempFilesFabric tempFilesFabric = new TempFilesFabric();

    public void divide(File fileToDivide, List<File> dividedFiles) {
        try(BufferedReader reader = new BufferedReader(new FileReader(fileToDivide))) {
            String tempLine = null;
            do {
                File dividedFile = tempFilesFabric.getNewTempFile();
                dividedFiles.add(dividedFile);
                try (BufferedWriter writer = new BufferedWriter(new FileWriter(dividedFile))) {
                    for (long i = 0; i < maxSize && (tempLine = reader.readLine()) != null; i++) {
                        writer.write(tempLine);
                        writer.newLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } while (tempLine != null);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    public void setTempFilesFabric(TempFilesFabric tempFilesFabric) {
        this.tempFilesFabric = tempFilesFabric;
    }

}
