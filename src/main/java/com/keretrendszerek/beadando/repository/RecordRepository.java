package com.keretrendszerek.beadando.repository;

import com.keretrendszerek.beadando.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long> {
}
