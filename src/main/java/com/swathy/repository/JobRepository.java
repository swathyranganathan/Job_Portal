package com.swathy.repository;


import com.swathy.entity.Job;
import org.hibernate.type.descriptor.converter.spi.JpaAttributeConverter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface JobRepository extends JpaRepository<Job,Integer> {
    List<Job> findByLocation(String location);
    List<Job> findBySkillsContaining(String skill);
    List<Job> findByStatus(Job.Status status);
}
