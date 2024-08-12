package com.qkart.utils;

import com.qkart.constants.FrameworkConstants;
import com.qkart.exception.ExcelFileNotFoundException;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
import java.util.*;

public final class ExcelReaderUtils {

    private ExcelReaderUtils() {
    }

    public static List<Map<String, String>> readExcel(String sheetName) {
        InputStream resource = ResourceFinderUtils.getResource(FrameworkConstants.getEXCEL_FILE_LOCATION());
        ArrayList<Map<String, String>> list = new ArrayList<>();
        try (XSSFWorkbook workbook = new XSSFWorkbook(resource)) {
            XSSFSheet sheet = workbook.getSheet(sheetName);
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                String key, value;
                Map<String, String> map = new HashMap<>();
                for (int j = 0; j < sheet.getRow(0).getLastCellNum(); j++) {
                    key = sheet.getRow(0).getCell(j).toString();
                    value = Objects.nonNull(sheet.getRow(i).getCell(j)) ? sheet.getRow(i).getCell(j).toString() : "";
                    map.put(key, value);
                }
                list.add(map);
            }
        } catch (IOException e) {
            throw new ExcelFileNotFoundException("the excel file is not found in the given path. Could you please check the file path!!");
        }
        return list;
    }
}
