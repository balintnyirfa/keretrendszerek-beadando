package com.keretrendszerek.beadando.service.impl;

import com.keretrendszerek.beadando.dto.UserDto;
import com.keretrendszerek.beadando.entity.User;
import com.keretrendszerek.beadando.service.ExcelService;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class ExcelServiceImpl implements ExcelService {
    @Override
    public Workbook generateExcel(List<UserDto> users) throws IOException {
        Workbook workbook = new XSSFWorkbook();

        Sheet sheet = workbook.createSheet("User list");

        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("Username");
        headerRow.createCell(1).setCellValue("Email");

        int rowNum = 1;
        for (UserDto user : users) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(user.getUsername());
            row.createCell(1).setCellValue(user.getEmail());
        }

        return workbook;
    }
}
