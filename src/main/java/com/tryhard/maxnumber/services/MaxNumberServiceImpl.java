package com.tryhard.maxnumber.services;

import com.tryhard.maxnumber.model.MaxNumberResponse;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ArrayUtils;
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
    public MaxNumberResponse getMaxNumber(int index, String filepath) throws IOException {
        FileInputStream file = new FileInputStream(filepath);

        Workbook workbook = new XSSFWorkbook(file);

        Sheet sheet = workbook.getSheetAt(0);

        ArrayList<Integer> data = new ArrayList<>();
        int i = 0;
        for (Row row : sheet) {
            Cell cell = row.getCell(0);
            switch (cell.getCellType()) {
                case STRING:
                    data.add(i, Integer.parseInt(row.getCell(0).getRichStringCellValue().getString()));
                    break;
                case NUMERIC, FORMULA:
                    data.add(i, (int) row.getCell(0).getNumericCellValue());
                    break;
                default: data.add(i, 0);
            }
            i++;
        }
        System.out.println(data);
        int[] array = ArrayUtils.toPrimitive(data.toArray(new Integer[0]));
        sortArray(array);
        System.out.println(Arrays.toString(array));
        return new MaxNumberResponse(String.valueOf(array[array.length-index-1]));
    }

    private void sortArray(int[] array) {
        mergeSort(array, 0, array.length-1);
    }

    // Сортировка слиянием сложность O(nlog(n))
    public void mergeSort(int[] array, int left, int right) {
        if (right <= left) return;
        int mid = (left+right)/2;
        mergeSort(array, left, mid);
        mergeSort(array, mid+1, right);
        merge(array, left, mid, right);
    }

    void merge(int[] array, int left, int mid, int right) {
        // вычисляем длину
        int lengthLeft = mid - left + 1;
        int lengthRight = right - mid;

        // создаем временные подмассивы
        int leftArray[] = new int [lengthLeft];
        int rightArray[] = new int [lengthRight];

        // копируем отсортированные массивы во временные
        for (int i = 0; i < lengthLeft; i++)
            leftArray[i] = array[left+i];
        for (int i = 0; i < lengthRight; i++)
            rightArray[i] = array[mid+i+1];

        // итераторы содержат текущий индекс временного подмассива
        int leftIndex = 0;
        int rightIndex = 0;

        // копируем из leftArray и rightArray обратно в массив
        for (int i = left; i < right + 1; i++) {
            // если остаются нескопированные элементы в R и L, копируем минимальный
            if (leftIndex < lengthLeft && rightIndex < lengthRight) {
                if (leftArray[leftIndex] < rightArray[rightIndex]) {
                    array[i] = leftArray[leftIndex];
                    leftIndex++;
                }
                else {
                    array[i] = rightArray[rightIndex];
                    rightIndex++;
                }
            }
            // если все элементы были скопированы из rightArray, скопировать остальные из leftArray
            else if (leftIndex < lengthLeft) {
                array[i] = leftArray[leftIndex];
                leftIndex++;
            }
            // если все элементы были скопированы из leftArray, скопировать остальные из rightArray
            else if (rightIndex < lengthRight) {
                array[i] = rightArray[rightIndex];
                rightIndex++;
            }
        }
    }
}
