package checker;

import parser.Parser;

import java.io.File;
import java.util.Comparator;

public interface Checker<T> {
    void check(File fileToCheck);

    void setComparator(Comparator comparator);

    void setParser(Parser<T> parser);

    boolean isNotEmpty();

    boolean isTyped();

    boolean isOrdered();

    boolean isSorted();

    boolean isOpened();
}
