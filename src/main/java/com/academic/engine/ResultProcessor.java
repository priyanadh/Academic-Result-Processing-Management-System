package com.academic.engine;

import java.util.List;
import java.util.stream.Collectors;

public class ResultProcessor {

    public static class StudentRecord {
        public String name;
        public String department;
        public double cgpa;
        
        public StudentRecord(String name, String department, double cgpa) {
            this.name = name;
            this.department = department;
            this.cgpa = cgpa;
        }
    }

    /**
     * Filters high-volume student lists utilizing modern functional Java Streams.
     * Demonstrates query mapping acceleration over typical full sequences loops.
     */
    public List<StudentRecord> filterTopPerformers(List<StudentRecord> databaseRecords, String targetDept) {
        return databaseRecords.stream()
            .filter(student -> student.department.equalsIgnoreCase(targetDept))
            .filter(student -> student.cgpa >= 7.50) // High performance baseline mapping
            .collect(Collectors.toList());
    }
}
