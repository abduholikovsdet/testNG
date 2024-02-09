package com.crm.util;

import org.apache.commons.io.FileUtils;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class TestUtil {

    public static long PAGE_LOAD_TIMEOUT = 30;
    public static  long DEFAULT_IMPLICIT_WAIT = 30;
    public static long DEFAULT_EXPLICIT_WAIT = 30;

    public static Object[][] excelReader(String filePath) {
        Object[][] data = null;

        try (FileInputStream fileInputStream = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fileInputStream)) {

            // Assuming your data is in the first sheet
            Sheet sheet = workbook.getSheetAt(0);

            int rowCount = sheet.getPhysicalNumberOfRows();
            int colCount = sheet.getRow(0).getPhysicalNumberOfCells();

            data = new Object[rowCount - 1][colCount]; // Subtract 1 for header row

            for (int i = 1; i < rowCount; i++) { // Start from 1 to skip the header row
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    data[i - 1][j] = row.getCell(j).toString();
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static String takeScreenshot(WebDriver driver) {
        File sourceScrnShotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinationFile = new File(System.getProperty("user.dir")+ "/target/html-report/screenshots/"+ System.currentTimeMillis()+".png");

        try {

            FileUtils.copyFile(sourceScrnShotFile, destinationFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return destinationFile.getAbsolutePath();

    }

}
