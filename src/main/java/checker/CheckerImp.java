package checker;

import parser.Parser;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.Scanner;

public class CheckerImp<T> implements Checker<T>{
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

        try (Scanner scanner = new Scanner(fileToCheck)) {
            opened = true;

            T prev;

            if(!scanner.hasNextLine()) {
                notEmpty = false;
                return;
            }

            try {
                prev = parser.parse(scanner.nextLine());
            } catch (NumberFormatException e) {
                typed = false;
                return;
            }

            while (scanner.hasNextLine()) {
                T next;
                try {
                    next = parser.parse(scanner.nextLine());
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

        } catch (FileNotFoundException e) {
            opened = false;
            e.printStackTrace();
        }

        sorted = ascending || descending;
        ordered = ascending;
        notEmpty = true;
        typed = true;
    }



    @Override
    public void setComparator(Comparator comparator) {
        this.comparator = comparator;
    }

    @Override
    public boolean isNotEmpty() {
        return notEmpty;
    }

    @Override
    public boolean isTyped() {
        return typed;
    }

    @Override
    public boolean isOrdered() {
        return ordered;
    }

    @Override
    public boolean isSorted() {
        return sorted;
    }

    @Override
    public boolean isOpened() {
        return opened;
    }

    @Override
    public void setParser(Parser<T> parser) {
        this.parser = parser;
    }
}
