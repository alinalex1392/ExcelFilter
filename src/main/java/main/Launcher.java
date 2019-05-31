package main;


import com.samsung.excel.parser.ExcelParser;
import com.samsung.excel.pivot.PivotConfig;
import com.samsung.excel.pivot.PivotPSIWConfig;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class Launcher {

    private ExcelParser excelParserObject = new ExcelParser();

    private List<PivotConfig> pivotConfigs = Arrays.asList(
            new PivotPSIWConfig()
    );

    public static void main(String[] args) {

        String pathToReadFrom = args[0];

        Launcher starterClass = new Launcher();

        for(PivotConfig pivotConfig : starterClass.getPivotConfigs())

        try {
            File file = readFile(pathToReadFrom);
            starterClass.getExcelParserObject().processFile(file, pivotConfig);
        } catch (IOException ex) {
            System.out.println("File not found.");
        }

    }

    private static File readFile(String pathToReadFrom) throws IOException {

        validatePath(pathToReadFrom);
        File file;

        try {
            System.out.println("Trying to load file from the path : " + pathToReadFrom);

            file = new File(pathToReadFrom);

            System.out.println("Load file successfully");

        } catch (Exception e) {
            throw new RuntimeException("Cannot read file from the path: " + pathToReadFrom);
        }

        return file;
    }

    private static void validatePath(String pathToReadFrom) {
        Objects.requireNonNull(pathToReadFrom, "The path specified via variable is null , please specify a non null path");
    }

    private ExcelParser getExcelParserObject() {
        return excelParserObject;
    }

    private List<PivotConfig> getPivotConfigs(){
        return pivotConfigs;
    }

}
