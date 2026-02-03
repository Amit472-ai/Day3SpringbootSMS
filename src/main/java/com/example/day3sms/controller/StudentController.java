package com.example.day3sms.controller;

import com.example.day3sms.DTO.StudentRequestDTO;
import com.example.day3sms.DTO.StudentResponseDTO;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class StudentController {
    //Request - Post
    //Response - Get

    //controller --> Service --> DTO --> Repository --> Model(Database)

    private final StudentService service;

    public StudentController(StudentService service) {
        this.service = service;
    }
    //Create function API

    @PostMapping("/add-student")
    public StudentResponseDTO addStudent(@Valid @RequestBody StudentRequestDTO student){
        return service.addStudent(student);
    }

    //Display Students

    @GetMapping("/students")
    public List<StudentModel> getStudents(){
        return service.getStudents();
    }

    @PutMapping("/update/{id}")
    public StudentModel updateStudent(@PathVariable String id, @RequestBody StudentModel student){
        return service.updateStudent(id,student);
    }

    @DeleteMapping("/students/{id}")
    public StudentModel deleteStudent(@PathVariable String id) {
        return service.deleteStudent(id);
    }








}
