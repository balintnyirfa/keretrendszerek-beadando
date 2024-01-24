package com.keretrendszerek.beadando.service;

import com.keretrendszerek.beadando.dto.UserDto;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.IOException;
import java.util.List;

public interface ExcelService {
    Workbook generateExcel(List<UserDto> users) throws IOException;
}
