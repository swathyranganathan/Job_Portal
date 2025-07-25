package com.swathy.service;

import com.swathy.entity.Job;
import com.swathy.entity.User;
import com.swathy.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobRepository jobRepository;

    //Company create job
    public Job createJob(Job job, User company){
       job.setCompany(company);
       job.setStatus(Job.Status.PENDING);
       return jobRepository.save(job);
    }

    //update job status
    public Optional<Job> updateJobStatus (int jobId, Job.Status status){
        Optional<Job> jobOpt = jobRepository.findById(jobId);
        jobOpt.ifPresent(j ->{
            j.setStatus(status);
            jobRepository.save(j);
        });
        return jobOpt;
    }

    //listing all approved jobs
    public List<Job> getApprovedJobs(){
        return jobRepository.findByStatus(Job.Status.APPROVED);
    }

    //FILTERING by location and skill
    public List<Job> filterByLocation(String location){
        return jobRepository.findByLocation(location);
    }
    public List<Job> filrerBySkill(String skill){
        return jobRepository.findBySkillsContaining(skill);
    }

    //get job by ID
    public Optional<Job> getJobById(int id){
        return jobRepository.findById(id);
    }


    // listing all rejected jobs
    public List<Job> getRejectedJobs() {
        return jobRepository.findByStatus(Job.Status.REJECTED);
    }

}
