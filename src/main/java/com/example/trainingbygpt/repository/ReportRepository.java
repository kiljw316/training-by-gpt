package com.example.trainingbygpt.repository;

import com.example.trainingbygpt.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReportRepository extends JpaRepository<Report, Long> {
}