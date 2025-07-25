package com.swathy.repository;

import com.swathy.entity.Application;
import com.swathy.entity.Job;
import com.swathy.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplicationRepository extends JpaRepository<Application,Integer> {
    List<Application> findByUser(User user);
    List<Application> findByJob(Job job);
}
