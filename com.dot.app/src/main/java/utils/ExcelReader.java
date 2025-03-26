package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class ExcelReader {

    public static Map<String, String> getTestCaseData(String filePath, String sheetName, String testCaseName) {
        Map<String, String> testData = new HashMap<>();
        
        try (FileInputStream fis = new FileInputStream(new File(filePath));
             Workbook workbook = new XSSFWorkbook(fis)) {
            
            Sheet sheet = workbook.getSheet(sheetName);
            if (sheet == null) {
                throw new RuntimeException("Sheet '" + sheetName + "' not found in file: " + filePath);
            }

            // Read headers
            Row headerRow = sheet.getRow(0);
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }

            // Find test case row
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row row = sheet.getRow(i);
                if (row == null) continue;
                
                Cell testNameCell = row.getCell(0);
                if (testNameCell != null && testNameCell.getStringCellValue().equalsIgnoreCase(testCaseName)) {
                    // Map all columns to their values
                    for (int j = 0; j < headers.size(); j++) {
                        Cell cell = row.getCell(j, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
                        cell.setCellType(CellType.STRING);
                        testData.put(headers.get(j), cell.getStringCellValue());
                    }
                    break;
                }
            }
            
            if (testData.isEmpty()) {
                throw new RuntimeException("Test case '" + testCaseName + "' not found in sheet '" + sheetName + "'");
            }
            
        } catch (Exception e) {
            throw new RuntimeException("Error reading Excel file: " + e.getMessage(), e);
        }
        
        return testData;
    }
}