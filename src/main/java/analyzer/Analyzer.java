package analyzer;

import java.io.File;
import java.util.List;

public interface Analyzer {

    boolean isAscending();

    boolean isString();

    File getOutFile();

    List<File> getInputFiles();

    boolean analyze(String[] args);

    void initialize();
}
