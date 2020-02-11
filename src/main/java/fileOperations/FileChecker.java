package fileOperations;

import operations.Parser;

import java.io.*;
import java.util.Comparator;

public class FileChecker<T>{
    private boolean notEmpty;
    private boolean typed;
    private boolean ordered;
    private boolean sorted;
    private boolean opened;
    private Comparator<T> comparator;
    private Parser<T> parser;


    public void check(File fileToCheck) {
        if(fileToCheck.length() == 0) {
            notEmpty = false;
            return;
        }

        boolean ascending = true;
        boolean descending = true;

        try (BufferedReader reader = new BufferedReader(new FileReader(fileToCheck))) {
            opened = true;

            T prev;
            String tempLine;

            if ((tempLine = reader.readLine()) == null) {
                notEmpty = false;
                return;
            }

            try {
                prev = parser.parse(tempLine);
            } catch (NumberFormatException e) {
                typed = false;
                return;
            }

            while ((tempLine = reader.readLine()) != null) {
                T next;
                try {
                    next = parser.parse(tempLine);
                } catch (NumberFormatException e) {
                    typed = false;
                    return;
                }

                if (comparator.compare(prev, next) < 0) {
                    descending = false;
                }

                if (comparator.reversed().compare(prev, next) < 0) {
                    ascending = false;
                }
                prev = next;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        sorted = ascending || descending;
        ordered = ascending;
        notEmpty = true;
        typed = true;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public boolean isNotEmpty() {
        return notEmpty;
    }

    public boolean isTyped() {
        return typed;
    }

    public boolean isOrdered() {
        return ordered;
    }

    public boolean isSorted() {
        return sorted;
    }

    public boolean isOpened() {
        return opened;
    }

    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }
}
