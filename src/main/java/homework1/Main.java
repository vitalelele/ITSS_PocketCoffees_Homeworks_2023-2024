package main.java.homework1;

import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.Collections;

public class Main {
    public static <ArrayList> void main(String[] args) {
        java.util.ArrayList<JobSequencing.Job> jobs = new java.util.ArrayList<>();
        jobs.add(new JobSequencing.Job('a', 2, 100));
        jobs.add(new JobSequencing.Job('b', 1, 19));
        jobs.add(new JobSequencing.Job('c', 2, 27));
        jobs.add(new JobSequencing.Job('d', 1, 25));
        jobs.add(new JobSequencing.Job('e', 3, 15));
        Collections.sort(jobs);
        String jobSequence = JobSequencing.findJobSequence(jobs, jobs.size());
        System.out.println(jobSequence);
    }
}
