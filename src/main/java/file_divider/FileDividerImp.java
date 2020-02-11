package file_divider;

import temp_files_fabric.TempFilesFabric;
import temp_files_fabric.TempFilesFabricImp;

import java.io.*;
import java.util.List;

public class FileDividerImp implements FileDivider{
    private long maxSize = 1_000_000;
    private TempFilesFabric tempFilesFabric = new TempFilesFabricImp();

    @Override
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


    @Override
    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }

    @Override
    public void setTempFilesFabric(TempFilesFabric tempFilesFabric) {
        this.tempFilesFabric = tempFilesFabric;
    }

}
