package com.keretrendszerek.beadando.service;

import com.keretrendszerek.beadando.entity.Record;

import java.util.List;

public interface RecordService {
    List<Record> getAllRecords();
    void saveRecord(Record record);
    void deleteRecordById(long id);
}
