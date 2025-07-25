package com.swathy.service;



import com.swathy.entity.Application;
import com.swathy.entity.Job;
import com.swathy.entity.User;
import com.swathy.repository.ApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {

    @Autowired
    private ApplicationRepository applicationRepository;

    /*───────────────────────────────*
     *  1. Candidate applies to job  *
     *───────────────────────────────*/
    public Application applyToJob(User user, Job job) {
        // Check if already applied
        boolean exists = applicationRepository
                .findByUser(user)
                .stream()
                .anyMatch(app -> app.getJob().getId() == job.getId());
        if (exists) {
            throw new RuntimeException("You already applied to this job.");
        }

        Application app = new Application();
        app.setUser(user);
        app.setJob(job);
        app.setAppliedAt(LocalDateTime.now());
        return applicationRepository.save(app);
    }

    /*───────────────────────────────*
     *  2. View applications         *
     *───────────────────────────────*/
    public List<Application> getApplicationsByUser(User user) {
        return applicationRepository.findByUser(user);
    }

    public List<Application> getApplicantsForJob(Job job) {
        return applicationRepository.findByJob(job);
    }

    /*───────────────────────────────*
     *  3. Find by ID (optional)     *
     *───────────────────────────────*/
    public Optional<Application> getById(int id) {
        return applicationRepository.findById(id);
    }
}
