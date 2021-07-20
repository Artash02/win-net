package helper;

import org.apache.poi.ss.usermodel.*;

import java.io.*;
import java.util.ArrayList;

public class Excel {

    private FileInputStream fileInputStream;
    private Workbook workbook;
    private Sheet sheet;

    public ArrayList readDataByColumn(String excelFilePath, String SheetName, String titleName) throws Exception {
        ArrayList contentItems = new ArrayList<>();
        File file = new File(excelFilePath);
        fileInputStream = new FileInputStream(file);
        workbook = WorkbookFactory.create(fileInputStream);
        sheet = workbook.getSheet(SheetName);
        Row columnsRow = sheet.getRow(0);
        for (int columnNUmber = 0; columnNUmber < columnsRow.getLastCellNum(); columnNUmber++) {
            Cell titleCell = columnsRow.getCell(columnNUmber);
            if (titleCell.toString().equals(titleName)) {
                for (int rowNumber = 1; rowNumber <= sheet.getLastRowNum(); rowNumber++) {
                    Cell cell = sheet.getRow(rowNumber).getCell(columnNUmber);
                    if (cell == null) {
                        break;
                    }
                    contentItems.add((cell.toString()));
                }
            }
        }
        return contentItems;
    }
}

