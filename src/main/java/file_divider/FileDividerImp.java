package file_divider;

import temp_files_fabric.TempFilesFabric;
import temp_files_fabric.TempFilesFabricImp;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Scanner;

public class FileDividerImp implements FileDivider{
    private long maxSize = 1_000_000;
    private TempFilesFabric tempFilesFabric = new TempFilesFabricImp();

    @Override
    public void divide(File fileToDivide, List<File> dividedFiles) {

        try(Scanner scanner = new Scanner(fileToDivide)) {
            while(scanner.hasNextLine()) {
                File dividedFile = tempFilesFabric.getNewTempFile();
                dividedFiles.add(dividedFile);
                try(PrintWriter printWriter = new PrintWriter(dividedFile)){
                    for(long i = 0; i < maxSize && scanner.hasNextLine(); i++) {
                        printWriter.println(scanner.nextLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }


        } catch (FileNotFoundException e) {
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
