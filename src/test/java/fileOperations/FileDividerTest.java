package fileOperations;

import control.Configurator;
import control.TempFilesFabric;
import operations.ParserInt;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class FileDividerTest {
    @Test
    public void test1() {
        Configurator<Integer> configurator = new Configurator<>();
        configurator.config(Integer::compareTo, new ParserInt());
        TempFilesFabric tempFilesFabric = configurator.getTempFilesFabric();
        FileDivider fileDivider = configurator.getFileDivider();
        FileListConverter<Integer> fileListConverter = new FileListConverter<>();
        fileListConverter.setParser(new ParserInt());

        fileDivider.setMaxSize(10);

        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 99; i++) {
            list.add(i);
        }

        File fileToDivide = tempFilesFabric.getNewTempFile();
        fileListConverter.listToFile(list, fileToDivide);

        List<File> listDividedFiles = new ArrayList<>();

        fileDivider.divide(fileToDivide, listDividedFiles);

        assertEquals(10, listDividedFiles.size());

        List<Integer> result = new ArrayList<>();

        for(File file: listDividedFiles) {
            List<Integer> tempList = new ArrayList<>();
            fileListConverter.fileToList(file, tempList);
            result.addAll(tempList);
        }

        assertEquals(list, result);

    }

}