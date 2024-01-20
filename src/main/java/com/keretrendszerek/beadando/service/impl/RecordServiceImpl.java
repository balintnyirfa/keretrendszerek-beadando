package com.keretrendszerek.beadando.service.impl;

import com.keretrendszerek.beadando.entity.Record;
import com.keretrendszerek.beadando.repository.RecordRepository;
import com.keretrendszerek.beadando.service.RecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecordServiceImpl implements RecordService {
    @Autowired
    private RecordRepository recordRepository;

    @Override
    public List<Record> getAllRecords() {
        return recordRepository.findAll();
    }

    @Override
    public void saveRecord(Record record) {
        this.recordRepository.save(record);
    }

    @Override
    public Record getRecordById(long id) {
        Optional<Record> optional = recordRepository.findById(id);
        Record record = null;
        if (optional.isPresent()) {
            record = optional.get();
        } else {
            throw new RuntimeException(" Record not found for id :: " + id);
        }
        return record;
    }

    @Override
    public void deleteRecordById(long id) {
        this.recordRepository.deleteById(id);
    }

    @Override
    public Page<Record> findPaginated(int pageNo, int pageSize, String sortField, String sortDirection) {
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending() :
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.recordRepository.findAll(pageable);
    }
}
