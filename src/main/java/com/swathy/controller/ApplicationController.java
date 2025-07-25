package com.swathy.controller;


import com.swathy.entity.Application;
import com.swathy.entity.Job;
import com.swathy.entity.User;
import com.swathy.service.ApplicationService;
import com.swathy.service.JobService;
import com.swathy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {

    @Autowired private ApplicationService applicationService;
    @Autowired private UserService userService;
    @Autowired private JobService jobService;

    /*────────────────────────────────────*
     *  1. Candidate applies to a job     *
     *────────────────────────────────────*/
    @PostMapping("/apply/user/{userId}/job/{jobId}")
    public Application apply(@PathVariable int userId,
                             @PathVariable int jobId) {

        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Job job = jobService.getJobById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));

        return applicationService.applyToJob(user, job);
    }

    /*────────────────────────────────────*
     *  2. Candidate views own applies    *
     *────────────────────────────────────*/
    @GetMapping("/user/{userId}")
    public List<Application> myApplications(@PathVariable int userId) {
        User user = userService.getUserById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        return applicationService.getApplicationsByUser(user);
    }

    /*────────────────────────────────────*
     *  3. Company views applicants       *
     *────────────────────────────────────*/
    @GetMapping("/job/{jobId}")
    public List<Application> jobApplicants(@PathVariable int jobId) {
        Job job = jobService.getJobById(jobId)
                .orElseThrow(() -> new RuntimeException("Job not found"));
        return applicationService.getApplicantsForJob(job);
    }

    /*────────────────────────────────────*
     *  4. Optional: get by ID            *
     *────────────────────────────────────*/
    @GetMapping("/{id}")
    public Optional<Application> getById(@PathVariable int id) {
        return applicationService.getById(id);
    }
}

