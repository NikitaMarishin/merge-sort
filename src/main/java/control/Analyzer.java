package control;

import operations.Parser;
import operations.ParserInt;
import operations.ParserString;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Analyzer {
    private boolean ascending;
    private boolean string;
    private File outFile;
    private List<File> inputFiles;

    public boolean analyze(String[] args) {
        int ascendingIndex  = 0;
        int stringIndex = 1;
        int outFileIndex = 2;
        int inputFilesIndex = 3;

        if (args.length - 1  < ascendingIndex) {
            System.out.println("Type parameter is required");
            return false;
        }

        if ("-a".equals(args[ascendingIndex])) {
            ascending = true;
        } else if("-d".equals(args[ascendingIndex])) {
            ascending = false;
        } else {
            ascending = true;
            stringIndex--;
            outFileIndex--;
            inputFilesIndex--;
        }

        if(args.length - 1  < stringIndex) {
            System.out.println("Type parameter is required");
            return false;
        }

        if ("-s".equals(args[stringIndex])) {
            string = true;
        } else if("-i".equals((args[stringIndex]))) {
            string = false;
        } else {
            return false;
        }

        if(args.length - 1 < outFileIndex) {
            System.out.println("Out file is required");
            return false;
        }
        outFile = new File(args[outFileIndex]);

        if(args.length - 1 < inputFilesIndex) {
            System.out.println("At least one input file is required");
            return false;
        }

        inputFiles = new ArrayList<>();
        for(int i = inputFilesIndex; i < args.length; i++) {
            inputFiles.add(new File(args[i]));
        }

        return true;
    }

    public void initialize() {


        Comparator<String> comparatorString = String::compareTo;
        Comparator<Integer> comparatorInteger = Integer::compareTo;


        if (isString()) {

            Parser<String> parser = new ParserString();
            Comparator<String> comparator;

            if (isAscending()) {
                comparator = comparatorString;
            } else {
                comparator = comparatorString.reversed();
            }
            Configurator<String> configurator = new Configurator<String>();
            configurator.config(comparator, parser);
            Manager manager = configurator.getManager();
            manager.setInputFiles(inputFiles);
            manager.setOutFile(outFile);
            manager.start();

        } else {
            Parser<Integer> parser = new ParserInt();
            Comparator<Integer> comparator;
            if (isAscending()) {
                comparator = comparatorInteger;
            } else {
                comparator = comparatorInteger.reversed();
            }
            Configurator configurator = new Configurator<Integer>();
            configurator.config(comparator, parser);
            Manager manager = configurator.getManager();
            manager.setInputFiles(inputFiles);
            manager.setOutFile(outFile);
            manager.start();
        }

    }

    public boolean isAscending() {
        return ascending;
    }

    public boolean isString() {
        return string;
    }

    public File getOutFile() {
        return outFile;
    }

    public List<File> getInputFiles() {
        return inputFiles;
    }
}
