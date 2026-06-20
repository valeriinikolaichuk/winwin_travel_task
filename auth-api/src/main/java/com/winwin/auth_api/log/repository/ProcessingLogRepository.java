package com.winwin.auth_api.log.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.winwin.auth_api.log.entity.ProcessingLog;

@Repository
public interface ProcessingLogRepository 
    extends JpaRepository<ProcessingLog, UUID> {
        
}
