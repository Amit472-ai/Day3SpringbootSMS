package com.example.day3sms.service;

import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

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

    //Display Structure

    public List<StudentModel> getStudents(){
        return repository.findAll();
    }

    public StudentModel updateStudent(String id , StudentModel student){
        StudentModel existingStudent = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("No Student Found"));

        existingStudent.setName(student.getName());
        existingStudent.setAge(student.getAge());
        existingStudent.setEmail(student.getEmail());

        return repository.save(existingStudent);
    }

    public StudentModel deleteStudent(String id) {

        StudentModel student = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));

        repository.deleteById(id);

        return student;
    }


}
