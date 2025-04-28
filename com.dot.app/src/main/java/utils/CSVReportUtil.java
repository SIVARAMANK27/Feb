package utils;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;

public class CSVReportUtil {
    private static final String FILE_PATH = "D:\\Git\\Feb\\com.dot.app\\reports\\AutomationReport.csv";
    private static CSVWriter writer;

    public static void initReport() {
        try {
            writer = new CSVWriter(new FileWriter(FILE_PATH, false)); // overwrite on first run
            writer.writeNext(new String[]{"Test Class", "Test Name", "Status", "Error Message"});
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void writeResult(String testClass, String testName, String status, String errorMessage) {
        try {
            if (writer != null) {
                writer.writeNext(new String[]{testClass, testName, status, errorMessage});
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void closeReport() {
        try {
            if (writer != null) {
                writer.flush();
                writer.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
