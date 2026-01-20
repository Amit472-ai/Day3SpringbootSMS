package com.example.day3sms.service;

import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepo;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
    private final StudentRepo repository;

    public StudentService(StudentRepo repository) {
        this.repository = repository;
    }

    //Create
    public StudentModel addStudent(StudentModel student){
        return repository.save(student);
    }

}
