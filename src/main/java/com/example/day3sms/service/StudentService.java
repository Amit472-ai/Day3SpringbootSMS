package com.example.day3sms.service;

import com.example.day3sms.DTO.StudentRequestDTO;
import com.example.day3sms.DTO.StudentResponseDTO;
import com.example.day3sms.model.StudentModel;
import com.example.day3sms.repository.StudentRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    public StudentResponseDTO addStudent( StudentRequestDTO dto){
        StudentModel student = new StudentModel(); // 1st object for database
        student.setName(dto.getName());
        student.setAge(dto.getAge());
        student.setEmail(dto.getEmail());

        StudentModel saved = repository.save(student); //2nd object for user, client, response

        return new StudentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getAge(),
                saved.getEmail()
        );
    }
    private final StudentRepo repository;

    public StudentService(StudentRepo repository) {
        this.repository = repository;
    }

    //Create
//    public StudentModel addStudent(StudentModel student){
//        return repository.save(student);
//    }



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
