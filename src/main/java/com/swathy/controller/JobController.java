package com.swathy.controller;

import com.swathy.entity.Job;
import com.swathy.entity.User;
import com.swathy.service.JobService;
import com.swathy.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api/jobs")
public class JobController {
    @Autowired
    private JobService jobService;
    @Autowired
    private UserService userService;

    //company post new job
    @PostMapping("/create/{companyId}")
    public Job createJob(@PathVariable int companyId, @Valid @RequestBody Job job){
        User company = userService.getUserById(companyId) .orElseThrow(()->new RuntimeException("Company not found"));
        return jobService.createJob(job,company);
    }

    //admin update status
    @PutMapping("/{jobId}/status/{status}")
    public Optional<Job> updateStatus(@PathVariable int jobId,@PathVariable Job.Status status){
        return jobService.updateJobStatus(jobId,status);
    }

    //list approved jobs
    @GetMapping
    public List<Job> listApprovedJobs(){
        return jobService.getApprovedJobs();
    }

    //get job by id
    @GetMapping("/{id}")
    public Optional<Job> getJob(@PathVariable int id){
        return jobService.getJobById(id);
    }

    //filter by location and skill
    @GetMapping("/location/{location}")
    public List<Job> byLocation(@PathVariable String location){
        return jobService.filterByLocation(location);
    }
    @GetMapping("/skill/{skill}")
    public List<Job> bySkill(@PathVariable String skill){
        return jobService.filrerBySkill(skill);
    }


    @GetMapping("/rejected")
    public List<Job> listRejectedJobs() {
        return jobService.getRejectedJobs();
    }

    @PutMapping("/{jobId}/status")
    public ResponseEntity<Job> updateJobStatus(
            @PathVariable int jobId,
            @RequestBody Map<String, String> requestBody) {

        try {
            Job.Status newStatus = Job.Status.valueOf(requestBody.get("status").toUpperCase());
            Optional<Job> updatedJob = jobService.updateJobStatus(jobId, newStatus);

            return updatedJob.map(ResponseEntity::ok)
                    .orElseGet(() -> ResponseEntity.notFound().build());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null); // invalid status passed
        }
    }


}
