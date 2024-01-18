package com.keretrendszerek.beadando.service;

import com.keretrendszerek.beadando.entity.Record;
import org.springframework.data.domain.Page;

import java.util.List;

public interface RecordService {
    List<Record> getAllRecords();
    void saveRecord(Record record);
    Record getRecordById(long id);
    void deleteRecordById(long id);
    Page<Record> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection);
}
