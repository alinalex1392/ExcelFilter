package main;


import com.samsung.excel.parser.ExcelParser;
import com.samsung.excel.pivot.Pivot1Config;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

public class ExcelStarterClass {

    private ExcelParser excelParserObject = new ExcelParser();

    public static void main(String[] args) {

        String pathToReadFrom = args[0];


        try {
            readFile(pathToReadFrom);

        } catch (IOException ex) {
            System.out.println("File not found.");
        }

    }

    private static void readFile(String pathToReadFrom) throws IOException {

        validatePath(pathToReadFrom);

        ExcelStarterClass starterClass = new ExcelStarterClass();

        File file;

//        File file = new File(Objects.requireNonNull(ExcelStarterClass.class.getClassLoader().getResource(SAMPLE_MIRCEA)).getFile());
        try {
            System.out.println("Trying to load file from the path : " + pathToReadFrom);

            file = new File(pathToReadFrom);

            System.out.println("Load file successfully");

        } catch (Exception e) {
            throw new RuntimeException("Cannot read file from the path: " + pathToReadFrom);
        }

        starterClass.getExcelParserObject().processFile(file, new Pivot1Config());

    }

    private static void validatePath(String pathToReadFrom) {
        Objects.requireNonNull(pathToReadFrom, "The path specified via variable is null , please specify a non null path");
    }

    private ExcelParser getExcelParserObject() {
        return excelParserObject;
    }

}
