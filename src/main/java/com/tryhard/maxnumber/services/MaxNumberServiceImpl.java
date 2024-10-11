package com.tryhard.maxnumber.services;

import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MaxNumberServiceImpl implements MaxNumberService {

    @Override
    public String getMaxNumber(int order, String filepath) throws IOException {
        FileInputStream file = new FileInputStream(filepath);

        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

        ArrayList<Double> data = new ArrayList<>();
        int i = 0;
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            switch (cell.getCellType()) {
                case STRING:
                    data.add(i, Double.parseDouble(row.getCell(0).getRichStringCellValue().getString()));
                    break;
                case NUMERIC, FORMULA:
                    data.add(i, row.getCell(0).getNumericCellValue());
                    break;
                default: data.add(i, 0.0);
            }
            i++;
        }
        System.out.println(data);
        return sortArray(data).get(order).toString();
    }

    private List<Double> sortArray(ArrayList<Double> doubleArrayList) {
        return doubleArrayList.stream().sorted(Comparator.reverseOrder()).toList();
    }
}
