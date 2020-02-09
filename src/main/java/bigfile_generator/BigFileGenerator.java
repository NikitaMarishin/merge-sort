package bigfile_generator;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class BigFileGenerator {
    private long numberRow;
    private File bigFile;


    public void setBigFile(File bigFile) {
        this.bigFile = bigFile;
    }

    public void setNumberRow(long numberRow) {
        this.numberRow = numberRow;
    }

    public void generate() {
        try(PrintWriter printWriter = new PrintWriter(bigFile)) {
            for(long i = 0; i < numberRow; i++) {
                printWriter.println(i);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
